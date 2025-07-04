package cn.toint.oktool.spring.boot.config.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Toint
 * @date 2025/7/4
 */
@ConfigurationProperties("oktool.cache")
@Data
public class CacheProperties {
    /**
     * 缓存类型
     */
    private Type type = Type.AUTO;

    public enum Type {
        AUTO, LOCAL, REDIS
    }

}
