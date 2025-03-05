package com.kenspringapp.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kenspringapp.springapp.repository.UserDao;
import com.kenspringapp.springapp.model.User;
// メゾット内で例外が発生した際にロールバックつまりエラーが起きる直前に戻るアノテーション。
@Transactional
@Service // Serviceクラスのアノテーション。ServiceクラスはDAOから受け取ったデータを変換しControllerに渡す役目がある。
public class UserService {
    @Autowired
    @Qualifier("UserDaoJdbcImpl") // DAOはインターフェースをimplimentsするのが定石らしく、implimentsするファイルを明示的にする。
    UserDao dao;

    // Daoのメゾットを実行する。戻り値をList型でControllerに引き渡す。
    public List<User> selectMany() {
        return dao.selectMany();
    }
}
