package com.Kcompany.Kboard.common.paging;

public class ReplyPageCriteria {

	private int page;         // 현재 페이지 번호 (Limit ?,perPageNum)
	private int perPageNum;   // 페이지당 출력되는 댓글의 수 (Limit page,?)
	
	
	public ReplyPageCriteria() { // Default 값
		this.page = 1;
		this.perPageNum = 5;
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
