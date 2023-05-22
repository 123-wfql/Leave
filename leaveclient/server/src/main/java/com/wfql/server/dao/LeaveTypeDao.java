package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;


import com.wfql.server.entity.LeaveType;

import java.util.List;

@Dao
public interface LeaveTypeDao {


    @Insert
    void insert(LeaveType... leaveTypes);

    @Delete
    void delete(LeaveType... leaveTypes);

    @Query("DELETE FROM LeaveType")
    void deleteAll();

    @Query("DELETE FROM LeaveType WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(LeaveType... leaveTypes);

    @Query("SELECT * FROM LeaveType")
    Cursor queryAll();

    @Query("SELECT * FROM LeaveType WHERE leaveType=:leaveType ORDER BY leaveType DESC limit 1")
    LeaveType queryByLeaveType(String leaveType);

    @Query("SELECT :projection FROM LeaveType WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM LeaveType WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);

    @RawQuery
    Cursor queryByExecSql(SupportSQLiteQuery query);

    @RawQuery
    int deleteByExecSql(SupportSQLiteQuery query);


}
