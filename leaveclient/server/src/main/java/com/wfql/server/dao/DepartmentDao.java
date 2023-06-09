package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;


import com.wfql.server.entity.Department;

@Dao
public interface DepartmentDao {
    @Insert
    void insert(Department... departments);

    @Delete
    void delete(Department... departments);

    @Query("DELETE FROM Department")
    void deleteAll();

    @Query("DELETE FROM Department WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(Department... departments);

    @Query("SELECT * FROM Department")
    Cursor queryAll();

    @Query("SELECT * FROM Department WHERE department=:department ORDER BY department DESC limit 1")
    Department queryByDepartment(String department);

    @Query("SELECT * FROM Department WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);

    @RawQuery
    Cursor queryByExecSql(SupportSQLiteQuery query);

    @RawQuery
    int deleteByExecSql(SupportSQLiteQuery query);


}
