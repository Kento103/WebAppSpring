package com.kenspringapp.springapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// ルーティング
@Controller
public class PageController {
    // ルートディレクトリでトップページを表示する(公開ページ)
    // ルートディレクトリのアドレスにアクセスした際にGetメゾットを実行する。
    @GetMapping("/")
    public String topPage(Model model) {
        model.addAttribute("message", "Hello Thymeleaf!");
        // template配下のファイル名を指定することでViewを呼び出せる。
        return "top";
    }

    // ユーザーリストのページを表示する(管理者専用ページ)
    @GetMapping("/userlist")
    public String userList(Model model) {
        // template配下のファイル名を指定する事でViewを呼び出せる
        return "userlist";
    }
}
