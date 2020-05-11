package com.Kcompany.Kboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.dao.BoardDAO;
import com.Kcompany.Kboard.dao.ReplyDAO;
import com.Kcompany.Kboard.vo.BoardVO;

@Service
public class BoardService{
	
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private ReplyDAO dao2;
	
	
	public List<BoardVO> listAll(BoardPageCriteria pc){
		List<BoardVO> list = dao.list(pc);
		return list;
	}
	
	
	public int totalCount() {
		int result = dao.total();
		return result;
	}
	
	
	public BoardVO view(int index) {
		BoardVO boardVO = null;
		int result = 0;
		
		boardVO = dao.read(index);
		
		result = dao.hitCnt(boardVO);
		if(result != 1) {
			System.out.println("BoardService : view() Error!!!");
		}
		return boardVO;
	}
	
	
	
	public BoardVO simpleRead(int index) {
		BoardVO boardVO = dao.read(index);
		return boardVO;
	}
	
	
	public BoardVO write(BoardVO board) {
		
		BoardVO boardVO = null;
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
	
	
	public int correct(BoardVO board) {
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
	
	
	public void recommand(int index) {
		int result = dao.recommandCnt(index);
		if(result != 1) {
			System.out.println("BoardService : recommand() Error!!");
		}
	}
	


}














