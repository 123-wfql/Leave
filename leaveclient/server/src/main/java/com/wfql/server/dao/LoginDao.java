package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.wfql.server.entity.Login;

import java.util.Arrays;
import java.util.List;

@Dao
public interface LoginDao {


    @Insert
    void insert(Login... logins);

    @Delete
    void delete(Login... logins);

    @Query("DELETE FROM Login")
    int deleteAll();

    @Update
    int update(Login... logins);

    @Query("SELECT * FROM Login")
    Cursor queryAll();

    @Query("SELECT * FROM Login ORDER BY :sortOrder ASC")
    Cursor queryAllInSortOrder(String sortOrder);

    @Query("SELECT * FROM Login WHERE loginId=:loginId ORDER BY loginId DESC limit 1")
    Cursor queryAllBySelection(String loginId);

    @RawQuery
    Cursor queryByExecSql(SupportSQLiteQuery query);

    @Query("SELECT * FROM Login WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);

    @RawQuery
    int deleteByExecSql(SupportSQLiteQuery query);
}
