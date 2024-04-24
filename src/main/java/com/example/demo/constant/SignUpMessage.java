package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum SignUpMessage {

	SUCCEED(MessageConst.SIGNUP_RESIST_SUCCEED, false),
	EXISTED_LOGIN_ID(MessageConst.SIGNUP_EXISTEDNAME, true);
	
	public String messageId;
	public boolean isError;
}
