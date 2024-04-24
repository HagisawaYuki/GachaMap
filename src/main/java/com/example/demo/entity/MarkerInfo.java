package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * マーカー情報テーブル　エンティティー
 * 
 * @author hagi71011
 */
@Table(name = "marker_info")
@Entity
@Data
@AllArgsConstructor
public class MarkerInfo {

	/**IDナンバー　(登録順でナンバリング)*/
	@Id
	private int id;
	
	/**マーカー名*/
	private String name;
	
	/**緯度*/
	private double latitude;
	
	/**経度*/
	private double longitude;
	
	/**マーカーの説明*/
	private String explanation;
	
	/**ユーザID (FK)*/
	private int user_id;
	
	public MarkerInfo() {
	}
	
}
