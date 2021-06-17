package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(PostVo vo) {
		sqlSession.insert("post.insert", vo);
	}
	
	public List<PostVo> findAll(){
		return sqlSession.selectList("post.findAll");
	}
	
	public List<PostVo> findByNo(Long no){
		return sqlSession.selectList("post.findByNo", no);
	}
}
