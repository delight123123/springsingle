package com.ikjoo.springsingle.refund.model;

import java.util.List;
import java.util.Map;

public interface RefundDAO {
	int refundInsert(RefundVO refundVo);
	List<Map<String, Object>> refundSearch(RefundVO refundVo);
	int refundSearchTotal(RefundVO refundVo);
	int refundCancel(int refundNo);
	RefundVO refundSelByno(int refundNo);
	int refundUpdate(int refundNo);
}
