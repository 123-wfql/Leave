package com.client.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.client.dao.AdminDao;
import com.client.entity.Admin;

@Database(entities = {Admin.class}, version = 1, exportSchema = true)
public abstract class AdminDatabase extends RoomDatabase {

    public abstract AdminDao adminDao();

}
