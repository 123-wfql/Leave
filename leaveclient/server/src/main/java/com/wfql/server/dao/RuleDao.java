package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.wfql.server.entity.Rule;

import java.util.List;

@Dao
public interface RuleDao {


    @Insert
    void insert(Rule... rules);

    @Delete
    void delete(Rule... rules);

    @Query("DELETE FROM Rule")
    void deleteAll();

    @Query("DELETE FROM Rule WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(Rule... rules);

    @Query("SELECT * FROM Rule")
    Cursor queryAll();

    @Query("SELECT * FROM Rule WHERE ruleId=:ruleId ORDER BY ruleId DESC limit 1")
    Rule queryByRuleId(String ruleId);

    @Query("SELECT :projection FROM Rule WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM Rule WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);

    @RawQuery
    Cursor queryByExecSql(SupportSQLiteQuery query);

    @RawQuery
    int deleteByExecSql(SupportSQLiteQuery query);


}
