package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wfql.server.entity.Login;

import java.util.List;

@Dao
public interface LoginDao {


    @Insert
    void insert(Login... logins);

    @Delete
    void delete(Login... logins);

    @Query("DELETE FROM Login")
    void deleteAll();

    @Query("DELETE FROM Login WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(Login... logins);

    @Query("SELECT * FROM Login")
    List<Login> queryAll();

    @Query("SELECT * FROM Login WHERE loginId=:loginId ORDER BY loginId DESC limit 1")
    Login queryByLoginId(String loginId);

    @Query("SELECT loginId FROM Login")
    List<String> queryLoginIdArrayList();

    @Query("SELECT :projection FROM Login WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM Login WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);


}
