package com.douzone.jblog.vo;

public class PostVo {

	private Long no;
	private String title;
	private String contens;
	private String regDate;
	private Long categoryNo;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContens() {
		return contens;
	}
	public void setContens(String contens) {
		this.contens = contens;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", contens=" + contens + ", regDate=" + regDate
				+ ", categoryNo=" + categoryNo + "]";
	}
	
	
	
}
