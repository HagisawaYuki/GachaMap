package com.example.demo.form;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * マーカー登録フォーム
 * 
 * @author hagi71011
 */
@Data
public class RegisterMarkerForm {

	/**マーカーの名前*/
	@NotEmpty
	private String name;
	/**マーカーの緯度*/
	@NotNull
	@Digits(integer = 2, fraction = 6)
	@Range(min = -90, max = 90)
	private double latitude;
	/**マーカーの経度*/
	@NotNull
	@Digits(integer = 3, fraction = 6)
	@Range(min = -180, max = 180)
	private double longitude;
	/**マーカーの説明*/
	@NotEmpty
	private String explanation;
}
