//package cn.toint.oktool.spring.boot.flextenant;
//
//import cn.toint.oktool.util.ScopeValueUtil;
//
//import java.util.List;
//
///**
// * mybatis-flex租户上下文
// *
// * @author Toint
// * @since 2025/10/12
// */
//public class FlexTenantContextHolder {
//
//    /**
//     * 租户 ID 列表的 ScopedValue
//     * 使用 List 是为了支持多租户场景（如用户可以访问多个租户的数据）
//     */
//    private static final ScopedValue<List<Object>> TENANT_IDS = ScopedValue.newInstance();
//
//    /**
//     * 获取当前租户 ID 列表
//     *
//     * @return 租户 ID 列表，如果未绑定则返回 null
//     */
//    public static List<Object> getTenantIds() {
//        return ScopeValueUtil.getOrNull(TENANT_IDS);
//    }
//
//    /**
//     * 获取当前主租户 ID（第一个租户 ID）
//     *
//     * @return 主租户 ID，如果未绑定或列表为空则返回 null
//     */
//    public static Object getPrimaryTenantId() {
//        List<Object> tenantIds = getTenantIds();
//        if (tenantIds == null || tenantIds.isEmpty()) {
//            return null;
//        } else {
//            return tenantIds.getFirst();
//        }
//    }
//
//    /**
//     * 获取 ScopedValue 实例（用于在 Filter 或拦截器中绑定值）
//     *
//     * @return TENANT_IDS 的 ScopedValue 实例
//     */
//    public static ScopedValue<List<Object>> getTenantIdsScopedValue() {
//        return TENANT_IDS;
//    }
//}
