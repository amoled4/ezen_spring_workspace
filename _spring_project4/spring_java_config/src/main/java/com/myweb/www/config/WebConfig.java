package com.myweb.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		// encoding filter 설정
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");     // 외부에서 들어오는 encoding 설정
		encodingFilter.setForceEncoding(true);   // 나가는 encoding 설정도 같이 하겠다고 옵션 설정
		return new Filter[] {encodingFilter};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 커스터마이징 레지스터 => 파일첨부 업로드 설정할 때 사용
		// throwExceptionIfNotHandlerFound : 존재하지 않는 API 요청의 응답을 커스터마이징
		registration.setInitParameter("throwExceptionIfNotHandlerFound", "true");
		
		// 파일첨부
		String uploadLocation = "D:\\_myweb\\_java\\fileUpload";
		int maxFileSize = 1024*1024*2;        // 2MB
		int maxReqSize = maxFileSize * 5;     // 파일 요청 최대 크기
		int fileSizeThreshold = maxFileSize;  // 파일 전송 시 만들어지는 임시 공간
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadLocation, maxFileSize, maxReqSize, fileSizeThreshold);
		registration.setMultipartConfig(multipartConfigElement);
	}
	
	

}
