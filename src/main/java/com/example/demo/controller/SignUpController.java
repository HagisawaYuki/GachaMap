package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.SignUpMessage;
import com.example.demo.constant.UrlConst;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignUpForm;
import com.example.demo.service.SignUpService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ユーザ登録画面　コントローラー
 * 
 * @author hagi71011
 */
@Controller
@RequiredArgsConstructor
public class SignUpController {
	/**ユーザ登録画面　サービス*/
	private final SignUpService service;
	/**メッセージソース*/
	private final MessageSource messageSource;
	/**
	 * 初期表示
	 * 
	 * @param model　モデル
	 * @param form　入力情報
	 * @return　表示画面
	 */
	@GetMapping(UrlConst.SIGNUP)
	public String view(Model model, SignUpForm form) {
		return "signUp";
	}
	/**
	 * ユーザ登録
	 * 
	 * @param model　モデル
	 * @param form　入力情報
	 * @param bdResult　入力チェック結果
	 */
	@PostMapping(UrlConst.SIGNUP)
	public void signUp(Model model, @Validated SignUpForm form, BindingResult bdResult) {
		if(bdResult.hasErrors()) {
			editGuideMessage(model, MessageConst.FORM_ERROR, true);
			return;
		}
			//データベースにユーザ情報を入力
		var userInfoOpt = service.resistUserInfo(form);
		var signUpMessage = judgeMessageKey(userInfoOpt);
		editGuideMessage(model, signUpMessage.getMessageId(), signUpMessage.isError());
	}
	/**
	 * 画面に表示するガイドメッセージを設定する
	 * 
	 * @param model　モデル
	 * @param messageId　メッセージID
	 * @param isError　エラー有無
	 */
	private void editGuideMessage(Model model, String messageId, boolean isError) {
		var message = AppUtil.getMessage(messageSource, messageId);
		model.addAttribute("message", message);
		model.addAttribute("isError", isError);
	}
	
	/**
	 * ユーザ情報登録の結果メッセージキーを判断する
	 * 
	 * @param userInfoOpt　ユーザ登録結果(登録済みだった場合はEmpty)
	 * @return　メッセージキー
	 */
	private SignUpMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if(userInfoOpt.isEmpty()) {
			return SignUpMessage.EXISTED_LOGIN_ID;
		}else {
			return SignUpMessage.SUCCEED;
		}
	}
}
