package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(String blogId) {
		sqlSession.insert("category.insert", blogId);
	}
	
	public List<CategoryVo> findAll(){
		List<CategoryVo> list = sqlSession.selectList("category.findAll");
		return list;
	}
	
	public void insert2(CategoryVo vo) {
		sqlSession.insert("category.insert2", vo);
	}
	
	public List<CategoryVo> findByBlogId(String id) {
		return sqlSession.selectList("category.findByBlogId", id);
	}
}
