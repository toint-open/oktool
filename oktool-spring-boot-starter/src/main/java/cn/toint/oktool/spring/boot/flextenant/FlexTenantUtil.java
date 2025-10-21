//package cn.toint.oktool.spring.boot.flextenant;
//
//
//import cn.toint.oktool.spring.boot.content.OkContentHolder;
//
//import java.util.List;
//import java.util.function.Supplier;
//
///**
// * mybatis-flex租户工具类
// *
// * @author Toint
// * @since 2025/10/12
// */
//public class FlexTenantUtil {
//
//    /**
//     * 切换租户并执行
//     *
//     * @param tenantIds 租户ID
//     * @param runnable  执行方法
//     */
//    public static void runWithContext(List<Object> tenantIds, Runnable runnable) {
//        OkContentHolder.runWithNewContext(() -> {
//            OkContentHolder.setTenantIds(tenantIds);
//            runnable.run();
//        });
//    }
//
//    /**
//     * 切换租户并执行
//     *
//     * @param tenantIds 租户ID
//     * @param supplier  执行方法
//     * @return 执行结果
//     */
//    public static <T> T callWithContext(List<Object> tenantIds, Supplier<T> supplier) {
//        return OkContentHolder.callWithNewContext(() -> {
//            OkContentHolder.setTenantIds(tenantIds);
//            return supplier.get();
//        });
//    }
//
//}
