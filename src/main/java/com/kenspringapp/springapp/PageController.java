package com.kenspringapp.springapp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class PageController {
    // ルートディレクトリでトップページを表示する(公開ページ)
    @RequestMapping("/")
    public String topPage() {
        return "こんにちは！HelloWorld!!";
    }

    // ユーザーページを表示する(要認証[一般ユーザ、管理者どちらでも遷移できる])
    @RequestMapping("/user")
    public String userPage() {
        return "ユーザーページへようこそ。";
    }

    // 管理者向けダッシュボードを表示する(要認証[管理者のみ表示が許可される])
    @RequestMapping("/admin")
    public String adminPage() {
        return "管理者向けダッシュボードへようこそ。";
    }
}
