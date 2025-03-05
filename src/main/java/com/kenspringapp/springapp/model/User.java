package com.kenspringapp.springapp.model;

import lombok.Data;

// lombokを使用しているのでアノテーション一つでGetter,Setterを作成できる
@Data
public class User {
    private int userId;
    private String password;
    private String userName;
}
