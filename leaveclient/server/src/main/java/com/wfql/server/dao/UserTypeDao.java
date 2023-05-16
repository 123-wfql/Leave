package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.wfql.server.entity.UserType;

import java.util.List;

@Dao
public interface UserTypeDao {


    @Insert
    void insert(UserType... userTypes);

    @Delete
    void delete(UserType... userTypes);

    @Query("DELETE FROM UserType")
    void deleteAll();

    @Query("DELETE FROM UserType WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(UserType... userTypes);

    @Query("SELECT * FROM UserType")
    List<UserType> queryAll();

    @Query("SELECT * FROM UserType WHERE userType=:userType ORDER BY userType DESC limit 1")
    UserType queryByUserType(String userType);

    @Query("SELECT :projection FROM UserType WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM UserType WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);


}
