package com.Kcompany.Kboard.common.paging;


public class BoardPageCriteria {
	
	private int page;            // ���� ������ ��ȣ (Limit ?, perPageNum)
	private int perPageNum;      // �������� ��µǴ� �Խñ��� �� (Limit page, perPageNum)
	private int pageStart;
	
	
	public BoardPageCriteria() {
		
		this.page = 1;           // ���� ������ default ��
		this.perPageNum = 10;     // �������� ��� �Խñ� �� default ��
		
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
	
	public final void setPageStart() {
		this.pageStart = (this.page - 1) * this.perPageNum;
	}

	public int getPageStart() {
		
		return pageStart;
		
	}

}
