package com.Kcompany.Kboard.common.paging;

import org.springframework.beans.factory.annotation.Autowired;

public class ReplyPaging {
	
	@Autowired
	ReplyPageCriteria pc;
	
	private int totalCount;
	private int startNum;    
	private int endNum;      
	private boolean prev;
	private boolean next;
	private int displayPageNum = 5;
	private int tempEndPage;
	
	
	
	public void calcData() {
		
		endNum = (int) (Math.ceil(pc.getPage() / (double) displayPageNum) * displayPageNum);
		startNum = (endNum - displayPageNum) + 1;
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pc.getPerPageNum()));
		this.tempEndPage = tempEndPage;

		if (endNum > tempEndPage) {
			endNum = tempEndPage;
		}

		
		prev = startNum == 1 ? false : true;
		next = endNum * pc.getPerPageNum() >= totalCount ? false : true;
	}
	
	public final ReplyPageCriteria getPc() {
		return pc;
	}
	public final void setPaging(ReplyPageCriteria pc) {
		this.pc = pc;
	}
	public final int getTotalCount() {
		return totalCount;
	}
	public final void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	public final int getStartNum() {
		return startNum;
	}
	public final void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public final int getEndNum() {
		return endNum;
	}
	public final void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public final boolean isPrev() {
		return prev;
	}
	public final void setPrev(boolean prev) {
		this.prev = prev;
	}
	public final boolean isNext() {
		return next;
	}
	public final void setNext(boolean next) {
		this.next = next;
	}
	public final int getDisplayPageNum() {
		return displayPageNum;
	}
	public final void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public final int getTempEndPage() {
		return tempEndPage;
	}
	public final void setTempEndPage(int tempEndPage) {
		this.tempEndPage = tempEndPage;
	}

	
	
	
}
