package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")	// assets이 못들어오게
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private FileUploadService fileUploadService;
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
		@PathVariable("pathNo2") Optional<Long> pathNo2,
		Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		BlogVo blogvo = blogService.findById(id);
		model.addAttribute("blogVo", blogvo);
		
		List<CategoryVo> categoryList = categoryService.findByBlogId(id);
		model.addAttribute("categoryList", categoryList);
		
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		} else {
			categoryNo = categoryList.get(0).getNo();
		}
		
		
		PostVo post = postService.findByNo(id, categoryNo, postNo);
		model.addAttribute("post", post);
		
		List<PostVo> postList = postService.findByNoAndNo(id, categoryNo);
		model.addAttribute("postList", postList);

		
//		System.out.println("id: " + id);
//		System.out.println("category: " + categoryNo);
//		System.out.println("post: " + postNo);
		
		return "/blog/main";
	}
	
	// /{id}/admin/basic
	// /kickscar/admin/basic
//	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
//	public String adminBasic(@PathVariable("id") String id) {
//		return "blog/admin/basic";
//	}

	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, BlogVo blogVo, Model model) {
		blogVo = blogService.findById(id);
		model.addAttribute(blogVo);

		return "/blog/admin/basic";
	}
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String adminBasic(@PathVariable("id") String id, BlogVo blogVo, UserVo userVo, MultipartFile logoFile) {
		String logo = fileUploadService.restore(logoFile);
		blogVo.setId(userVo.getId());
		blogVo.setLogo(logo);
		blogService.update(blogVo);
		return "redirect:/" + id;
	}
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable("id") String id, CategoryVo categoryVo, Model model) {
		List<CategoryVo> list = categoryService.findByBlogId(id);
		model.addAttribute("list", list);
		BlogVo blogvo = blogService.findById(id);
		model.addAttribute("blogVo", blogvo);

		return "/blog/admin/category";
	}
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(@PathVariable("id") String id, CategoryVo categoryVo, BlogVo blogVo) {
		categoryVo.setBlogId(blogVo.getId());
		categoryService.insert2(categoryVo);

		return "redirect:/" + id + "/admin/category";
	}
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Long no, Model model) {
		List<CategoryVo> list = categoryService.findByBlogId(id);
		model.addAttribute("list", list);
		BlogVo blogvo = blogService.findById(id);
		model.addAttribute("blogVo", blogvo);

		
		return "/blog/admin/write";
	}
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo postVo, CategoryVo categoryVo) {
		postService.insert(postVo);
		
		return "redirect:/{id}";
		
	}
	@Auth
	@RequestMapping(value="/admin/category/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id, @PathVariable("no") Long no, CategoryVo categoryVo) {
		categoryService.delete(categoryVo.getNo());
		return "redirect:/" + id + "/admin/category";
	}

}
	
	

