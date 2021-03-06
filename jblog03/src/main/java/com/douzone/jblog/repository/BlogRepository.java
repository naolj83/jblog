package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BlogVo> findAll(){
		List<BlogVo> list = sqlSession.selectList("blog.findAll");
		return list;
	}
	
	public void insert(String id) {
		sqlSession.insert("blog.insert", id);
	}
	
	public void update(BlogVo vo) {
		sqlSession.update("blog.update", vo);
	}
	
	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}
}
