package com.ikjoo.springsingle.board.model;

import java.sql.Timestamp;

import com.ikjoo.springsingle.common.SearchVO;

public class ReboardVO extends SearchVO{
	private int reboardNo;
	private String reboardTitle;
	private String reboardContent;
	private Timestamp reboardReg;
	private int readcount;
	private int groupno;
	private int step;
	private int sortno;
	private String delflag;
	private String userid;
	private int newImgTerm;
	public int getReboardNo() {
		return reboardNo;
	}
	public void setReboardNo(int reboardNo) {
		this.reboardNo = reboardNo;
	}
	public String getReboardTitle() {
		return reboardTitle;
	}
	public void setReboardTitle(String reboardTitle) {
		this.reboardTitle = reboardTitle;
	}
	public String getReboardContent() {
		return reboardContent;
	}
	public void setReboardContent(String reboardContent) {
		this.reboardContent = reboardContent;
	}
	public Timestamp getReboardReg() {
		return reboardReg;
	}
	public void setReboardReg(Timestamp reboardReg) {
		this.reboardReg = reboardReg;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getSortno() {
		return sortno;
	}
	public void setSortno(int sortno) {
		this.sortno = sortno;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getNewImgTerm() {
		return newImgTerm;
	}
	public void setNewImgTerm(int newImgTerm) {
		this.newImgTerm = newImgTerm;
	}
	@Override
	public String toString() {
		return "ReboardVO [reboardNo=" + reboardNo + ", reboardTitle=" + reboardTitle + ", reboardContent="
				+ reboardContent + ", reboardReg=" + reboardReg + ", readcount=" + readcount + ", groupno=" + groupno
				+ ", step=" + step + ", sortno=" + sortno + ", delflag=" + delflag + ", userid=" + userid
				+ ", newImgTerm=" + newImgTerm + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
