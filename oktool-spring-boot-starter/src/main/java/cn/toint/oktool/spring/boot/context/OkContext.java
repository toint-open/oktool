package cn.toint.oktool.spring.boot.context;

import cn.toint.oktool.util.Assert;
import cn.toint.oktool.util.ScopedValueUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

/**
 * 上下文
 *
 * @author Toint
 * @since 2025/10/21
 */
public class OkContext {

    /**
     * 上下文实例
     */
    private static final ScopedValue<Map<String, Object>> CONTEXT_INSTANCE = ScopedValue.newInstance();

    /**
     * 租户ID
     */
    private static final String TENANT_ID_NAME = "tenantId";

    /**
     * 任务ID
     */
    private static final String TRACE_ID_NAME = "traceId";

    /**
     * 检查是否存在上下文
     */
    public static boolean hasContext() {
        return CONTEXT_INSTANCE.isBound() && CONTEXT_INSTANCE.get() != null;
    }

    /**
     * 检查上下文, 无上下文时抛异常
     */
    public static void checkContext() {
        if (!hasContext()) {
            throw new RuntimeException("OkContext上下文尚未初始化");
        }
    }

    /**
     * 获取当前上下文
     * <p>
     * 注意: 上下文未绑定时抛异常
     * </p>
     *
     * @return 当前上下文 (非null)
     * @throws RuntimeException 上下文未绑定时抛异常
     */
    public static Map<String, Object> getContext() {
        checkContext();
        return CONTEXT_INSTANCE.get();
    }

    /**
     * 获取指定key的值
     *
     * @param key key
     * @param <V> 泛型
     * @return 值 (上下文未绑定或key不存在时返回null)
     */
    @SuppressWarnings("unchecked")
    public static <V> V get(String key) {
        Assert.notBlankParam(key, "key");
        if (hasContext()) {
            return (V) getContext().get(key);
        } else {
            return null;
        }
    }

    /**
     * 获取指定key的值, 若不存在则返回默认值
     *
     * @param key          key
     * @param <V>          泛型
     * @param defaultValue 默认值
     * @return 值 (上下文未绑定或key不存在时返回defaultValue)
     */
    public static <V> V getOrDefault(String key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 设置指定key的值
     * <p>
     * 注意: 写操作要求上下文必须存在,否则抛出异常
     * </p>
     *
     * @param key   key (非null)
     * @param value 值 (非null)
     * @param <V>   泛型
     * @throws RuntimeException 如果上下文未初始化
     */
    public static <V> void put(String key, V value) {
        Assert.notBlankParam(key, "key");
        Assert.notNullParam(value, "value");
        getContext().put(key, value);
    }

    /**
     * 删除指定key的值
     * <p>
     * 无上下文时忽略
     * </p>
     *
     * @param key key (非null)
     */
    private static void delete(String key) {
        Assert.notBlankParam(key, "key");
        if (hasContext()) {
            getContext().remove(key);
        }
    }

    /**
     * 运行全新的上下文
     */
    public static void runWithNewContext(Runnable runnable) {
        ScopedValue.where(CONTEXT_INSTANCE, new ConcurrentHashMap<>())
                .run(runnable);
    }

    /**
     * 运行全新的上下文
     */
    public static <R> R callWithNewContext(Supplier<R> supplier) {
        return ScopedValue.where(CONTEXT_INSTANCE, new ConcurrentHashMap<>())
                .call(supplier::get);
    }

    /**
     * 在异步任务中传播 OkContext 上下文（无返回值）
     * <p>
     * 自动捕获当前的上下文并在异步任务中恢复。
     * 如果当前没有上下文，则在新上下文中执行。
     * </p>
     */
    public static CompletableFuture<Void> runAsync(Runnable runnable) {
        Assert.notNullParam(runnable, "runnable");
        return ScopedValueUtil.runAsync(CONTEXT_INSTANCE, runnable);
    }

    /**
     * 在异步任务中传播 OkContext 上下文（有返回值）
     * <p>
     * 自动捕获当前的上下文并在异步任务中恢复。
     * 如果当前没有上下文，则在新上下文中执行。
     * </p>
     */
    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        Assert.notNullParam(supplier, "supplier");
        return ScopedValueUtil.supplyAsync(CONTEXT_INSTANCE, supplier);
    }

    /**
     * 获取租户ID
     *
     * @return 租户ID (null或空集合, 集合中不包含null元素)
     */
    public static List<Object> getTenantIds() {
        return get(TENANT_ID_NAME);
    }

    /**
     * 设置租户ID
     *
     * @param tenantIds 租户ID. null或空集合代表清空当前租户上下文, 集合中的空元素会被自动忽略
     */
    public static void setTenantIds(List<Object> tenantIds) {
        if (tenantIds == null) {
            tenantIds = new CopyOnWriteArrayList<>();
        } else {
            tenantIds = tenantIds instanceof CopyOnWriteArrayList ? tenantIds : new CopyOnWriteArrayList<>(tenantIds);
            tenantIds.removeIf(Objects::isNull);
        }
        put(TENANT_ID_NAME, tenantIds);
    }

    /**
     * 切换租户上下文并执行
     *
     * @param tenantIds 租户ID
     * @param supplier  执行方法
     * @return 执行结果
     */
    public static <T> T callWithTenantIds(List<Object> tenantIds, Supplier<T> supplier) {
        Assert.notNullParam(supplier, "supplier");
        if (hasContext()) {
            // 已有上下文：保存旧值 → 设置新值 → 执行 → 恢复旧值
            List<Object> oldTenantIds = getTenantIds();
            try {
                setTenantIds(tenantIds);
                return supplier.get();
            } finally {
                setTenantIds(oldTenantIds);
            }
        } else {
            // 无上下文：创建新上下文 → 设置值 → 执行（自动销毁）
            return callWithNewContext(() -> {
                setTenantIds(tenantIds);
                return supplier.get();
            });
        }
    }

    /**
     * 设置任务ID
     * <p>
     * 同时设置到 MDC 和 OkContext, MDC优先读取
     * </p>
     *
     * @param traceId 任务ID (不能为空)
     */
    public static void setTraceId(String traceId) {
        Assert.notBlankParam(traceId, "traceId");
        MDC.put(TRACE_ID_NAME, traceId);

        // 如果有上下文, 也设置到上下文中
        if (hasContext()) {
            put(TRACE_ID_NAME, traceId);
        }
    }

    /**
     * 获取任务ID
     * <p>
     * 优先从 MDC 读取, MDC 为空时从上下文读取
     * </p>
     *
     * @return 任务ID (可能为null)
     */
    public static String getTraceId() {
        String value = MDC.get(TRACE_ID_NAME);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        return get(TRACE_ID_NAME);
    }
}
