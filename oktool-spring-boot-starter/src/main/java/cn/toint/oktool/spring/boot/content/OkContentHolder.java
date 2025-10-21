package cn.toint.oktool.spring.boot.content;

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
 * 上下文持有对象
 *
 * @author Toint
 * @since 2025/10/21
 */
public class OkContentHolder {

    /**
     * 上下文实例
     */
    private static final ScopedValue<Map<String, Object>> CONTENT_INSTANCE = ScopedValue.newInstance();

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
        return CONTENT_INSTANCE.isBound();
    }

    /**
     * 检查上下文
     */
    public static void checkContent() {
        if (!hasContext()) {
            throw new RuntimeException("OkContent上下文尚未初始化");
        }
    }

    /**
     * 获取当前上下文
     *
     * @return 当前上下文
     */
    public static Map<String, Object> getContent() {
        checkContent();
        return CONTENT_INSTANCE.get();
    }

    /**
     * 获取指定key的值
     *
     * @param key key
     * @param <V> 泛型
     * @return 值 (若ScopedValue未绑定则返回null)
     */
    @SuppressWarnings("unchecked")
    public static <V> V get(String key) {
        Assert.notBlankParam(key, "key");
        return (V) getContent().get(key);
    }

    /**
     * 设置指定key的值
     * <p>
     * 若ScopedValue未绑定则忽略
     * </p>
     *
     * @param key   key (非null)
     * @param value 值 (非null)
     * @param <V>   泛型
     */
    public static <V> void put(String key, V value) {
        Assert.notBlankParam(key, "key");
        Assert.notNullParam(value, "value");
        getContent().put(key, value);
    }

    /**
     * 删除指定key的值
     * <p>
     * 若ScopedValue未绑定则忽略
     * </p>
     *
     * @param key key (非null)
     */
    private static void delete(String key) {
        Assert.notBlankParam(key, "key");
        getContent().remove(key);
    }

    /**
     * 运行全新的上下文
     */
    public static void runWithNewContext(Runnable runnable) {
        ScopedValue.where(CONTENT_INSTANCE, new ConcurrentHashMap<>())
                .run(runnable);
    }

    /**
     * 运行全新的上下文
     */
    public static <R> R callWithNewContext(Supplier<R> supplier) {
        return ScopedValue.where(CONTENT_INSTANCE, new ConcurrentHashMap<>())
                .call(supplier::get);
    }

    /**
     * 在异步任务中传播 OkContent 上下文（无返回值）
     * <p>
     * 自动捕获当前的上下文并在异步任务中恢复。
     * 如果当前没有上下文，则在新上下文中执行。
     * </p>
     */
    public static CompletableFuture<Void> runAsync(Runnable runnable) {
        Assert.notNullParam(runnable, "runnable");
        return ScopedValueUtil.runAsync(CONTENT_INSTANCE, runnable);
    }

    /**
     * 在异步任务中传播 OkContent 上下文（有返回值）
     * <p>
     * 自动捕获当前的上下文并在异步任务中恢复。
     * 如果当前没有上下文，则在新上下文中执行。
     * </p>
     */
    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        Assert.notNullParam(supplier, "supplier");
        return ScopedValueUtil.supplyAsync(CONTENT_INSTANCE, supplier);
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
     *
     * @param traceId 任务ID (不能为空)
     */
    public static void setTraceId(String traceId) {
        Assert.notBlankParam(traceId, "traceId");
        MDC.put(TRACE_ID_NAME, traceId);
        put(TRACE_ID_NAME, traceId);
    }

    /**
     * 获取任务ID
     *
     * @return 任务ID (可能为null)
     */
    public static String getTraceId() {
        String value = MDC.get(TRACE_ID_NAME);
        return StringUtils.isBlank(value) ? get(TRACE_ID_NAME) : value;
    }
}
