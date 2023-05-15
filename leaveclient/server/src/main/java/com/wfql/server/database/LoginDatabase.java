package com.wfql.server.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wfql.server.dao.LoginDao;
import com.wfql.server.entity.Login;

@Database(entities = {Login.class}, version = 1, exportSchema = true)
public abstract class LoginDatabase extends RoomDatabase {
    public abstract LoginDao loginDao();
}
