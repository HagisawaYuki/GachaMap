package com.example.demo.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.RegisterMarkerForm;
import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.entity.MarkerInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.MapService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * マップ画面 コントローラー
 * 
 * @author hagi71011
 */
@Controller
@RequiredArgsConstructor
public class MapController {
	/** セッション情報*/
	//private final HttpSession session;
	
	private final MapService service;
	
	private final MessageSource messageSource;
		
	/**
	 * 初期表示
	 * 
	 * @param model　モデル
	 * @return　表示画面
	 */
	@GetMapping(UrlConst.MAP)
	public String view(@AuthenticationPrincipal User user, Model model, RegisterMarkerForm form) {
		//エラーメッセージ
		editGuideMessage(model, MessageConst.MAP_FORM_ERROR, false);
		/**データベーステーブルを取得*/
		searchDataBaseTable(model);
		return "map";
	}
	
	/**
	 * マーカー情報を登録
	 * 
	 * @param model　モデル
	 * @param form　入力情報
	 */
	@PostMapping(UrlConst.MAP)
	public void registerMarker(Model model, @Validated RegisterMarkerForm form, BindingResult bdResult, @AuthenticationPrincipal User user) {
		if(bdResult.hasErrors()) {
			//エラーメッセージ
			editGuideMessage(model, MessageConst.MAP_FORM_ERROR, true);
			/**データベーステーブルを取得*/
			searchDataBaseTable(model);
			
			return;
		}
		//エラーメッセージ
		editGuideMessage(model, MessageConst.MAP_FORM_ERROR, false);
		//データベースにユーザ情報を入力
		var userInfoOpt = service.registerMarkerInfo(form, user);
		/**データベーステーブルを取得*/
		searchDataBaseTable(model);
		
		
	}
	
	/**
	 * モデルにメッセージを入れる
	 * 
	 * @param model　モデル
	 * @param messageId　メッセージID
	 * @param isError　エラーかどうか
	 */
	private void editGuideMessage(Model model, String messageId, boolean isError) {
		
		if(isError) {
			var message = AppUtil.getMessage(messageSource, messageId);
			model.addAttribute("errorMsg", message);
			model.addAttribute("isError", isError);
		}else {
			var message = "";
			model.addAttribute("errorMsg", message);
			model.addAttribute("isError", isError);
		}
		
	}
	
	/**
	 * データベーステーブルを取得してモデルに入れる
	 * 
	 * @param model　モデル
	 */
	private void searchDataBaseTable(Model model) {
		List<MarkerInfo> markerInfo = service.searchMarkers();
		model.addAttribute("markerInfo", markerInfo);
		System.out.println(markerInfo);
		/**データベーステーブルuser_infoの取得*/
		List<UserInfo> userInfo = service.searchUsers();
		model.addAttribute("userInfo", userInfo);
	}
	
}
