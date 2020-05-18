package com.Kcompany.Kboard.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.common.paging.IBoardPageCriteria;
import com.Kcompany.Kboard.dao.IBoardDAO;
import com.Kcompany.Kboard.dao.IReplyDAO;
import com.Kcompany.Kboard.vo.IBoardVO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class IBoardService{
	
	@Value("${path.upload}")
	private String upload;
	
	@Autowired
	private IBoardDAO dao;
	
	@Autowired
	private IReplyDAO dao2;
	
	
	// 업로드 파일을 저장하는 메소드
	private String saveUploadFile(MultipartFile file) {
		
		// 이름이 똑같은 파일은 덮여쓰여지기 때문에 파일이름 앞에 시간을 붙여준다. 
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		
		try {
			file.transferTo(new File(upload + "/" + fileName));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	
	public List<IBoardVO> listAll(IBoardPageCriteria pc){
		List<IBoardVO> list = dao.list(pc);
		return list;
	}
	
	
	public int totalCount() {
		int result = dao.total();
		return result;
	}
	
	
	public IBoardVO view(int index) {
		IBoardVO boardVO = null;
		int result = 0;
		
		boardVO = dao.read(index);
		
		result = dao.hitCnt(boardVO);
		if(result != 1) {
			System.out.println("BoardService : view() Error!!!");
		}
		return boardVO;
	}
	
	
	
	public IBoardVO simpleRead(int index) {
		IBoardVO boardVO = dao.read(index);
		return boardVO;
	}
	
	
	public IBoardVO write(IBoardVO board) {
		
		MultipartFile uploadFile = board.getUploadFile();
		
		IBoardVO boardVO = null;
		// 최근 index
		int recindex = dao.recentIndex();
		board.setB_index(++recindex);
		
		// 파일을 첨부했으면 파일을 업로드하고 board객체에 파일이름을 담으면서 디비에도 저장할 준비
		if(board.getUploadFile().getSize() > 0) {
			String fileName = saveUploadFile(uploadFile);
			board.setB_fileName(fileName);
		}
		int result = dao.insert(board);
		
		if(result == 0) {
			System.out.println("BoardService : write() Error!!");
		}else {
			boardVO = simpleRead(board.getB_index());
		}
		
		return boardVO;
	}
	
	
	public int correct(IBoardVO board) {
		
		MultipartFile uploadFile = board.getUploadFile();
		
		// 파일을 첨부했으면 파일을 업로드하고 board객체에 파일이름을 담으면서 디비에도 저장할 준비
		if(board.getUploadFile().getSize() > 0) {
			String fileName = saveUploadFile(uploadFile);
			board.setB_fileName(fileName);
		}
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














