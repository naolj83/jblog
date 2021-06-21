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
	
	public PostVo findByNo(String id, Long categoryNo, Long postNo){
		return postRepository.findByNo(id, categoryNo, postNo);
	}

	public List<PostVo> findByNoAndNo(String id, Long categoryNo){
		return postRepository.findByNoAndNo(id, categoryNo);
	}
	
	public void insert(PostVo vo) {
		postRepository.insert(vo);
	}
}
