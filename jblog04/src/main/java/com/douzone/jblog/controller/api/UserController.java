package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;


@RestController("userControllerApi")	// controller id 설정
@RequestMapping("/user/api")			// RestController 쓰면 RequestBody 안 붙여도 다 메세지다.
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/checkid")
	public JsonResult checkid(@RequestParam(value="id", required=true, defaultValue="") String id) {
		UserVo userVo = userService.getUser(id);
		return JsonResult.success(userVo != null);
	}

}
