package com.wfql.client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wfql.client.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title;
    private Button btn_user_detail;
    private Button btn_askleave;
    private Button btn_check;
    private Button btn_myleave;
    private Button btn_feedback;
    private Button btn_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_title = findViewById(R.id.tv_title);
        btn_user_detail = findViewById(R.id.btn_user_detail);
        btn_askleave = findViewById(R.id.btn_askleave);
        btn_check = findViewById(R.id.btn_check);
        btn_myleave = findViewById(R.id.btn_myleave);
        btn_feedback = findViewById(R.id.btn_feedback);
        btn_news = findViewById(R.id.btn_news);

        btn_user_detail.setOnClickListener(this);
        btn_askleave.setOnClickListener(this);
        btn_check.setOnClickListener(this);
        btn_myleave.setOnClickListener(this);
        btn_feedback.setOnClickListener(this);
        btn_news.setOnClickListener(this);



        tv_title.setText("主页");


    }

    public void goBack(View view){
        onBackPressed();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_user_detail) {
            Intent intentUserDetail = new Intent(MainActivity.this, UserDetailActivity.class);
            intentUserDetail.putExtra("sourceActivity", MainActivity.class);
            startActivity(intentUserDetail);
        } else if (id == R.id.btn_askleave) {

        } else if (id == R.id.btn_check) {

        } else if (id == R.id.btn_myleave) {

        } else if (id == R.id.btn_feedback) {

        } else if (id == R.id.btn_news) {

        } else {

        }
    }
}