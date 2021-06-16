package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/{id:(?!assets).*}")	// assets이 못들어오게
public class BlogController {
/**
	// /{id}
	// /kickscar
	@ResponseBody
	@RequestMapping("")
	public String index(@PathVariable("id") String id) {
		System.out.println("id: " + id);
		return "BlogController.index";
	}
*/	
	// /{id}/{categoryNo}/{postNo}
	// /kickscar/1/10
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
		@PathVariable("id") String id, 
		@PathVariable("pathNo1") Optional<Long> pathNo1, 
		@PathVariable("pathNo2") Optional<Long> pathNo2) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		} 
		
		System.out.println("id: " + id);
		System.out.println("category: " + categoryNo);
		System.out.println("post: " + postNo);
		
		return "/blog/main";
	}
	
	// /{id}/admin/basic
	// /kickscar/admin/basic
	@ResponseBody
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		System.out.println(id);
		return "BlogController.adminBasic";
	}

}
