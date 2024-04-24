package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MarkerInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.RegisterMarkerForm;
import com.example.demo.repository.MarkerInfoRepository;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 *マップ画面　サービス
 *@author hagi71011
 */
@Service
@RequiredArgsConstructor
public class MapService {

	/**マーカー情報テーブルDAO*/
	private final MarkerInfoRepository markerRepository;
	/**ユーザー情報テーブルDAO*/
	private final UserInfoRepository userRepository;
	
	/**
	 * マーカー情報テーブル　全検索
	 * 
	 * @param id　マーカーID
	 * @return マーカー情報テーブルを全検索した結果
	 */
	public List<MarkerInfo> searchMarkers(){
		return markerRepository.findAll();
	}
	/**
	 * ユーザー情報テーブル　全検索した結果
	 * 
	 * @param id　ユーザーID
	 * @return ユーザー情報テーブルを全検索した結果
	 */
	public List<UserInfo> searchUsers(){
		return userRepository.findAll();
	}
	
	/**
	 * マーカー情報テーブル　新規登録
	 * 
	 * @param form　入力情報
	 * @return　登録情報(マーカー情報登録エンティティー)
	 */
	public Optional<MarkerInfo> registerMarkerInfo(RegisterMarkerForm form, User user){
		var markerInfo = new MarkerInfo();
		markerInfo.setName(form.getName());
		markerInfo.setExplanation(form.getExplanation());
		markerInfo.setLatitude(form.getLatitude());
		markerInfo.setLongitude(form.getLongitude());
		//登録するユーザ情報を取得する。
		int userID = searchUserIDByUserName(user.getUsername());
		markerInfo.setUser_id(userID);
		return Optional.of(markerRepository.save(markerInfo));
	}
	
	/**
	 * ユーザ名からユーザIDナンバーを探して返す関数
	 * 
	 * @param name　ユーザ名
	 * @return　ユーザIDナンバー
	 */
	private int searchUserIDByUserName(String name) {
		return userRepository.findById(name).get().getId();
	}
}
