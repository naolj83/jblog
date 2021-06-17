package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	
	public List<BlogVo> findAll(){
		return blogRepository.findAll();
	}

	public void update(BlogVo vo) {
		blogRepository.update(vo);
	}

	public BlogVo findById(String id) {
		return blogRepository.findById(id);
	}
}
