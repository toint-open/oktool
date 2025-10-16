package cn.toint.oktool.spring.boot.flextenant;

import com.mybatisflex.core.tenant.TenantFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * mybatis-flex租户工厂
 *
 * @author Toint
 * @dete 2025/10/12
 */
@Slf4j
public class FlexTenantFactory implements TenantFactory {
    @Override
    public Object[] getTenantIds() {
        return getTenantIds(null);
    }

    /**
     * 获取当前上下文的租户ID
     *
     * @param tableName 表名
     * @return 忽略租户条件: 返回 null 或者 空数组
     */
    @Override
    public Object[] getTenantIds(String tableName) {
        // 获取当前上下文的用户ID
        List<Object> tenantIds = FlexTenantContextHolder.getTenantIds();
        if (tenantIds == null) {
            return null;
        } else {
            return tenantIds.toArray();
        }
    }
}
