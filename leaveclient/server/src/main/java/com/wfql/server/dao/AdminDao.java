package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wfql.server.entity.Admin;

import java.util.List;

@Dao
public interface AdminDao {


    @Insert
    void insert(Admin... admins);

    @Delete
    void delete(Admin... admins);

    @Query("DELETE FROM Admin")
    void deleteAll();

    @Query("DELETE FROM Admin WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(Admin... admins);

    @Query("SELECT * FROM Admin")
    List<Admin> queryAll();

    @Query("SELECT * FROM Admin WHERE adminId=:adminId ORDER BY adminId DESC limit 1")
    Admin queryByAdminId(String adminId);

    @Query("SELECT :projection FROM Admin WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM Admin WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);


}
