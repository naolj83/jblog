package com.douzone.jblog.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String join() {
		return "user/join";
	}

//	@RequestMapping(value="/join", method=RequestMethod.POST)
//	public String join(HttpServletRequest request) {
//		request.getParameter("name");
//		return "user/join";
//	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo vo) {
		userService.join(vo);
		System.out.println(vo);
		return "user/joinsuccess";
	}


	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute UserVo vo, HttpServletRequest request) {
		UserVo userVo = userService.getUser(vo.getId(), vo.getPassword());
		if(userVo == null) {
			return "user/login";
		} 
		// session 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", userVo);
		
		return "redirect:/";
	}
	

	
	
	@RequestMapping(value="logout")
	public String logout() {
		return "redirect:/";
	}
}
