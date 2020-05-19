package com.Kcompany.Kboard.common.paging;

import org.springframework.stereotype.Component;

@Component
public class IBoardPageCriteria {
	
	// 현재 페이지 번호
	private int page;            
	
	// 페이지당 출력되는 게시글의 수 (Limit pageStart, perPageNum)
	// 현재 페이지부터 perPageNum만큼의 게시글 수가 노출
	private int perPageNum;      
	
	// 쿼리문에 사용할 페이지 시작번호  (Limit pageStart, perPageNum)
	private int pageStart;		
	
	
	
	// 생성자를 이용하여 초기 설정을 해준다 (1페이지, 9개의 게시글)
	public IBoardPageCriteria() {
		
		this.page = 1;           // 현재 페이지 default 값
		this.perPageNum = 9;    // 페이지당 출력 게시글 수 default 값
		
	}
	public final int getPage() {
		return page;
	}
	// 현재 페이지가 0보다 작은 정수면 1로 지정
	// 매개변수로 가져온 페이지를 현재페이지에 저장
	public final void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}
	public final int getPerPageNum() {
		return perPageNum;
	}
	public final void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	// b_index Limit(pageStart, perPageNum) 에서 perPageNum은 10으로 고정
	// pageStart에 들어간 index부터 9개씩 표출
	// ex) 현재 페이지가 2라면 pageStart는 9, 현재 페이지가 3이라면 pageStart는 18 ...  
	public final void setPageStart() {
		this.pageStart = (this.page - 1) * this.perPageNum;
	}
	public int getPageStart() {
		return pageStart;
	}

}
