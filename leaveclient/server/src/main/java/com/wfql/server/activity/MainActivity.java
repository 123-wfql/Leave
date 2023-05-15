package com.wfql.server.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wfql.server.R;
import com.wfql.server.dao.LoginDao;
import com.wfql.server.entity.Login;

public class MainActivity extends AppCompatActivity {

    public LoginDao loginDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}