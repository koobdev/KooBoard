package com.Kcompany.Kboard.common.paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IBoardPaging {
	
	@Autowired
	private IBoardPageCriteria pc;
	
	// 총 게시물의 수
	private int totalCount;
	// 페이지 리모컨의 시작 정수
	private int startNum;
	// 페이지 리모컨의 끝 정수
	private int endNum;
	private int tempEndPage;
	// 페이지의 이전 버튼
	private boolean prev;
	// 페이지의 다음 버튼
	private boolean next;
	// 게시글 표출 수(10개로 고정)
	private int displayPageNum = 10;
	       
	
	
	// 페이지의 리모컨의 시작과 끝,이전버튼과 다음버튼을 계산하는 함수
	// 현재 페이지가 1~10이면 endNum은 10, 현재 페이지가 11~20이면 endNum은 20 ...
	// endNum이 10이면 startNum은 1, endNum이 20이면 startNum은 10 ...
	public void calcData() {
		
		endNum = (int) (Math.ceil(pc.getPage() / (double) displayPageNum) * displayPageNum);
		startNum = (endNum - displayPageNum) + 1;
		
		// 게시물 갯수에 맞추어서 endNum 조정
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pc.getPerPageNum()));
		this.tempEndPage = tempEndPage;
		if (endNum > tempEndPage) {
			endNum = tempEndPage;
		}

		// startNum이 1이면 이전버튼이 없음(false)
		prev = startNum == 1 ? false : true;
		// endNum이 게시물을 표현할 마지막 페이지이면 다음버튼이 없음(false)
		next = endNum * pc.getPerPageNum() >= totalCount ? false : true;
	}
	public final IBoardPageCriteria getPc() {
		return pc;
	}
	public final void setPage(IBoardPageCriteria pc) {
		this.pc = pc;
	}
	public final int getTotalCount() {
		return totalCount;
	}
	// 총 게시물의 수를 주입받으면 각 프로퍼티에 들어갈 값을 계산함
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















