package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryVo> findAll(){
		return categoryRepository.findAll();
	}
	
	public List<CategoryVo> findByBlogId(String id) {
		return categoryRepository.findByBlogId(id);
	}
	
	public void insert2(CategoryVo vo) {
		categoryRepository.insert2(vo);
	}
	
	public void delete(Long no) {
		categoryRepository.delete(no);
	}
}
