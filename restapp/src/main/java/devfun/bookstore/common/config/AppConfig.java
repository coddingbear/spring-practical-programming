package devfun.bookstore.common.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@EnableTransactionManagement // 애너테이션을 사용한 트랙잭션 관리
@MapperScan("devfun.bookstore.common.mapper")
@Configuration
@ComponentScan(basePackages = {"devfun.bookstore.common.service"}) //, useDefaultFilters = false, includeFilters = {@Filter(Service.class)})
public class AppConfig implements TransactionManagementConfigurer {
	// DataSource 빈 설정
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setName("testdb") // DB 이름 설정
				.setType(EmbeddedDatabaseType.HSQL) // DB 종류 설정
				.addScript("schema.sql") // 스키마 스크립트 추가
				.addScript("data.sql")   // 데이터 스크립트 추가
				.build();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager(); // reference the existing @Bean method above
	}
	

}
