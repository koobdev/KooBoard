package com.Kcompany.Kboard.vo;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberVO {

	@Size(min=6, max=10)
	@Pattern(regexp="[a-zA-Z0-9]*")
	private String memId;
	
	@Size(min=6, max=10)
	@Pattern(regexp="[a-zA-Z0-9]*")
	private String memPw;
	
	@Pattern(regexp="\\w+@\\w+\\.\\w+(\\.\\w+)?")
	private String memMail;
	
	@Pattern(regexp="[0-9]{11}")
	private String memPhone;
	
	private String memTime;
	
	@AssertTrue
	private boolean checkId;
	
	// checkId의 defalut는 false
	public MemberVO() {
		this.checkId = false;
	}
	public final String getMemId() {
		return memId;
	}
	public final void setMemId(String memId) {
		this.memId = memId;
	}
	public final String getMemPw() {
		return memPw;
	}
	public final void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public final String getMemMail() {
		return memMail;
	}
	public final void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public final String getMemPhone() {
		return memPhone;
	}
	public final void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public final String getMemTime() {
		return memTime;
	}
	public final void setMemTime(String memTime) {
		this.memTime = memTime;
	}
	public final boolean isCheckId() {
		return checkId;
	}
	public final void setCheckId(boolean checkId) {
		this.checkId = checkId;
	}
}
