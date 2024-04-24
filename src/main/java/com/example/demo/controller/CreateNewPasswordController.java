package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.form.NewPasswordForm;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.CreateNewPasswordService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;
/**
 * パスワード更新画面　コントローラー
 * @author hagi71011
 */
@Controller
@RequiredArgsConstructor
public class CreateNewPasswordController {
	
	/**メッセージソース*/
	private final MessageSource messageSource;
	
	/**ユーザ情報テーブル レポジトリー*/
	private final UserInfoRepository repository;

	/**パスワード更新画面サービス*/
	private final CreateNewPasswordService service;
	
	/**
	 * 初期表示
	 * 
	 * @param model　モデル
	 * @param form　入力画面
	 * @return　表示画面
	 */
	@GetMapping(UrlConst.CREATE_NEW_PASSWORD)
	public String view(Model model, NewPasswordForm form) {
		return "createNewPassword";
	}
	/**
	 * パスワード入力が正しければ、マップ画面へ移動
	 * 
	 * @param model　モデル
	 * @param form　入力画面
	 * @return　表示画面
	 */
	//.orElseThrow(() -> new UsernameNotFoundException(form.getName()))
	@PostMapping(UrlConst.CREATE_NEW_PASSWORD)
	public String newPassword(Model model, NewPasswordForm form) {
		var userInfo = repository.findById(form.getName());
		var isCorrectUserAuth = !userInfo.isEmpty()
				&& (form.getYear() + "/" + form.getMonth() + "/" +form.getDay()).equals(userInfo.get().getBirthday())
				&& form.getFirst().equals(form.getSecond());
				
		if(isCorrectUserAuth) {
			repository.save(service.updateUserPassword(userInfo.get(), form.getFirst()));
			return "redirect:/toppage";
		}else {
			model.addAttribute("isError", true);
			var errorMsg = AppUtil.getMessage(messageSource, MessageConst.FORM_ERROR);
			model.addAttribute("errorMsg", errorMsg);
			return "createNewPassword";
		}
	}
}
