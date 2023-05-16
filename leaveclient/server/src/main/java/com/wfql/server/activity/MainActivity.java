package com.wfql.server.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.wfql.server.R;
import com.wfql.server.dao.LoginDao;
import com.wfql.server.database.LeaveSysDB;

public class MainActivity extends AppCompatActivity {


    private LoginDao loginDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("wfql", "MainActivity onCreate");
        LeaveSysDB leaveSysDB = Room.databaseBuilder(this, LeaveSysDB.class, "LeaveSysDB")
                .build();
        loginDao = leaveSysDB.loginDao();
        Log.d("wfql", "loginDao in act create");
    }


}