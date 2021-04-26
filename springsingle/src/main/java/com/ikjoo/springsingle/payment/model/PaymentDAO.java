package com.ikjoo.springsingle.payment.model;

import java.util.List;
import java.util.Map;

public interface PaymentDAO {
	int paymentInsert(PaymentVO paymentVo);
	List<Map<String, Object>> paymentSearch(PaymentVO paymentVo);
	int paymentSearchTotal(PaymentVO paymentVo);
	PaymentVO paymentSelByimp(String impUid);
	PaymentVO paymentSelByNo(int paymentNo);
}
