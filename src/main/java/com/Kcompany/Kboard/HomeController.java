package com.Kcompany.Kboard;


import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	// 클래스패스를 cp라는 이름으로 반환함으로써 어디서든 사용할 수 있다.
	@ModelAttribute("cp")
	public String classpath(HttpServletRequest request) {
			
		String cp = request.getContextPath();
		return cp;
	}
	
	// home
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		// 실제 이클립스 상의 배포위치
		System.out.println(request.getServletContext().getRealPath("/"));
		
		return "home";
	}
	
}
