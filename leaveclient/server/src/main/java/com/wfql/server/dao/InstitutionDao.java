package com.wfql.server.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.wfql.server.entity.Institution;

import java.util.List;

@Dao
public interface InstitutionDao {


    @Insert
    void insert(Institution... institutions);

    @Delete
    void delete(Institution... institutions);

    @Query("DELETE FROM Institution")
    void deleteAll();

    @Query("DELETE FROM Institution WHERE :selection=:selectionArgs")
    int delete(String selection, String[] selectionArgs);

    @Update
    int update(Institution... institutions);

    @Query("SELECT * FROM Institution")
    Cursor queryAll();

    @Query("SELECT * FROM Institution WHERE institution=:institution ORDER BY institution DESC limit 1")
    Institution queryByInstitution(String institution);

    @Query("SELECT :projection FROM Institution WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder);

    @Query("SELECT * FROM Institution WHERE :selection=:selectionArgs ORDER BY :sortOrder DESC")
    Cursor query(String selection, String[] selectionArgs, String sortOrder);

    @RawQuery
    Cursor queryByExecSql(SupportSQLiteQuery query);

    @RawQuery
    int deleteByExecSql(SupportSQLiteQuery query);


}
