package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.form.LoginForm;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面　コントローラー
 * 
 * @author hagi71011
 * 
 */
@Controller
@RequiredArgsConstructor
public class LoginController {
	/** セッション情報*/
	//private final HttpSession session;
	
	private final MessageSource messageSource;

	/**
	 * 初期表示
	 * 
	 * @param model　モデル
	 * @param form　入力情報
	 * @return 表示画面
	 */
	@GetMapping(UrlConst.LOGIN)
	public String view(Model model, LoginForm form) {
		return "login";
	}
	
	/**
	 * ログインエラー画面表示
	 * 
	 * @param model　モデル
	 * @param form　入力情報
	 * @return 表示画面
	 */
	@GetMapping(value = UrlConst.LOGIN, params = "error")
	public String viewWithError(Model model, LoginForm form) {
		/*var errorInfo = (Exception)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute("errorMsg", errorInfo.getMessage());*/
		model.addAttribute("isError", true);
		model.addAttribute("errorMsg", AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT));
		return "login";
	}
}
