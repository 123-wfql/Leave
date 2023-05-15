package com.wfql.client.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wfql.client.R;

public class AskLeaveActivity extends AppCompatActivity {

    private EditText et_askleave_userid;
    private EditText et_askleave_username;
    private EditText et_askleave_userkind;
    private EditText et_askleave_institution;
    private EditText et_askleave_department;
    private Spinner sp_askleave_leavekind;
    private CheckBox ck_askleave_isurgent;
    private TextView tv_askleave_userid;
    private TextView tv_askleave_institution;
    private TextView tv_askleave_department;
    private EditText et_askleave_destination;
    private EditText et_askleave_leavereason;
    private Button btn_askleave_startdate;
    private Button btn_askleave_starttime;
    private Button btn_askleave_enddate;
    private Button btn_askleave_endtime;
    private final static String[] leaveKindArray = {"请选择", "不离校", "离校不离穗", "离穗"};
    private final static String[] check1Array = {"请选择", "10086"};
    private final static String[] check2Array = {"请选择", "10086"};
    private final static String[] check3Array = {"请选择", "10086"};
    private Spinner sp_askleave_check1;
    private Spinner sp_askleave_check2;
    private Spinner sp_askleave_check3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_leave);

        tv_askleave_userid = findViewById(R.id.tv_askleave_userid);
        et_askleave_userid = findViewById(R.id.et_askleave_userid);
        //TextView tv = findViewById(R.id.tv_askleave_username);
        et_askleave_username = findViewById(R.id.et_askleave_username);
        //TextView tv = findViewById(R.id.tv_askleave_userkind);
        et_askleave_userkind = findViewById(R.id.et_askleave_userkind);
        tv_askleave_institution = findViewById(R.id.tv_askleave_institution);
        et_askleave_institution = findViewById(R.id.et_askleave_institution);
        tv_askleave_department = findViewById(R.id.tv_askleave_department);
        et_askleave_department = findViewById(R.id.et_askleave_department);
        sp_askleave_leavekind = findViewById(R.id.sp_askleave_leavekind);
        ck_askleave_isurgent = findViewById(R.id.ck_askleave_isurgent);
        et_askleave_destination = findViewById(R.id.et_askleave_destination);
        et_askleave_leavereason = findViewById(R.id.et_askleave_leavereason);
        Drawable ic_calendar = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_calendar, null);
        Drawable ic_clock = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clock, null);
        btn_askleave_startdate = findViewById(R.id.btn_askleave_startdate);
        btn_askleave_starttime = findViewById(R.id.btn_askleave_starttime);
        btn_askleave_enddate = findViewById(R.id.btn_askleave_enddate);
        btn_askleave_endtime = findViewById(R.id.btn_askleave_endtime);
        sp_askleave_check1 = findViewById(R.id.sp_askleave_check1);
        sp_askleave_check2 = findViewById(R.id.sp_askleave_check2);
        sp_askleave_check3 = findViewById(R.id.sp_askleave_check3);

        //定义缩放图标
        ic_calendar.setBounds(0,0,25,25);
        ic_clock.setBounds(0,0,25,25);
        btn_askleave_startdate.setCompoundDrawables(ic_calendar, null, null, null);
        btn_askleave_starttime.setCompoundDrawables(ic_clock, null, null, null);
        btn_askleave_enddate.setCompoundDrawables(ic_calendar, null, null, null);
        btn_askleave_endtime.setCompoundDrawables(ic_clock, null, null, null);

        ArrayAdapter<String> leaveKindAdapter = new ArrayAdapter<>(this, R.layout.stringarray_select, leaveKindArray);
        sp_askleave_leavekind.setAdapter(leaveKindAdapter);
        sp_askleave_leavekind.setSelection(0);

        ArrayAdapter<String> check1Adapter = new ArrayAdapter<>(this, R.layout.stringarray_select, check1Array);
        sp_askleave_check1.setAdapter(check1Adapter);
        sp_askleave_check1.setSelection(0);

        ArrayAdapter<String> check2Adapter = new ArrayAdapter<>(this, R.layout.stringarray_select, check2Array);
        sp_askleave_check2.setAdapter(check2Adapter);
        sp_askleave_check2.setSelection(0);

        ArrayAdapter<String> check3Adapter = new ArrayAdapter<>(this, R.layout.stringarray_select, check3Array);
        sp_askleave_check3.setAdapter(check3Adapter);
        sp_askleave_check3.setSelection(0);

        et_askleave_userid.setText("31195182");
        et_askleave_userid.setEnabled(false);
        et_askleave_username.setText("小太阳");
        et_askleave_username.setEnabled(false);
        et_askleave_userkind.setText("学生");
        et_askleave_userkind.setEnabled(false);
        et_askleave_institution.setText("计算机");
        et_askleave_institution.setEnabled(false);
        et_askleave_department.setText("软件工程");
        et_askleave_department.setEnabled(false);






    }

    public void goBack(View view){
        onBackPressed();
    }
}