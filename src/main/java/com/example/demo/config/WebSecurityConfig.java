package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;

import lombok.RequiredArgsConstructor;

/**
 * Spring Security　カスタマイズ用
 * 
 * @author hagi71011
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	/**パスワードエンコーダ*/
	private final PasswordEncoder passwordEncoder;
	
	/**ユーザ情報取得　サービス*/
	private final UserDetailsService userDetailsService;
	
	/**メッセージ取得*/
	private final MessageSource messageSource;
	

	/**ユーザ名のname属性*/
	private final String USERNAME_PARAMETER = "name";
	/**
	 * Spring Security　カスタマイズ用
	 * 
	 * @param http　カスタマイズ用
	 * @return カスタマイズ結果
	 * @throws Exception　予期せぬ例外
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		
		http
		.authorizeHttpRequests(
				authorize -> authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
				.requestMatchers("/css/**").permitAll()
				.anyRequest().authenticated())
		.formLogin(login -> login.loginPage(UrlConst.LOGIN) // 自作ログイン画面(Controller)を使うための設定
				.usernameParameter(USERNAME_PARAMETER) // ユーザ名パラメータのname属性
				.defaultSuccessUrl(UrlConst.MAP)) // ログイン成功後のリダイレクトURL
		.logout(logout -> logout.logoutSuccessUrl(UrlConst.TOPPAGE)); // ログアウトした後にトップページへ移動するようにしている。
		return http.build();
	}
	
	/**
	 * Providerの定義
	 * 
	 * @return　カスタマイズprovider情報
	 */
	@Bean
	AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setMessageSource(messageSource);
		
		return provider;
	}
	
	
	
	
	
	
	
	
	
}
