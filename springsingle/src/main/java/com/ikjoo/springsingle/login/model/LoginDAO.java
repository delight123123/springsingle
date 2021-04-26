package com.ikjoo.springsingle.login.model;

import com.ikjoo.springsingle.register.model.RegisterVO;

public interface LoginDAO {
	String saltByuserid(String userid);
	String pwByuserid(String userid);
	RegisterVO userInfoByuserid(String userid);
	int userPwCg(RegisterVO registerVo);
}
