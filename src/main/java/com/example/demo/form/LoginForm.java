package com.example.demo.form;

import lombok.Data;
/**
 * ログイン画面フォーム
 * 
 * @author hagi71011
 */
@Data
public class LoginForm {
	/**ユーザ名*/
	private String name;
	/**パスワード*/
	private String password;
}
