package com.wfql.client.databaseNoNeed;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wfql.client.daoNoNeed.LoginDaoOfRoom;
import com.wfql.client.entity.Login;

@Database(entities = {Login.class}, version = 1, exportSchema = true)
public abstract class LoginDatabaseOfRoom extends RoomDatabase {
    public abstract LoginDaoOfRoom loginDao();
}
