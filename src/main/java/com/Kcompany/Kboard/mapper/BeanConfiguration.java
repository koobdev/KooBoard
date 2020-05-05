package com.Kcompany.Kboard.mapper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName("com.mysql.cj.jdbc.Driver");
		source.setUrl("jdbc:mysql://localhost:3306/spring_db?serverTimezone=UTC");
		source.setUsername("root");
		source.setPassword("root");
		return source;
	}
	
	// SqlSessionFactory : jdbc를 처리하는 객체
	@Bean
	public SqlSessionFactory factory(BasicDataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		SqlSessionFactory factory = factoryBean.getObject();
		
		return factory;		
	}
	
	// BoardMapper
	@Bean
	public MapperFactoryBean<BoardMapperInterface> boardMapper(SqlSessionFactory factory) throws Exception{
		MapperFactoryBean<BoardMapperInterface> factoryBean = 
				new MapperFactoryBean<BoardMapperInterface>(BoardMapperInterface.class);
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	
}















