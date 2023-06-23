package com.myweb.www.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.myweb.www.service.MemberService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Setter
	@Getter
	private String authEmail;
	
	@Setter
	@Getter
	private String authUrl;
	
	private RedirectStrategy rdStg = new DefaultRedirectStrategy();
	
	private RequestCache reqCache = new HttpSessionRequestCache();
	
	@Inject
	private MemberService msv;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//authEmail, authUrl 설정
		setAuthEmail(authentication.getName());
		setAuthUrl("/board/list");
		
		boolean isUp = msv.updateLastLogin(getAuthEmail());
		
		//내부에서 이미 로그인 세션 저장됨.
		HttpSession ses = request.getSession();
		log.debug(">>>>> ses "+ ses.toString());
		if(!isUp || ses == null) {
			return;
		}else {
			//시큐리티에서 시도한 인증 실패 기록 삭제
			ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		SavedRequest saveReq = reqCache.getRequest(request, response);
		
		rdStg.sendRedirect(request, response, 
				(saveReq != null ? saveReq.getRedirectUrl() : getAuthUrl()));

	}

}
