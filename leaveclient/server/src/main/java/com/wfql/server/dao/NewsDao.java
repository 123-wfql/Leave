package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.wfql.server.entity.News;

import java.util.List;

@Dao
public interface NewsDao {


    @Insert
    void insert(News... news);

    @Delete
    void delete(News... news);

    @Query("DELETE FROM News")
    void deleteAll();

    @Query("DELETE FROM News WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(News... news);

    @Query("SELECT * FROM News")
    List<News> queryAll();

    @Query("SELECT * FROM News WHERE newsId=:newsId ORDER BY newsId DESC limit 1")
    News queryByNewsId(String newsId);

    @Query("SELECT :projection FROM news WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM news WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);


}
