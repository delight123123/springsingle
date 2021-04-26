package com.ikjoo.springsingle.register.model;

import java.sql.Timestamp;

import com.ikjoo.springsingle.common.SearchVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO extends SearchVO{
	private String userid;
	private String userpw;
	private String email1;
	private String email2;
	private String salt;
	private String adminauth;
	private Timestamp outDate;
}
