package com.kenspringapp.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kenspringapp.springapp.model.User;
import com.kenspringapp.springapp.service.UserService;

// ルーティング
// Controllerクラスのアノテーション
@Controller
public class PageController {
    // インスタンスを作成してDIコンテナに収納する
    @Autowired
    UserService userService;

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
        List<User> userList = userService.selectMany();
        // userServiceから受けとったデータをView側に渡す。
        model.addAttribute("userList", userList);

        // template配下のファイル名を指定することでViewを呼び出せる。
        return "userlist";
    }
}
