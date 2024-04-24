package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.UrlConst;
/**
 * トップページコントローラー
 * 
 * @author hagi71011
 */
@Controller
public class ToppageController {

	/**
	 * 初期表示
	 * 
	 * @return　表示画面
	 */
	@GetMapping(UrlConst.TOPPAGE)
	public String view() {
		return "toppage";
	}
}
