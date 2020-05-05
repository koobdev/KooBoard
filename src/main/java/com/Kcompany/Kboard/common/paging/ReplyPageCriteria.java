package com.Kcompany.Kboard.common.paging;

public class ReplyPageCriteria {

	private int page;         // ���� ������ ��ȣ (Limit ?,perPageNum)
	private int perPageNum;   // �������� ��µǴ� ����� �� (Limit page,?)
	
	
	public ReplyPageCriteria() { // Default ��
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
