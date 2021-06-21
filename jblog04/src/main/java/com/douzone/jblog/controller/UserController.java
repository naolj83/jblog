package com.douzone.jblog.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Validated UserVo userVo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();		// ObjectError는 vo의 에러
			 //@ModelAttribute 어노테이션이 없을 때, spring은 코드 싫어하고 선언적인 거 좋아함.
			model.addAllAttributes(result.getModel());			// jsp에서 ${map.a }아니라 ${a } 이렇게만 써도 됨
			return "user/join";									// model이 jsp로 보내줌
			
	}
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

//	@RequestMapping(value="/join", method=RequestMethod.POST)
//	public String join(HttpServletRequest request) {
//		request.getParameter("name");
//		return "user/join";
//	}
	
//	@RequestMapping(value="/join", method=RequestMethod.POST)
//	public String join(@ModelAttribute @Validated UserVo vo, BindingResult result, Model model) {
//		userService.join(vo);
//		
//		System.out.println(vo);
//		return "user/joinsuccess";
//	}


	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(UserVo vo, HttpServletRequest request) {
//		UserVo userVo = userService.getUser(vo.getId(), vo.getPassword());
//		System.out.println(userVo);
//		if(userVo == null) {
//			return "user/login";
//		} 
//		// session 처리
//		HttpSession session = request.getSession(true);
//		session.setAttribute("authUser", userVo);
//		return "redirect:/";
//	}
	
	
	
//	@RequestMapping(value="logout")
//	public String logout() {
//		return "redirect:/";
//	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsucceess() {
		return "user/joinsuccess";
	}
}
