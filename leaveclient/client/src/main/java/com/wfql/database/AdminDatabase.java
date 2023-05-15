package com.wfql.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wfql.dao.AdminDao;
import com.wfql.entity.Admin;

@Database(entities = {Admin.class}, version = 1, exportSchema = true)
public abstract class AdminDatabase extends RoomDatabase {

    public abstract AdminDao adminDao();

}
