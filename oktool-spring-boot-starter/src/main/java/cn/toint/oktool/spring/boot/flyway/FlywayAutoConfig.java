package cn.toint.oktool.spring.boot.flyway;

import cn.toint.oktool.spring.boot.flyway.service.FlywayService;
import com.mybatisflex.core.datasource.FlexDataSource;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * 数据库版本控制
 *
 * @author Toint
 * @since 2025/10/11
 * @see FlywayService
 */
@AutoConfiguration
@ConditionalOnClass(FlexDataSource.class)
public class FlywayAutoConfig {

    @Bean
    public FlywayService flywayService() {
        return new FlywayService();
    }

}
