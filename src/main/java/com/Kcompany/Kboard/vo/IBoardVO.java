package com.Kcompany.Kboard.vo;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class IBoardVO {
	
	private int b_index;
	
	@NotNull(message = "제목은 필수 입력사항입니다.")
	private String b_title;
	
	@NotNull(message = "내용은 필수 입력사항입니다.")
	private String b_content;
	
	// 데이터베이스에 저장된 이미지 파일의 이름이 저장된 프로퍼티
	private String b_fileName;
	
	// 클라이언트가 요청한 파일 데이터를 담는 프로퍼티
	private MultipartFile uploadFile;
	
	private int b_hit;
	private int b_recommend;
	private int b_replyCount;
	private String b_memId;
	private Timestamp b_createDate;
	
	
	public final int getB_index() {
		return b_index;
	}
	public final void setB_index(int b_index) {
		this.b_index = b_index;
	}
	public final String getB_title() {
		return b_title;
	}
	public final void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public final String getB_content() {
		return b_content;
	}
	public final void setB_content(String b_content) {
		this.b_content = b_content;
	}	
	public final String getB_fileName() {
		return b_fileName;
	}
	public final void setB_fileName(String b_fileName) {
		this.b_fileName = b_fileName;
	}
	public final MultipartFile getUploadFile() {
		return uploadFile;
	}
	public final void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public final int getB_hit() {
		return b_hit;
	}
	public final void setB_hit(int b_hit) {
		this.b_hit = b_hit;
	}
	public final int getB_recommend() {
		return b_recommend;
	}
	public final void setB_recommend(int b_recommend) {
		this.b_recommend = b_recommend;
	}
	public final int getB_replyCount() {
		return b_replyCount;
	}
	public final void setB_replyCount(int b_replyCount) {
		this.b_replyCount = b_replyCount;
	}
	public final String getB_memId() {
		return b_memId;
	}
	public final void setB_memId(String b_memId) {
		this.b_memId = b_memId;
	}	
	public final Timestamp getB_createDate() {
		return b_createDate;
	}
	public final void setB_createDate(Timestamp b_createDate) {
		this.b_createDate = b_createDate;
	}
}
