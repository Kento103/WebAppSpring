package com.kenspringapp.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class SpringappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringappApplication.class, args);
	}

	// Spring Securty関係の記述
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests
			// .hasAnyRole(権限)で権限内のロール保有者のみ閲覧可能
			// ユーザー権限、管理者権限保有時のみアクセス可能(ログインが必要)
			.requestMatchers("GET", "/user").hasAnyRole("ADMIN", "USER")
			// 管理者権限保有時のみアクセス可能(ログインが必要)
			.requestMatchers("GET", "/admin").hasAnyRole("ADMIN")
			// .permitAllでログインの有無に限らずページを閲覧できる。
			.requestMatchers("GET", "/").permitAll())
			// ログインが成功した際の遷移先を指定する？(第二引数はAlwaysUse:この設定を常に適用する設定。falseにすると、認証前にアクセスしたページに移動してしまう。)
			.formLogin(login -> login.defaultSuccessUrl("/user",true));
		return http.build();
	}

	// 認証に使用するデータ関連の設定
	@Bean
	public UserDetailsService userDetailsService() {
		// 一般ユーザ(ダミー)...テスト後、MariaDBから参照する
		// {noop}はno operationの略(暗号化なし平文で送信する)
		UserDetails user = User.builder().username("user")
		.password("{noop}user").roles("USER").build();
		// 管理者ユーザ(ダミー)... テスト後、MariaDBから参照する
		UserDetails admin = User.builder().username("admin")
		.password("{noop}admin").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}
}
