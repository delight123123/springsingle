package com.ikjoo.springsingle.refund.model;

import java.sql.Timestamp;

import com.ikjoo.springsingle.common.SearchVO;

public class RefundVO extends SearchVO{
	private int refundNo;
	private String refundType;
	private int refundPrice;
	private Timestamp reportingDate;
	private String refundState;
	private Timestamp refundDate;
	private int paymentNo;
	private String refundReason;
	public int getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(int refundNo) {
		this.refundNo = refundNo;
	}
	public String getRefundType() {
		return refundType;
	}
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}
	public int getRefundPrice() {
		return refundPrice;
	}
	public void setRefundPrice(int refundPrice) {
		this.refundPrice = refundPrice;
	}
	public Timestamp getReportingDate() {
		return reportingDate;
	}
	public void setReportingDate(Timestamp reportingDate) {
		this.reportingDate = reportingDate;
	}
	public String getRefundState() {
		return refundState;
	}
	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}
	public Timestamp getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Timestamp refundDate) {
		this.refundDate = refundDate;
	}
	public int getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	@Override
	public String toString() {
		return "RefundVO [refundNo=" + refundNo + ", refundType=" + refundType + ", refundPrice=" + refundPrice
				+ ", reportingDate=" + reportingDate + ", refundState=" + refundState + ", refundDate=" + refundDate
				+ ", paymentNo=" + paymentNo + ", refundReason=" + refundReason + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
