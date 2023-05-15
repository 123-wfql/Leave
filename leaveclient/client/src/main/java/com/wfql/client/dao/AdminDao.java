package com.wfql.client.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wfql.client.entity.Admin;

import java.util.List;

@Dao
public interface AdminDao {

    @Insert
    void insert(Admin... admin);

    @Delete
    void delete(Admin... admin);

    @Query("DELETE FROM Admin")
    void deleteAll();

    @Update
    int update(Admin... admin);

    @Query("SELECT * FROM Admin")
    List<Admin> queryAll();

    @Query("SELECT * FROM Admin WHERE admName = :admName ORDER BY admId DESC limit 1")
    Admin queryByName(String admName);



}
