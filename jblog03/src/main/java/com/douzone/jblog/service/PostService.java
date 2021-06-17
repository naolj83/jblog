package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;
@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public List<PostVo> findAll(){
		return postRepository.findAll();
	}
	
	public List<PostVo> findByNo(Long no){
		return postRepository.findByNo(no);
	}
	
	public void insert(PostVo vo) {
		postRepository.insert(vo);
	}
}
