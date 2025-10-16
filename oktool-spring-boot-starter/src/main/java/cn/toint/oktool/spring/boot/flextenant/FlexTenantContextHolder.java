package cn.toint.oktool.spring.boot.flextenant;

import cn.hutool.v7.core.array.ArrayUtil;
import cn.hutool.v7.core.collection.CollUtil;
import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * mybatis-flex租户上下文
 *
 * @author Toint
 * @dete 2025/10/12
 */
public class FlexTenantContextHolder {

    private static final TransmittableThreadLocal<List<Object>> TENANT_IDS_TTL = new TransmittableThreadLocal<>();

    /**
     * 设置租户ID
     *
     * @param tenantIds 租户ID(自动过滤null元素)
     */
    protected static void setTenantIds(Object[] tenantIds) {
        if (ArrayUtil.isEmpty(tenantIds)) {
            clear();
        } else {
            // List.of()不允许存在null元素, 而Arrays.asList()允许.
            setTenantIds(Arrays.asList(tenantIds));
        }
    }

    /**
     * 设置租户ID
     *
     * @param tenantIds 租户ID(自动过滤null元素)
     */
    protected static void setTenantIds(List<Object> tenantIds) {
        if (CollUtil.isEmpty(tenantIds)) {
            clear();
        } else {
            List<Object> list = new ArrayList<>(tenantIds);
            list.removeIf(Objects::isNull);
            TENANT_IDS_TTL.set(list);
        }
    }

    /**
     * 获取租户ID
     *
     * @return 租户ID(可能返回null, 但元素不会为null)
     */
    protected static List<Object> getTenantIds() {
        return TENANT_IDS_TTL.get();
    }

    /**
     * 清空租户ID
     */
    protected static void clear() {
        TENANT_IDS_TTL.remove();
    }
}
