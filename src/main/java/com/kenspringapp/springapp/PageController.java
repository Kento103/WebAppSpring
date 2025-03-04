package com.kenspringapp.springapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// ルーティング
@Controller
public class PageController {
    // ルートディレクトリでトップページを表示する(公開ページ)
    @GetMapping("/")
    public String topPage(Model model) {
        model.addAttribute("message", "Hello Thymeleaf!");
        return "top";
    }
}
