package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignUpForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザ登録画面　サービス
 * 
 * @author hagi71011
 */

@Service
@RequiredArgsConstructor
public class SignUpService {

	/**ユーザ情報テーブルDAO*/
	private final UserInfoRepository repository;
	/**パスワードエンコーダ*/
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザ情報テーブル　新規登録
	 * 
	 * @param form　入力情報
	 * @return　登録情報(ユーザ情報登録エンティティー)、すでに同じユーザ名が登録されている場合はEmpty
	 */
	public Optional<UserInfo> resistUserInfo(SignUpForm form){
		
		var userInfoExistedOpt = repository.findById(form.getName());
		if(userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}
		var userInfo = new UserInfo();
		userInfo.setName(form.getName());
		userInfo.setBirthday(form.getYear() + "/" + form.getMonth() + "/" + form.getDay());
		var encodedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encodedPassword);
		userInfo.setLoginFailureCount(0);
		userInfo.setAccountLockedTime(null);
		userInfo.setDisabled(false);
		return Optional.of(repository.save(userInfo));
	}
}
