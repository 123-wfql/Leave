package com.wfql.client.daoNoNeed;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wfql.client.entity.Login;

import java.util.List;

@Dao
public interface LoginDaoOfRoom {


    @Insert
    void insert(Login... login);

    @Delete
    void delete(Login... login);

    @Query("DELETE FROM Login")
    void deleteAll();

    @Update
    int update(Login... login);

    @Query("SELECT * FROM Login")
    List<Login> queryAll();

    @Query("SELECT * FROM Login WHERE loginId = :loginId ORDER BY loginId DESC limit 1")
    Login queryByLoginId(String loginId);

    @Query("SELECT LoginId FROM Login")
    List<String> queryLoginIdArrayList();

    @Query("DELETE FROM Login WHERE loginId = :loginId")
    void deleteByLoginId(String loginId);
}
