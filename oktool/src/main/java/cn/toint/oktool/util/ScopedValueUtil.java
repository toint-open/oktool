package cn.toint.oktool.util;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * ScopedValue 异步传播工具类
 * <p>
 * 用于在异步任务（CompletableFuture）中传播 JDK 21+ 的 ScopedValue，
 * 解决 ScopedValue 在跨线程时值丢失的问题。
 * </p>
 *
 * <h3>使用示例：</h3>
 * <pre>{@code
 * ScopedValue<String> USER_ID = ScopedValue.newInstance();
 *
 * ScopedValue.where(USER_ID, "user123").run(() -> {
 *     // 异步任务中自动传播 USER_ID
 *     ScopedValueUtil.supplyAsync(USER_ID, () -> {
 *         return "Processing for: " + USER_ID.get();
 *     }).thenAccept(System.out::println);
 * });
 * }</pre>
 *
 * @author Toint
 * @since 2025/10/21
 */
public class ScopedValueUtil {

    /**
     * 虚拟线程执行器，用于执行异步任务
     */
    private static final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    /**
     * 在异步任务中传播单个 ScopedValue（无返回值）
     * <p>
     * 捕获当前线程中指定 ScopedValue 的值，并在异步任务中恢复该值的绑定。
     * </p>
     *
     * @param scopedValue 需要传播的 ScopedValue
     * @param runnable    异步执行的任务
     * @param <V>         ScopedValue 的值类型
     * @return CompletableFuture<Void> 异步任务的 Future 对象
     */
    public static <V> CompletableFuture<Void> runAsync(ScopedValue<V> scopedValue, Runnable runnable) {
        return runAsync(Collections.singletonList(scopedValue), runnable);
    }

    /**
     * 在异步任务中传播单个 ScopedValue（有返回值）
     * <p>
     * 捕获当前线程中指定 ScopedValue 的值，并在异步任务中恢复该值的绑定。
     * </p>
     *
     * @param scopedValue 需要传播的 ScopedValue
     * @param supplier    异步执行的任务，返回结果
     * @param <V>         ScopedValue 的值类型
     * @param <R>         异步任务的返回值类型
     * @return CompletableFuture<R> 异步任务的 Future 对象，包含任务执行结果
     */
    public static <V, R> CompletableFuture<R> supplyAsync(ScopedValue<V> scopedValue, Supplier<R> supplier) {
        return supplyAsync(Collections.singletonList(scopedValue), supplier);
    }

    /**
     * 在异步任务中传播多个 ScopedValue（无返回值）
     * <p>
     * 捕获当前线程中多个 ScopedValue 的值，并在异步任务中恢复这些值的绑定。
     * 只有已绑定的 ScopedValue 会被传播，未绑定的会被忽略。
     * </p>
     *
     * @param scopedValues 需要传播的 ScopedValue 列表（可以包含不同类型的 ScopedValue）
     * @param runnable     异步执行的任务
     * @return CompletableFuture<Void> 异步任务的 Future 对象
     */
    public static CompletableFuture<Void> runAsync(List<ScopedValue<?>> scopedValues, Runnable runnable) {
        Map<ScopedValue<?>, Object> scopedValueMap = captureContext(scopedValues);
        return runAsync(scopedValueMap, runnable);
    }

    /**
     * 在异步任务中传播多个 ScopedValue（有返回值）
     * <p>
     * 捕获当前线程中多个 ScopedValue 的值，并在异步任务中恢复这些值的绑定。
     * 只有已绑定的 ScopedValue 会被传播，未绑定的会被忽略。
     * </p>
     *
     * @param scopedValues 需要传播的 ScopedValue 列表（可以包含不同类型的 ScopedValue）
     * @param supplier     异步执行的任务，返回结果
     * @param <R>          异步任务的返回值类型
     * @return CompletableFuture<R> 异步任务的 Future 对象，包含任务执行结果
     */
    public static <R> CompletableFuture<R> supplyAsync(List<ScopedValue<?>> scopedValues, Supplier<R> supplier) {
        Map<ScopedValue<?>, Object> scopedValueMap = captureContext(scopedValues);
        return supplyAsync(scopedValueMap, supplier);
    }

    /**
     * 在异步任务中传播 ScopedValue 上下文（有返回值）
     * <p>
     * 根据提供的 ScopedValue 上下文映射，在异步任务中恢复这些值的绑定。
     * 这是最底层的传播方法，其他 supplyAsync 方法最终都会调用此方法。
     * </p>
     *
     * @param scopedValueMap ScopedValue 到其值的映射，表示需要传播的上下文
     * @param supplier       异步执行的任务，返回结果
     * @param <R>            异步任务的返回值类型
     * @return CompletableFuture<R> 异步任务的 Future 对象，包含任务执行结果
     * @throws NullPointerException 如果 supplier 为 null
     */
    public static <R> CompletableFuture<R> supplyAsync(Map<ScopedValue<?>, Object> scopedValueMap, Supplier<R> supplier) {
        Objects.requireNonNull(supplier, "supplier must not be null");

        if (scopedValueMap == null || scopedValueMap.isEmpty()) {
            return CompletableFuture.supplyAsync(supplier, executorService);
        }

        return CompletableFuture.supplyAsync(() -> {
            ScopedValue.Carrier carrier = buildCarrier(scopedValueMap);
            return carrier.call(supplier::get);
        }, executorService);
    }

    /**
     * 在异步任务中传播 ScopedValue 上下文（无返回值）
     * <p>
     * 根据提供的 ScopedValue 上下文映射，在异步任务中恢复这些值的绑定。
     * 这是最底层的传播方法，其他 runAsync 方法最终都会调用此方法。
     * </p>
     *
     * @param scopedValueMap ScopedValue 到其值的映射，表示需要传播的上下文
     * @param runnable       异步执行的任务
     * @return CompletableFuture<Void> 异步任务的 Future 对象
     * @throws NullPointerException 如果 runnable 为 null
     */
    public static CompletableFuture<Void> runAsync(Map<ScopedValue<?>, Object> scopedValueMap, Runnable runnable) {
        Objects.requireNonNull(runnable, "runnable must not be null");

        return supplyAsync(scopedValueMap, () -> {
            runnable.run();
            return null;
        });
    }

    /**
     * 构建 ScopedValue.Carrier 对象
     * <p>
     * Carrier 用于在新的执行上下文中批量绑定多个 ScopedValue。
     * 通过链式调用 where() 方法构建包含所有绑定的 Carrier。
     * </p>
     *
     * @param scopedValueMap ScopedValue 到其值的映射
     * @return ScopedValue.Carrier 对象，如果映射为空则返回 null
     */
    @SuppressWarnings("unchecked")
    public static ScopedValue.Carrier buildCarrier(Map<ScopedValue<?>, Object> scopedValueMap) {
        if (scopedValueMap == null || scopedValueMap.isEmpty()) return null;

        ScopedValue.Carrier carrier = null;
        for (Map.Entry<ScopedValue<?>, Object> entry : scopedValueMap.entrySet()) {
            // 类型安全：这里需要强制转换，但在运行时是类型安全的
            // 因为我们确保了 value 就是对应 ScopedValue 的正确类型
            ScopedValue<Object> key = (ScopedValue<Object>) entry.getKey();
            Object value = entry.getValue();

            if (carrier == null) {
                carrier = ScopedValue.where(key, value);
            } else {
                carrier = carrier.where(key, value);
            }
        }
        return carrier;
    }

    /**
     * 捕获当前线程中已绑定的 ScopedValue
     * <p>
     * 遍历指定的 ScopedValue 列表，提取所有在当前线程中已绑定的值，
     * 构建成 Map 以便后续在异步线程中恢复。未绑定或为 null 的 ScopedValue 会被忽略。
     * </p>
     *
     * @param scopedValues 需要捕获的 ScopedValue 列表（可以包含不同类型的 ScopedValue）
     * @return {@code Map<ScopedValue<?>, Object>} 包含已绑定值的映射，如果没有绑定值则返回空 Map
     */
    public static Map<ScopedValue<?>, Object> captureContext(List<ScopedValue<?>> scopedValues) {
        Map<ScopedValue<?>, Object> scopedValueMap = new HashMap<>();
        if (scopedValues != null && !scopedValues.isEmpty()) {
            scopedValues.forEach(scopedValue -> {
                if (scopedValue != null && scopedValue.isBound()) {
                    scopedValueMap.put(scopedValue, scopedValue.get());
                }
            });
        }
        return scopedValueMap;
    }

    /**
     * 安全获取 ScopedValue 的绑定值
     * <p>
     * 与 {@link ScopedValue#get()} 不同，此方法在 ScopedValue 未绑定时返回 Optional.empty()，
     * 而不是抛出 {@link NoSuchElementException} 异常。适用于需要容错处理的场景。
     * </p>
     *
     * <h3>使用场景：</h3>
     * <ul>
     *     <li>不确定 ScopedValue 是否已绑定时</li>
     *     <li>需要优雅处理未绑定情况，避免异常</li>
     *     <li>可选的上下文传递场景</li>
     * </ul>
     *
     * <h3>使用示例：</h3>
     * <pre>{@code
     * ScopedValue<String> USER_ID = ScopedValue.newInstance();
     *
     * // 安全获取，不会抛异常
     * ScopedValueUtil.get(USER_ID).ifPresent(userId -> {
     *     System.out.println("User ID: " + userId);
     * });
     * }</pre>
     *
     * @param scopedValue 需要获取值的 ScopedValue，允许为 null
     * @param <V>         ScopedValue 的值类型
     * @return Optional 包装的值
     * @see ScopedValue#get()
     * @see ScopedValue#isBound()
     */
    public static <V> Optional<V> get(ScopedValue<V> scopedValue) {
        return Optional.ofNullable(getOrNull(scopedValue));
    }

    /**
     * 安全获取 ScopedValue 的绑定值
     * <p>
     * 与 {@link ScopedValue#get()} 不同，此方法在 ScopedValue 未绑定时返回 null，
     * 而不是抛出 {@link NoSuchElementException} 异常。适用于需要容错处理的场景。
     * </p>
     *
     * <h3>使用场景：</h3>
     * <ul>
     *     <li>不确定 ScopedValue 是否已绑定时</li>
     *     <li>需要优雅处理未绑定情况，避免异常</li>
     *     <li>可选的上下文传递场景</li>
     * </ul>
     *
     * <h3>使用示例：</h3>
     * <pre>{@code
     * ScopedValue<String> USER_ID = ScopedValue.newInstance();
     *
     * // 安全获取，不会抛异常
     * String userId = ScopedValueUtil.getOrNull(USER_ID);
     * if (userId != null) {
     *     System.out.println("User ID: " + userId);
     * }
     * }</pre>
     *
     * @param scopedValue 需要获取值的 ScopedValue，允许为 null
     * @param <V>         ScopedValue 的值类型
     * @return 已绑定的值，如果未绑定或 scopedValue 为 null 则返回 null
     * @see ScopedValue#get()
     * @see ScopedValue#isBound()
     */
    public static <V> V getOrNull(ScopedValue<V> scopedValue) {
        return orElse(scopedValue, null);
    }

    /**
     * 安全获取 ScopedValue 的绑定值，若未绑定则返回默认值
     * <p>
     * 与 {@link ScopedValue#get()} 不同，此方法在 ScopedValue 未绑定时返回指定的默认值，
     * 而不是抛出 {@link NoSuchElementException} 异常。适用于需要提供后备值的场景。
     * </p>
     *
     * <h3>使用场景：</h3>
     * <ul>
     *     <li>需要为未绑定的 ScopedValue 提供后备值</li>
     *     <li>避免显式的 null 检查，直接提供默认值</li>
     *     <li>链式调用或函数式编程中简化逻辑</li>
     * </ul>
     *
     * <h3>使用示例：</h3>
     * <pre>{@code
     * ScopedValue<String> USER_ROLE = ScopedValue.newInstance();
     *
     * // 获取用户角色，未绑定时返回 "guest"
     * String role = ScopedValueUtil.orElse(USER_ROLE, "guest");
     * System.out.println("User role: " + role); // 输出 "guest"（若未绑定）
     * }</pre>
     *
     * @param scopedValue  需要获取值的 ScopedValue，允许为 null
     * @param defaultValue 当 ScopedValue 未绑定或为 null 时返回的默认值
     * @param <V>          ScopedValue 的值类型
     * @return 已绑定的值，如果未绑定或 scopedValue 为 null 则返回 defaultValue
     * @see #getOrNull(ScopedValue)
     * @see ScopedValue#get()
     * @see ScopedValue#isBound()
     */
    public static <V> V orElse(ScopedValue<V> scopedValue, V defaultValue) {
        if (scopedValue != null && scopedValue.isBound()) {
            return scopedValue.get();
        } else {
            return defaultValue;
        }
    }

    /**
     * 安全判断指定的 {@link ScopedValue} 是否已绑定到当前线程的上下文。
     *
     * <p>此方法提供空安全检查，避免直接调用 {@code scopedValue.isBound()} 时可能出现的 {@code NullPointerException}。</p>
     *
     * <p>典型使用场景：</p>
     * <pre>{@code
     * if (ScopedValueUtils.isBound(CONTEXT_KEY)) {
     *     // 已绑定时执行逻辑
     * }
     * }</pre>
     *
     * @param <V>         ScopedValue 的泛型类型
     * @param scopedValue 要检查的 {@link ScopedValue} 实例，允许为 {@code null}
     * @return {@code true} 如果 {@code scopedValue} 非空且已绑定到当前线程；
     * {@code false} 如果 {@code scopedValue} 为 {@code null} 或未绑定
     * @see ScopedValue#isBound()
     */
    public static <V> boolean isBound(ScopedValue<V> scopedValue) {
        return scopedValue != null && scopedValue.isBound();
    }
}