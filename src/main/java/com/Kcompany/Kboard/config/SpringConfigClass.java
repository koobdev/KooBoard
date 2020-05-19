package com.Kcompany.Kboard.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer{
	// DispatcherServlet에 매핑할 요청 주소를 셋팅한다.
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	
	// Spring MVC 프로젝트 설정을 위한 클래스를 지정한다.
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletAppContext.class};
	}
	
	// 프로젝트에서 사용할 Bean들을 정의기 위한 클래스를 지정한다.
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootAppContext.class};
	}
	
	// 파라미터 인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter};
	}

	// Registration을 커스터마이징 하는 함수
	// ServletAppContext.java에서 등록해준 MultipartResolver의 객체를 생성한다.
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
		super.customizeRegistration(registration);
		
		// 첫번째 매개변수 : 클라이언트가 보낸 파일데이터를 저장하는 임시파일의 경로 , null하면 임시폴더로 설정됨
		// 두번째 매개변수 : 업로드하는 최대 용량 (바이트 단위로 50MB로 지정)
		// 세번째 매개변수 : 파일데이터를 포함한 전체 요청정보의 최대용량 (바이트 단위로 100MB로 지정)
		// 네번째 매개변수 : 파일의 임계값
		MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 104857600, 0);
		registration.setMultipartConfig(config1);
	}
	
}

