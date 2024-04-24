package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * ユーザ情報テーブル　エンティティー
 * 
 * @author hagi71011
 */
@Table(name = "user_info")
@Entity
@Data
@AllArgsConstructor
public class UserInfo {
	/**IDナンバー　(登録順でナンバリング)*/
	private int id;
	
	/**ユーザ名　(IDのため固有値、4~20桁)*/
	@Id
	private String name;
	
	/**パスワード (8~20桁)*/
	private String password;
	
	/**誕生日　(yyyy/mm/ddの形式で情報を保管)*/
	private String birthday;
	
	/**ログイン失敗回数*/
	@Column(name = "login_failure_count")
	private int loginFailureCount = 0;
	
	/**アカウントロック日時*/
	@Column(name = "account_locked_time")
	private LocalDateTime accountLockedTime;
	
	/**利用可能か(true : 利用可能)*/
	@Column(name = "is_disabled")
	private boolean isDisabled;
	
	public UserInfo() {
	}
	
	
	/**
	 * ログイン失敗回数を加算する
	 * @return UserInfo
	 */
	public UserInfo incrementLoginFailureCount() {
		return new UserInfo(id, name, password, birthday, ++loginFailureCount, accountLockedTime, isDisabled);
	}
	
	/**
	 * ログイン失敗回数をリセットする
	 * @return UserInfo
	 */
	public UserInfo resetLoginFailureInfo() {
		return new UserInfo(id, name, password, birthday, 0, null, isDisabled);
	}
	
	/**
	 * アカウントロック日時を更新する
	 * @return UserInfo
	 */
	public UserInfo updateAccountLocked() {
		return new UserInfo(id, name, password, birthday, 0, LocalDateTime.now(), isDisabled);
	}
	
	
}

