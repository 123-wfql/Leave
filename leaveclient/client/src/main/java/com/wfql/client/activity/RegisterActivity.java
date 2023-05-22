package com.wfql.client.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Interpolator;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wfql.client.R;
import com.wfql.client.content.UserContent;
import com.wfql.client.content.UserTypeContent;

import java.util.Calendar;
import java.util.Random;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, NumberPicker.OnValueChangeListener {

    private final static String PLS_SELECT = "——请选择——";
    private final static String[] genderArray = {PLS_SELECT, "男", "女"};
    private final static String[] userTypeArray = {PLS_SELECT, "学生", "教师"};
    private final static String[] institutionArray = {PLS_SELECT, "计算机学院", "信息工程学院"};
    private final static String[] departmentArray = {PLS_SELECT, "软件工程专业", "电子信息专业"};
    private NumberPicker np_register_year;
    private EditText et_register_userName;
    private LinearLayout ll_register_institution;
    private LinearLayout ll_register_department;
    private Spinner sp_register_gender;
    private Spinner sp_register_usertype;
    private Spinner sp_register_institution;
    private Spinner sp_register_department;
    private String userName;
    private String genderSelected;
    private String userTypeSelected;
    private String institutionSelected;
    private String departmentSelected;
    private String enterYearSelected;
    private EditText et_register_first;
    private EditText et_register_second;
    private String firstPwd;
    private String secondPwd;
    private Button btn_register_commit;
    private Button btn_register_cancel;
    private String userId;
    private TextView tv_title;
    private String userPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_title = findViewById(R.id.tv_title);

        ll_register_institution = findViewById(R.id.ll_register_institution);
        ll_register_department = findViewById(R.id.ll_register_department);
        np_register_year = findViewById(R.id.np_register_year);
        et_register_userName = findViewById(R.id.et_register_username);
        sp_register_gender = findViewById(R.id.sp_register_gender);
        sp_register_usertype = findViewById(R.id.sp_register_userType);
        sp_register_institution = findViewById(R.id.sp_register_institution);
        sp_register_department = findViewById(R.id.sp_register_department);
        et_register_first = findViewById(R.id.et_register_first);
        et_register_second = findViewById(R.id.et_register_second);
        btn_register_commit = findViewById(R.id.btn_register_commit);
        btn_register_cancel = findViewById(R.id.btn_register_cancel);

        tv_title.setText("用户注册");

        et_register_first.addTextChangedListener(new ViewTextWatcher(et_register_first, 18));
        et_register_second.addTextChangedListener(new ViewTextWatcher(et_register_second, 18));
        et_register_userName.addTextChangedListener(new ViewTextWatcher(et_register_userName, 18));

        sp_register_gender.setOnItemSelectedListener(this);
        sp_register_usertype.setOnItemSelectedListener(this);
        sp_register_institution.setOnItemSelectedListener(this);
        sp_register_department.setOnItemSelectedListener(this);

        np_register_year.setOnValueChangedListener(this);

        btn_register_commit.setOnClickListener(this);
        btn_register_cancel.setOnClickListener(this);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, R.layout.stringarray_select, genderArray);
        sp_register_gender.setAdapter(genderAdapter);
        sp_register_gender.setSelection(0);

        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<>(this, R.layout.stringarray_select, userTypeArray);
        sp_register_usertype.setAdapter(userTypeAdapter);
        sp_register_usertype.setSelection(0);

        ArrayAdapter<String> institutionAdapter = new ArrayAdapter<>(this, R.layout.stringarray_select, institutionArray);
        sp_register_institution.setAdapter(institutionAdapter);
        sp_register_institution.setSelection(0);

        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, R.layout.stringarray_select, departmentArray);
        sp_register_department.setAdapter(departmentAdapter);
        sp_register_department.setSelection(0);

        np_register_year.setMaxValue(Calendar.getInstance().get(Calendar.YEAR));
        np_register_year.setValue(2019);
        enterYearSelected = Integer.toString(2019);
        np_register_year.setMinValue(1970);
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int id = adapterView.getId();
        if (id == R.id.sp_register_gender){
            genderSelected = (String) adapterView.getItemAtPosition(i);
        } else if (id == R.id.sp_register_userType){
            userTypeSelected = (String) adapterView.getItemAtPosition(i);
            ll_register_institution.setVisibility(!userTypeSelected.equals(PLS_SELECT) ? View.VISIBLE : View.GONE);

        } else if (id == R.id.sp_register_institution) {
            institutionSelected = (String) adapterView.getItemAtPosition(i);
            ll_register_department.setVisibility(!institutionSelected.equals(PLS_SELECT) ? View.VISIBLE : View.GONE);
        } else if (id ==  R.id.sp_register_department) {
            departmentSelected = (String) adapterView.getItemAtPosition(i);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.np_register_year) {
//            Calendar calendar = Calendar.getInstance();
//            int year = calendar.get(Calendar.YEAR);
//
//            // 创建日期选择对话框
//            DatePickerDialog datePickerDialog = new DatePickerDialog(
//                    this,
//                    android.R.style.Theme_Material_Light_Dialog_Alert,
//                    new DatePickerDialog.OnDateSetListener() {
//
//                        @Override
//                        public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth) {
//                            // 处理选择的年份selectedYear
//                            enterYearSelected = Integer.toString(selectedYear);
//                        }
//                    },
//                    year, // 设置初始年份为当前年份
//                    0,    // 设置初始月份为 0，表示一月
//                    1     // 设置初始日期为 1
//            );
//            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//            datePickerDialog.show();

        }else if(id == R.id.btn_register_commit) {
            if (firstPwd.equals(secondPwd)) {
                if(createUserId()){
                    if(recordUser()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("注册成功，你的ID是：" + userId)
                                .setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 点击去登录按钮后的操作
                                        // 跳转到登录界面，并携带ID和密码参数
                                        Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                        intentLogin.putExtra("userId", userId);
                                        intentLogin.putExtra("userPwd", firstPwd);
                                        intentLogin.putExtra("sourceActivity", RegisterActivity.class);
                                        startActivity(intentLogin);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    };
                };

            } else {
                Toast.makeText(this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.btn_register_cancel) {
            finish();
        };
    }

    private boolean createUserId() {

        Uri uri1 = Uri.parse(UserTypeContent.CONTENT_URI_SQL + "");
        String idTag = null;
        String genderTag = null;
        String yearTag;
        String indexTag;
        try {
            Cursor cursor1 = getContentResolver().query(uri1, null, "userType=?", new String[]{userTypeSelected}, null);
            if (cursor1 != null && cursor1.getCount() > 0) {
                cursor1.moveToFirst();
                int idIntColumnIndex = cursor1.getColumnIndexOrThrow("idTag");
                idTag = cursor1.getString(idIntColumnIndex);
            } else {
                Toast.makeText(this, "创建用户失败，原因：用户类型不存在", Toast.LENGTH_SHORT).show();
                return false;
            }
            cursor1.close();
            if (genderSelected.equals("男")) {
                genderTag = "1";
            } else if(genderSelected.equals("女")) {
                genderTag = "2";
            } else {
                Toast.makeText(this, "创建用户失败，原因：性别不存在", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText((Context) RegisterActivity.this, (CharSequence) e, Toast.LENGTH_SHORT).show();
            return false;
        }
        yearTag = enterYearSelected.substring(enterYearSelected.length()-2);
        indexTag = String.format("%04d", new Random().nextInt(9999));
        userId = idTag + genderTag + yearTag + indexTag;
        Uri uri2 = Uri.parse(UserContent.CONTENT_URI_SQL + "");
        try {
            do{
                Cursor cursor2 = getContentResolver().query(uri2, null, "userId=?", new String[]{userId}, null);
                if (cursor2 != null){
                    if (cursor2.getCount() == 0) {
                        Log.d("register", "id没有重复");
                        cursor2.close();
                        break;
                    } else {
                        indexTag = String.format("%04d", new Random().nextInt(9999));
                        userId = idTag + genderTag + yearTag + indexTag;
                    }
                } else {
                    Toast.makeText(this, "创建用户失败，原因：查重失败", Toast.LENGTH_SHORT);
                }
                cursor2.close();
            } while (true);
        } catch (Exception e){
            Toast.makeText((Context) RegisterActivity.this, (CharSequence) e, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
        
    }

    private boolean recordUser() {
        userPwd = BCrypt.withDefaults().hashToString(12, firstPwd.toCharArray());
        Uri uri = Uri.parse(UserContent.CONTENT_URI_SQL + "");
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("userPwd", userPwd);
        values.put("userName", userName);
        values.put("verifyPhone", "");
        values.put("userGender", genderSelected);
        values.put("enterYear", enterYearSelected);
        values.put("iconUrl", "");
        values.put("checkLv", 0);
        values.put("userRemark", "");
        values.put("depName", departmentSelected);
        values.put("governLv", 0);
        values.put("job", "");
        try {
            getContentResolver().insert(uri, values);
        } catch (Exception e){
            Toast.makeText((Context)RegisterActivity.this, (CharSequence) e, Toast.LENGTH_SHORT ).show();
            return false;
        }
        return true;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int oldYear, int newYear) {
        enterYearSelected = Integer.toString(newYear);
    }

    private class ViewTextWatcher implements TextWatcher {
        private EditText mView; // 声明一个编辑框对象
        private int mMaxLength; // 声明一个最大长度变量
        public ViewTextWatcher(EditText v, int maxLength) {
            super();
            mView = v;
            mMaxLength = maxLength;
        }
        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mView.getId() == R.id.et_register_first || mView.getId() == R.id.et_register_second || mView.getId() == R.id.et_register_username) {
                firstPwd = et_register_first.getText().toString();
                secondPwd = et_register_second.getText().toString();
                userName = et_register_userName.getText().toString();
                btn_register_commit.setEnabled(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(genderSelected)
                                                && !TextUtils.isEmpty(userTypeSelected) && !TextUtils.isEmpty(institutionSelected)
                                                && !TextUtils.isEmpty(departmentSelected) && !TextUtils.isEmpty(enterYearSelected)
                                                && !TextUtils.isEmpty(firstPwd) && !TextUtils.isEmpty(secondPwd));
                Log.d("register", userName+genderSelected+userTypeSelected+institutionSelected+departmentSelected+enterYearSelected+firstPwd+secondPwd);//登录按钮
            }
        }
        // 在编辑框的输入文本变化后触发
        @Override
        public void afterTextChanged(Editable editable) {
            String str = editable.toString(); // 获得已输入的文本字符串

        }


    }



}