package cn.toint.oktool.spring.boot.flextenant;

import cn.toint.oktool.spring.boot.context.OkContext;
import com.mybatisflex.core.tenant.TenantFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * mybatis-flex租户工厂
 *
 * @author Toint
 * @since 2025/10/12
 */
public class FlexTenantFactory implements TenantFactory {

    private static final Logger log = LoggerFactory.getLogger(FlexTenantFactory.class);

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
        List<Object> tenantIds = OkContext.getTenantIds();
        if (tenantIds == null) {
            return null;
        } else {
            return tenantIds.toArray();
        }
    }

    @PostConstruct
    private void init() {
        log.info("TenantFactory-多租户功能已开启");
    }
}
