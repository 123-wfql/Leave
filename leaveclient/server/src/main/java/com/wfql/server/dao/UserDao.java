package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;


import com.wfql.server.entity.User;

import java.util.List;

@Dao
public interface UserDao {


    @Insert
    void insert(User... users);

    @Delete
    void delete(User... users);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("DELETE FROM User WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(User... users);

    @Query("SELECT * FROM User")
    Cursor queryAll();

    @Query("SELECT * FROM User WHERE userId=:userId ORDER BY userId DESC limit 1")
    User queryByUserId(String userId);

    @Query("SELECT :projection FROM User WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM User WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);

    @RawQuery
    Cursor queryByExecSql(SupportSQLiteQuery query);

    @RawQuery
    int deleteByExecSql(SupportSQLiteQuery query);


}
