package com.Kcompany.Kboard.common.paging;

public class BoardPageCriteria {
	
	private int page;            // 현재 페이지 번호 (Limit ?, perPageNum)
	private int perPageNum;      // 페이지당 출력되는 게시글의 수 (Limit page, perPageNum)
	
	
	public BoardPageCriteria() {
		
		this.page = 1;           // 현재 페이지 default 값
		this.perPageNum = 10;     // 페이지당 출력 게시글 수 default 값
		
	}
	
	public final int getPage() {
		return page;
	}
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
	
	public int getPageStart() {
		
		return (this.page - 1) * this.perPageNum;
		
	}

}
