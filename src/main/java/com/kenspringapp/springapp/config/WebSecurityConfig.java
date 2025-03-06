package com.kenspringapp.springapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// あやしい
import com.kenspringapp.springapp.service.UserDetailsServiceImpl;

// Spring Securty関係の記述
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // フォームの値と比較するDBから取得したパスワードは暗号化されているので、フォームの値も暗号化するために使用する
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // ハッシュで暗号化した値を返す
        return bCryptPasswordEncoder;
    }

    // 静的なリソースはすべて認可処理の対象から除外する設定
    // @Override
    // public void configure(WebSecurity webSecurity) throws Exception {
    //     webSecurity.ignoring().anyRequest(
    //         "/images/**",
    //         "/css/**",
    //         "/javascript/**"
    //     );
    // }

    // Spring Securty関係の記述
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
            .anyRequest().authenticated()
            // .hasAnyRole(権限)で権限内のロール保有者のみ閲覧可能
            // ユーザー権限、管理者権限保有時のみアクセス可能(ログインが必要)
            .requestMatchers("GET", "/user").hasAnyRole("ADMIN", "USER")
            // 管理者権限保有時のみアクセス可能(ログインが必要)
            .requestMatchers("GET", "/admin").hasAnyRole("ADMIN")
            .requestMatchers("GET", "/userlist").hasAnyRole("ADMIN")
            // .permitAllでログインの有無に限らずページを閲覧できる。
            .requestMatchers("GET", "/").permitAll()
            .requestMatchers("/images/**").permitAll()
            .requestMatchers("/css/**").permitAll()
            .requestMatchers("/javascript/**").permitAll())
            // ログインが成功した際の遷移先を指定する？(第二引数はAlwaysUse:この設定を常に適用する設定。falseにすると、認証前にアクセスしたページに移動してしまう。)
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/user",true)
                .permitAll())
            // ログアウトに関する記載
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll());
        return http.build();
    }

    // 認証に使うデータソースを定義する為のメゾット
    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    // 認証に使用するデータ関連の設定(仮運用時用、次期バージョンでは削除の事)
    /* 〇一般ユーザー
     * ユーザー名：user　パスワード：user
     * 
     * 〇管理者
     * ユーザー名：admin　パスワード：admin
     */
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     // 一般ユーザ(ダミー)...テスト後、MariaDBから参照する
    //     // {noop}はno operationの略(暗号化なし平文で送信する)
    //     UserDetails user = User.builder().username("user")
    //     .password("{noop}user").roles("USER").build();
    //     // 管理者ユーザ(ダミー)... テスト後、MariaDBから参照する
    //     UserDetails admin = User.builder().username("admin")
    //     .password("{noop}admin").roles("ADMIN").build();
    //     return new InMemoryUserDetailsManager(user, admin);
    // }
}