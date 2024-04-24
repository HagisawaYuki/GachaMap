package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.example.demo.entity.MarkerInfo;
/**
 * マーカー情報テーブルDAO
 * 
 * @author hagi71011
 */
//@Repository
public interface MarkerInfoRepository extends JpaRepository<MarkerInfo, Integer>{

}
