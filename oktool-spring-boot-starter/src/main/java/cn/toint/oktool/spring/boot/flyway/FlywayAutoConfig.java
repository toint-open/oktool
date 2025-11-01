package cn.toint.oktool.spring.boot.flyway;

import cn.toint.oktool.spring.boot.flyway.service.FlywayService;
import com.mybatisflex.core.datasource.FlexDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 数据库版本控制
 *
 * @author Toint
 * @see FlywayService
 * @since 2025/10/11
 */
@AutoConfiguration
@ConditionalOnClass({FlexDataSource.class, Flyway.class})
public class FlywayAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public FlywayService flywayService() {
        return new FlywayService();
    }

}
