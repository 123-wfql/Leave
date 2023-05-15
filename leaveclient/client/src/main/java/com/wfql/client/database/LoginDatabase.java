package com.wfql.client.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wfql.client.dao.LoginDao;
import com.wfql.client.entity.Login;

@Database(entities = {Login.class}, version = 1, exportSchema = true)
public abstract class LoginDatabase extends RoomDatabase {
    public abstract LoginDao loginDao();
}
