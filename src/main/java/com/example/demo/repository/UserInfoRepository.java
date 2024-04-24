package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserInfo;
/**
 * ユーザ情報テーブルDAO
 * 
 * @author hagi71011
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String>{

	
}
