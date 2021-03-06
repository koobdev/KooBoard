package com.Kcompany.Kboard.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring MVC 프로젝트에 관련된 설정을 하는 클래스
@Configuration
// Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다.
@EnableWebMvc
// 스캔할 패키지를 지정한다.
@ComponentScan("com.Kcompany.Kboard")
@ComponentScan("com.Kcompany.Kboard.common.paging")
@ComponentScan("com.Kcompany.Kboard.controller")
@ComponentScan("com.Kcompany.Kboard.service")
@ComponentScan("com.Kcompany.Kboard.dao")
// 스캔할 매퍼를 지정한다.
@MapperScan("com.Kcompany.Kboard.mapper")
// 프로퍼티의 변수를 사용하기 위하여 ProperySource를 등록해준다. 
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer{
	
	// db.properties에 정의 되어 있는 값을 가져온다.
	@Value("${db.classname}")
	private String classname;

	@Value("${db.url}")
	private String url;
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	
	// Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	// 정적 파일의 경로를 매핑한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}
	
	// dbcp 커넥션풀. 데이터베이스의 접속 정보를 관리한다.
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(classname);
		source.setUrl(url);
		source.setUsername(username);
		source.setPassword(password);
		
		return source;
	}
	
	// 쿼리문과 접속 관리를 하는 객체이다.
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		
		return factory;
	}
	
	// PropertySource와 메세지로 등록한 properties파일의 충돌을 막기 위해 따로 Bean을 등록하여 관리한다.
	@Bean
	  public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
	// 에러메세지를 jsp에서 띄우기 위해서 error_message.properties를 메세지로 등록한다.
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/WEB-INF/properties/error_message");
		
		return ms;
	}
		
	// 파일데이터를 주입하기 위한 설정
	// 클라이언트 폼에서 설정한 enctype="multipart/form-data"(파일데이터 업로드)를 관리한다.
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
}























