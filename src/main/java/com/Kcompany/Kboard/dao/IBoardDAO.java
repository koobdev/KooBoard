package com.Kcompany.Kboard.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.Kcompany.Kboard.common.paging.IBoardPageCriteria;
import com.Kcompany.Kboard.mapper.ImageBoardMapper;
import com.Kcompany.Kboard.vo.IBoardVO;

@Repository
public class IBoardDAO {

	@Autowired
	private ImageBoardMapper boardMapper;
	
	
	public List<IBoardVO> list(IBoardPageCriteria pc){
		// 쿼리에 들어갈 페이지 시작번호를 가져옴
		pc.setPageStart();
		return boardMapper.list(pc);
	}
	
	public int total() {
		return boardMapper.total();
	}
	
	public IBoardVO read(int index) {
		return boardMapper.read(index);
	}

	public int hitCnt(IBoardVO board) {
		return boardMapper.hitCnt(board);
	}
	
	public int recentIndex() {
		return boardMapper.recentIndex();
	}

	public int insert(IBoardVO board) {
		return boardMapper.insert(board);
	}

	public int update(IBoardVO board) {
		return boardMapper.update(board);
	}

	public int delete(int b_index) {
		return boardMapper.delete(b_index);
	}

	public int recommandCnt(int index) {
		return boardMapper.recommandCnt(index);
	}

}
