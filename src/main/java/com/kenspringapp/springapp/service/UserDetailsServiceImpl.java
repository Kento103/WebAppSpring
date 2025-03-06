package com.kenspringapp.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kenspringapp.springapp.entity.LoginUser;
import com.kenspringapp.springapp.repository.LoginUserDao;

// Spring Securityのユーザー検索用のサービス実装クラス
// DataSourceの引数として指定する事で認証にDBを利用できるようになる
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    // DBからユーザー情報を検索するメゾットを実装したクラス
    @Autowired
    private LoginUserDao userDao;

    /*
     * UserDetailsSeriviceインタフェースの実装メゾット
     * フォームから取得したユーザー名でDBを検索し、合致下ものが存在したとき、パスワード、権限情報と共にUserDetailsオブジェクトを生成
     * コンフィグクラスで上入力値とDBから取得したパスワードと比較し、ログイン判定を行う為のもの
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LoginUser user = userDao.findUser(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User" + userName + "was not found in the database");
        }

        /*
         * 権限のリスト
         * 一旦全員Adminとしてログインできるように実装するものとする
         * 今後、AdminとUserを分離できるように設定を行う。
         */
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");

        grantList.add(authority);

        // 平文のパスワードは渡せないので、暗号化を実施する
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // UserDetailsはインターフェースなので、Userクラスのコンストラクタで生成したユーザオブジェクトをキャストする
        //UserDetails userDetails = (UserDetails)new User(user.getUserMail(), encoder.encode(user.getPassword()), grantList);
        UserDetails userDetails = (UserDetails) new User(user.getUserMail(), encoder.encode(user.getPassword()), grantList);

        return userDetails;
    }
}
