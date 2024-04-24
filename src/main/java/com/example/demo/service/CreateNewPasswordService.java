package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * パスワード変更画面　サービス
 * 
 * @author hagi71011
 */

@Service
@RequiredArgsConstructor
public class CreateNewPasswordService {

	/**パスワードエンコーダ*/
	private final PasswordEncoder passwordEncoder;
	/**ユーザ情報テーブルDAO*/
	private final UserInfoRepository repository;
	/**
	 * ユーザ情報テーブル　主キー検索
	 * 
	 * @param name　ユーザ名
	 * @return ユーザ情報テーブルを主キー検索した結果(1件)
	 */
	public Optional<UserInfo> searchUserById(String name){
		return repository.findById(name);
	}
	
	/**
	 * パスワードを更新する
	 * 
	 * @param user　パスワード更新するユーザ
	 * @param password　新しいパスワード
	 * @return　ユーザ情報
	 */
	public UserInfo updateUserPassword(UserInfo user, String password) {
		return new UserInfo(user.getId(), user.getName(), passwordEncoder.encode(password), user.getBirthday(), user.getLoginFailureCount(), user.getAccountLockedTime(), user.isDisabled());
	}
}
