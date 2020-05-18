package com.Kcompany.Kboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.dao.FBoardDAO;
import com.Kcompany.Kboard.dao.FReplyDAO;
import com.Kcompany.Kboard.vo.FBoardVO;

@Service
public class FBoardService{
	
	
	@Autowired
	private FBoardDAO dao;
	
	@Autowired
	private FReplyDAO dao2;
	
	
	public List<FBoardVO> listAll(BoardPageCriteria pc){
		List<FBoardVO> list = dao.list(pc);
		return list;
	}
	
	
	public int totalCount() {
		int result = dao.total();
		return result;
	}
	
	
	public FBoardVO view(int index) {
		FBoardVO boardVO = null;
		int result = 0;
		
		boardVO = dao.read(index);
		
		result = dao.hitCnt(boardVO);
		if(result != 1) {
			System.out.println("BoardService : view() Error!!!");
		}
		return boardVO;
	}
	
	
	
	public FBoardVO simpleRead(int index) {
		FBoardVO boardVO = dao.read(index);
		return boardVO;
	}
	
	
	public FBoardVO write(FBoardVO board) {
		
		FBoardVO boardVO = null;
		int recindex = dao.recentIndex();
		board.setB_index(++recindex);
		int result = dao.insert(board);
		
		if(result == 0) {
			System.out.println("BoardService : write() Error!!");
		}else {
			boardVO = simpleRead(board.getB_index());
		}
		
		return boardVO;
	}
	
	
	public int correct(FBoardVO board) {
		int result = dao.update(board);
		if(result != 1) {
			System.out.println("BoardService : correct() Error!!");
		}
		return result;
	}
	
	
	public void delete(int b_index) {
		
		int forein = dao2.deleteBoard(b_index);
		int result = dao.delete(b_index);
		if(forein != 1 && result != 1) {
			System.out.println("BoardService : delete() Error!!");
		}
	}
	
	
	public void recommend(int index) {
		int result = dao.recommendCnt(index);
		if(result != 1) {
			System.out.println("BoardService : recommend() Error!!");
		}
	}
	


}














