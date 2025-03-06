package com.kenspringapp.springapp.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kenspringapp.springapp.entity.LoginUser;

/*
 * DBへのアクセスメゾットを呼び出すDao
 */
@Repository
public class LoginUserDao {
    /* 
    * エンティティを管理するオブジェクト
    * 下のメゾットでエンティティクラスのLoginUserにキャストして戻り値を返すので必要なオブジェクト
    */
    @Autowired
    EntityManager em;

    /**
     * フォームの入力値から該当するユーザを検索 合致するものがない場合は、nullを返す
     * @param userMail
     * @return 一致するユーザが存在する時:UserEntity、存在しないときはNull
     */
    public LoginUser findUser(String userMail) {
        String query = "";
        query = "SELECT * FROM users WHERE usermail = :userName"; // setPatamaterで引数の値を代入できるようにNameParamaterを利用する。

        // EntityManagerで取得された結果はオブジェクトとなるので、LoginUser型へキャストが必要となる
        return (LoginUser)em.createNativeQuery(query, LoginUser.class).setParameter("usermail", userMail).getSingleResult();
    }
}
