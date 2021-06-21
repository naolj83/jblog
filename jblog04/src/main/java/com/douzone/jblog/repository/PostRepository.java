package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public PostVo findByNo(String id, Long categoryNo, Long postNo){
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryNo", String.valueOf(categoryNo));
		map.put("postNo", String.valueOf(postNo));

		return sqlSession.selectOne("post.findByNo", map);
	}
	
	public List<PostVo> findByNoAndNo(String id, Long categoryNo){
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryNo", String.valueOf(categoryNo));

		return sqlSession.selectList("post.findByNoAndNo", map);
	}
	
}
