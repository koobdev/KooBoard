package com.Kcompany.Kboard.vo;

import java.sql.Timestamp;

public class FReplyVO {
	
	private int r_index;
	private String r_memId;
	private String r_content;
	private Timestamp r_createDate;
	private int b_index;
	
	
	public final int getR_index() {
		return r_index;
	}
	public final void setR_index(int r_index) {
		this.r_index = r_index;
	}
	public final String getR_memId() {
		return r_memId;
	}
	public final void setR_memId(String r_memId) {
		this.r_memId = r_memId;
	}
	public final String getR_content() {
		return r_content;
	}
	public final void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public final Timestamp getR_createDate() {
		return r_createDate;
	}
	public final void setR_createDate(Timestamp r_createDate) {
		this.r_createDate = r_createDate;
	}
	public final int getB_index() {
		return b_index;
	}
	public final void setB_index(int b_index) {
		this.b_index = b_index;
	}
}
