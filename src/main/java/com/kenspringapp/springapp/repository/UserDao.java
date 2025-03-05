package com.kenspringapp.springapp.repository;

import java.util.List;

import com.kenspringapp.springapp.model.User;
import org.springframework.dao.DataAccessException;

public interface UserDao {
    // Userテーブルの全データを取得
    public List<User> selectMany() throws DataAccessException;
}
