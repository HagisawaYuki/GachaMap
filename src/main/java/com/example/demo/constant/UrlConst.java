package com.example.demo.constant;
/**
 * URL 定数クラス
 * 
 * @author hagi71011
 */
public class UrlConst {
	/**トップページ画面*/
	public static final String TOPPAGE = "/toppage"; 
	/**ログイン画面*/
	public static final String LOGIN = "/login";
	/**新規会員登録画面*/
	public static final String SIGNUP = "/signUp";
	/**マップ画面*/
	public static final String MAP = "/map";
	/**新規パスワード入力画面*/
	public static final String CREATE_NEW_PASSWORD = "/createNewPassword";
	/**認証不要画面*/
	public static final String[] NO_AUTHENTICATION = {TOPPAGE, LOGIN, SIGNUP, CREATE_NEW_PASSWORD};
}
