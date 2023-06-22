package com.myweb.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.scheduling.annotation.EnableScheduling;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages= {"com.myweb.www"})
@MapperScan(basePackages= {"com.myweb.www.repository"})
//@EnableAspectJAutoProxy
//@EnableScheduling  스케줄링 사용시
public class RootConfig {
	
	// 사용자가 직접 작성하면 보통 inject 사용, 원래 있는 것은 Autowired, 어느 걸 써도 상관은 없음
	@Autowired
	ApplicationContext applicationcontext;
	
	// DB 설정
	// log4jdbc-log4j2 라이브러리 사용 시 드라이버 다른 걸로 설정해야 함
	// Driver "net.sf.log4jdbc.sql.jdbcapi.DriverSpy"
	
	// 내가 만들었으면 component 사용, 라이브러리에서 제공하면 Bean 사용
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springtestdb");
		hikariConfig.setUsername("springuser");
		hikariConfig.setPassword("mysql");
		hikariConfig.setMinimumIdle(5);     // 최소 유휴 커넥션 수 -> 성능 향상
		
		hikariConfig.setConnectionTestQuery("SELECT now()");   // test 쿼리
		hikariConfig.setPoolName("springHikariCP");
		
		// 추가되는 부분
		// cache 사용 여부에 대한 설정 => true 쓰겠다
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		// Mysql 드라이브가 연결당 cache할 statement의 수에 관한 설정, 기본 25, 250~500 권장사항
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "200");
		// cache할 sql 구문의 최대 길이
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true");
		// 서버에서 지원받는 설정이 있다면 가능할지에 대한 부분 설정
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		
		HikariDataSource hikaroDataSource = new HikariDataSource(hikariConfig);
		return hikaroDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		// 설정 사항에 대한 경로 설정
		sqlFactoryBean.setConfigLocation(applicationcontext.getResource("classpath:/MybatisConfig.xml"));
		sqlFactoryBean.setMapperLocations(applicationcontext.getResources("classpath:/mappers/*.xml"));
		return (SqlSessionFactory)sqlFactoryBean.getObject();
	}
}
