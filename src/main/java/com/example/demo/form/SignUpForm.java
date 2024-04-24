package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
/**
 * 新規会員登録画面フォーム
 * 
 * @author hagi71011
 */
@Data
public class SignUpForm {
	
	/**ユーザ名*/
	@Length(min = 4, max = 20)
	private String name;
	
	/**パスワード*/
	@Length(min = 8, max = 20)
	
	private String password;
	
	/**誕生日の年*/
	/*@Length(min = 4, max = 4)
	@Max(2024)
	@Min(1900)*/
	private String year;
	
	/**誕生日の月*/
	/*@Length(min = 2, max = 2)
	@Max(12)
	@Min(1)*/
	private String month;
	
	/**誕生日の日*/
	/*@Length(min = 2, max = 2)
	@Max(31)
	@Min(1)*/
	private String day;
	
}
