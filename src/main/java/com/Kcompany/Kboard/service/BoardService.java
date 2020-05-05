package com.Kcompany.Kboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.dao.BoardDAO;
import com.Kcompany.Kboard.vo.BoardVO;

@Service
public class BoardService implements IBoardService{

	@Autowired
	BoardDAO dao;
	
	
	public List<BoardVO> listAll(BoardPageCriteria pc){
		List<BoardVO> list = dao.list(pc);
		
		return list;
	}
	
	
	public int totalCount() {

		int result = dao.total();
		return result;
	}
	
	
	//view 하면 무조건 조회수가 같이 올라가게...!!!
	public BoardVO view(int index) {
		
		BoardVO boardVO = null;
		int result = 0;
		
		// 지금 누른 게시판 글 을 가지고 오고, 
		boardVO = dao.read(index);
		
		// 지금 누른 게시판 글의 조회수를 늘린다.
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
	
	
	public BoardVO write(BoardVO board, String writer) {
		
		BoardVO boardVO = null;
		int result = dao.insert(board, writer);
		if(result == 0) {
			System.out.println("BoardService : write() Error!!");
		}else {
			boardVO = simpleRead(result);
		}
		
		return boardVO;
	}
	
	
	public int correct(BoardVO board, String writer) {
		
		int result = dao.update(board, writer);
		if(result != 1) {
			System.out.println("BoardService : correct() Error!!");
		}
		return result;
	}
	
	
	public void delete(int b_index) {
		int result = dao.delete(b_index);
		if(result != 1) {
			System.out.println("BoardService : delete() Error!!");
		}
	}
	
	
	public BoardVO recommand(int index) {
		
		BoardVO boardVO = dao.recommandCnt(index);
		return boardVO;
	}
	


}














