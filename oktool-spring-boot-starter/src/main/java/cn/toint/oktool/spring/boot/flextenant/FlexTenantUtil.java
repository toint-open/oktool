package cn.toint.oktool.spring.boot.flextenant;


import java.util.List;
import java.util.function.Supplier;

/**
 * mybatis-flex租户工具类
 *
 * @author Toint
 * @date 2025/10/12
 */
public class FlexTenantUtil {

    /**
     * 切换租户并执行(自动清除上下文)
     *
     * @param tenantIds 租户ID
     * @param runnable  执行方法
     */
    public static void execute(List<Object> tenantIds, Runnable runnable) {
        try {
            FlexTenantContextHolder.setTenantIds(tenantIds);
            runnable.run();
        } finally {
            FlexTenantContextHolder.clear();
        }
    }

    /**
     * 切换租户并执行(自动清除上下文)
     *
     * @param tenantIds 租户ID
     * @param supplier  执行方法
     * @return 执行结果
     */
    public static <T> T execute(List<Object> tenantIds, Supplier<T> supplier) {
        try {
            FlexTenantContextHolder.setTenantIds(tenantIds);
            return supplier.get();
        } finally {
            FlexTenantContextHolder.clear();
        }
    }

}
