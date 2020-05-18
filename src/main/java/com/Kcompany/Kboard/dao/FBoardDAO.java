package com.Kcompany.Kboard.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.mapper.FreeBoardMapper;
import com.Kcompany.Kboard.vo.FBoardVO;

@Repository
public class FBoardDAO {

	@Autowired
	private FreeBoardMapper boardMapper;
	
	public List<FBoardVO> list(BoardPageCriteria pc){
		pc.setPageStart();
		return boardMapper.list(pc);
	}
	
	public int total() {
		return boardMapper.total();
	}
	
	public FBoardVO read(int index) {
		return boardMapper.read(index);
	}

	public int hitCnt(FBoardVO board) {
		return boardMapper.hitCnt(board);
	}
	
	public int recentIndex() {
		return boardMapper.recentIndex();
	}

	public int insert(FBoardVO board) {
		return boardMapper.insert(board);
	}

	public int update(FBoardVO board) {
		return boardMapper.update(board);
	}

	public int delete(int b_index) {
		return boardMapper.delete(b_index);
	}

	public int recommendCnt(int index) {
		return boardMapper.recommendCnt(index);
	}

}
