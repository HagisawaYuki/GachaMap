package com.example.demo.form;

import lombok.Data;
/**
 * 新規パスワード入力画面フォーム
 * 
 * @author hagi71011
 */
@Data
public class NewPasswordForm {
	/**ユーザ名*/
	private String name;
	/**誕生日の年*/
	private String year;
	/**誕生日の月*/
	private String month;
	/**誕生日の日*/
	private String day;
	/**最初に入力したパスワード*/
	private String first;
	/**もう一度入力したパスワード*/
	private String second;
}
