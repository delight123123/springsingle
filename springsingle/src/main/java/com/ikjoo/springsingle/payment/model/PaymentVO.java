package com.ikjoo.springsingle.payment.model;

import java.sql.Timestamp;

import com.ikjoo.springsingle.common.SearchVO;

public class PaymentVO extends SearchVO{
	private int paymentNo;
	private String ordername;
	private String impUid;
	private int price;
	private Timestamp payment_reg;
	private String userid;
	public int getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getOrdername() {
		return ordername;
	}
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	public String getImpUid() {
		return impUid;
	}
	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Timestamp getPayment_reg() {
		return payment_reg;
	}
	public void setPayment_reg(Timestamp payment_reg) {
		this.payment_reg = payment_reg;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "PaymentVO [paymentNo=" + paymentNo + ", ordername=" + ordername + ", impUid=" + impUid + ", price="
				+ price + ", payment_reg=" + payment_reg + ", userid=" + userid + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}

