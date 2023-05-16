package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wfql.server.entity.Feedback;

import java.util.List;

@Dao
public interface FeedbackDao {


    @Insert
    void insert(Feedback... feedbacks);

    @Delete
    void delete(Feedback... feedbacks);

    @Query("DELETE FROM Feedback")
    void deleteAll();

    @Query("DELETE FROM Feedback WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(Feedback... feedbacks);

    @Query("SELECT * FROM Feedback")
    List<Feedback> queryAll();

    @Query("SELECT * FROM Feedback WHERE feedbackId=:feedbackId ORDER BY feedbackId DESC limit 1")
    Feedback queryByFeedbackId(String feedbackId);

    @Query("SELECT :projection FROM Feedback WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM Feedback WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);


}
