package com.wfql.client.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wfql.client.R;

import java.util.Random;

public class VerifyLoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {


    private Button btn_get_verifycode;
    private Button btn_via_pwd;
    private Button btn_login;
    private ImageButton btn_del_phone;
    private ImageButton btn_del_verifycode;
    private EditText et_verify_phone;
    private EditText et_verify_code;
    private String phone;
    private String verifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_login);


        btn_del_phone = findViewById(R.id.btn_del_phone);
        btn_del_verifycode = findViewById(R.id.btn_del_verifycode);
        btn_get_verifycode = findViewById(R.id.btn_get_verifycode);
        btn_via_pwd = findViewById(R.id.btn_via_pwd);
        btn_login = findViewById(R.id.btn_login);
        et_verify_phone = findViewById(R.id.et_verify_phone);
        et_verify_code = findViewById(R.id.et_verify_code);

        //OnClick
        btn_get_verifycode.setOnClickListener(this);
        btn_via_pwd.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_del_phone.setOnClickListener(this);
        btn_del_verifycode.setOnClickListener(this);

        //TextChange
        et_verify_phone.addTextChangedListener(new CodeTextWatcher(et_verify_phone, 11));
        et_verify_code.addTextChangedListener(new CodeTextWatcher(et_verify_code, 18));

        //OnFocus
        et_verify_phone.setOnFocusChangeListener(this);
        et_verify_code.setOnFocusChangeListener(this);

        //获取上一个页面的数据
        Bundle bundle = getIntent().getExtras();
        String phone = bundle.getString("phone");
        et_verify_phone.setText(phone);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_login){ //点击“登录”
            if ( phone.equals(et_verify_phone.getText().toString()) && verifyCode.equals(et_verify_code.getText().toString()) )  {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
            } else {
                Toast.makeText(this, "请输入正确的手机号和验证码", Toast.LENGTH_SHORT).show();
            }

        } else if(id == R.id.btn_get_verifycode) { //点击“获取验证码”
            //生成6位数验证码
            verifyCode = String.format("%06d", new Random().nextInt(999999));
            phone = et_verify_phone.getText().toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("收到验证码");
            builder.setMessage("手机号：" + phone + "\n本次验证码为" + verifyCode + "，请输入验证码");
            builder.setPositiveButton("好的", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if(id == R.id.btn_del_phone) { //清空手机号输入框
            et_verify_phone.setText("");
            btn_del_phone.setVisibility(View.GONE);
        } else if (id == R.id.btn_del_verifycode) { //清空验证码输入框
            et_verify_code.setText("");
            btn_del_verifycode.setVisibility(View.GONE);
        } else if (id == R.id.btn_via_pwd) { //切换密码登录
            Intent intentLogin = new Intent(this, LoginActivity.class);
            startActivity(intentLogin);

        } else {
            //
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (view.getId() == R.id.et_verify_code && hasFocus ) {  //监听焦点从账号框转换到密码框
            btn_del_phone.setVisibility(View.GONE);
            btn_del_verifycode.setVisibility(TextUtils.isEmpty((et_verify_code.getText().toString())) ? View.GONE : View.VISIBLE);
        } else if (view.getId() == R.id.et_verify_phone && hasFocus) {
            btn_del_verifycode.setVisibility(View.GONE);
            btn_del_phone.setVisibility(TextUtils.isEmpty((et_verify_phone.getText().toString())) ? View.GONE : View.VISIBLE);
        }
    }

    // 定义一个编辑框监听器
    private class CodeTextWatcher implements TextWatcher {
        private EditText mView; // 声明一个编辑框对象
        private int mMaxLength; // 声明一个最大长度变量

        public CodeTextWatcher(EditText v, int maxLength) {
            super();
            mView = v;
            mMaxLength = maxLength;
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mView.getId() == R.id.et_verify_phone || mView.getId() == R.id.et_verify_code) {
                String phone = et_verify_phone.getText().toString();
                String code = et_verify_code.getText().toString();
                btn_login.setEnabled(!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code));
            }
        }

        // 在编辑框的输入文本变化后触发
        @Override
        public void afterTextChanged(Editable editable) {
            String str = editable.toString(); // 获得已输入的文本字符串
            if (mView.getId() == R.id.et_verify_phone) { //框非空时显示清除按钮
                btn_del_phone.setVisibility(str.length() >= 1 ? View.VISIBLE : View.GONE);
            } else if (mView.getId() == R.id.et_verify_code) {
                btn_del_verifycode.setVisibility(str.length() >= 1 ? View.VISIBLE : View.GONE);
            }
        }
    }
}