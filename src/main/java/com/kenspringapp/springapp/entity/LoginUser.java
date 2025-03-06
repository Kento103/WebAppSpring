package com.kenspringapp.springapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ログインユーザのユーザ名、パスワードを格納する為のEntity
 * @author aoi
 */
@Entity
@Table(name = "users")
public class LoginUser {

    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "usermail")
    @Id
    private String usermail;

    @Column(name = "userpassword")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return usermail;
    }

    public void setUserMail(String usermail) {
        this.usermail = usermail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
