package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wfql.server.entity.Leave;

import java.util.List;

@Dao
public interface LeaveDao {


    @Insert
    void insert(Leave... leaves);

    @Delete
    void delete(Leave... leaves);

    @Query("DELETE FROM Leave")
    void deleteAll();

    @Query("DELETE FROM Leave WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(Leave... leaves);

    @Query("SELECT * FROM Leave")
    List<Leave> queryAll();

    @Query("SELECT * FROM Leave WHERE leaveId=:leaveId ORDER BY leaveId DESC limit 1")
    Leave queryByLeaveId(String leaveId);

    @Query("SELECT :projection FROM Leave WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM Leave WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);


}
