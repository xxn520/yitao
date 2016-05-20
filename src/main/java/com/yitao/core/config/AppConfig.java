/**
 * 
 */
package com.yitao.core.config;

import com.yitao.core.Constants;
import com.yitao.core.jpa.DefaultJpaRepository;
import com.yitao.core.security.SpringSecurityAuditorAware;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import java.util.Map;

/**
 * @author iDay
 *
 */
@EnableJpaAuditing
@EntityScan("com.yitao.**.model")
@EnableTransactionManagement(order = 1000)
@SpringBootApplication(scanBasePackages = { "com.yitao" })
@EnableJpaRepositories(basePackages = { "com.yitao.**.dao" }, repositoryBaseClass = DefaultJpaRepository.class)
@EnableScheduling
public class AppConfig {

	@Inject
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public SpringSecurityAuditorAware securityAuditor() {
		return new SpringSecurityAuditorAware();
	}

	@Bean
	public SessionFactory sessionFactory() {
		HibernateJpaSessionFactoryBean factoryBean = new HibernateJpaSessionFactoryBean();
		factoryBean.setEntityManagerFactory(entityManagerFactory);
		return factoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Inject
	private JerseyProperties jerseyProperties;

	@Bean
	public WebConfig webConfig() {
		Map<String, String> map = jerseyProperties.getInit();
		return new WebConfig(map);
	}

}
