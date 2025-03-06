package com.kenspringapp.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication // これはSpringBootアプリケーションだよということを示している(必須)
@ComponentScan("kenspringapp.springapp") //BeanとしてDIに登録する。パッケージとして指定することができる。
@EntityScan("kenspringapp.springapp.entity") // 上記同様BeanとしてDIに登録
@EnableJpaRepositories("kenspringapp.springapp.repository")
public class SpringappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringappApplication.class, args);
	}
}
