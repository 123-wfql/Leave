package com.wfql.client.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wfql.client.R;

import java.util.Random;

public class ForgetPwdActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_fgtpwd_phone;
    private EditText et_fgtpwd_first;
    private EditText et_fgtpwd_second;
    private EditText et_fgtpwd_verify;
    private Button btn_fgtpwd_getverifycode;
    private Button btn_fgtpwd_change;
    private Button btn_fgtpwd_cancel;
    private String phone;
    private String real_verifyCode;
    private String pwd_first;
    private String pwd_second;
    private String now_verifyCode;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);


        et_fgtpwd_phone = findViewById(R.id.et_fgtpwd_phone);
        et_fgtpwd_first = findViewById(R.id.et_fgtpwd_first);
        et_fgtpwd_second = findViewById(R.id.et_fgtpwd_second);
        et_fgtpwd_verify = findViewById(R.id.et_fgtpwd_verify);
        btn_fgtpwd_getverifycode = findViewById(R.id.btn_fgtpwd_getverifycode);
        btn_fgtpwd_change = findViewById(R.id.btn_fgtpwd_change);
        btn_fgtpwd_cancel = findViewById(R.id.btn_fgtpwd_cancel);
        tv_title = findViewById(R.id.tv_title);

        tv_title.setText("修改密码");


        //OnClick
        btn_fgtpwd_getverifycode.setOnClickListener(this);
        btn_fgtpwd_change.setOnClickListener(this);
        btn_fgtpwd_cancel.setOnClickListener(this);

        //TextChange
        //et_fgtpwd_phone.addTextChangedListener(new CodeTextWatcher(et_fgtpwd_phone, 11));
        et_fgtpwd_first.addTextChangedListener(new CodeTextWatcher(et_fgtpwd_first, 18));
        et_fgtpwd_second.addTextChangedListener(new CodeTextWatcher(et_fgtpwd_second, 18));
        et_fgtpwd_verify.addTextChangedListener(new CodeTextWatcher(et_fgtpwd_verify, 6));



        //获取上一个页面的数据
        Bundle bundle = getIntent().getExtras();
        String phone = bundle.getString("phone");
        et_fgtpwd_phone.setText(phone);
        et_fgtpwd_phone.setEnabled(false);
        real_verifyCode = "";
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_fgtpwd_change) { //点击“重设密码”
            //获取数据：手机号，两个密码，验证码
            phone = et_fgtpwd_phone.getText().toString();
            pwd_first = et_fgtpwd_first.getText().toString();
            pwd_second = et_fgtpwd_second.getText().toString();
            now_verifyCode = et_fgtpwd_verify.getText().toString();

            //验证验证码
            if (now_verifyCode.equals(real_verifyCode)) {   //验证验证码正确性
                if( pwd_first.length() >= 6 ) {
                    if (pwd_first.equals(pwd_second)) { //验证两次密码一致性
                        //将新密码保存到数据库

                        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {    //密码不一致
                        Toast.makeText(this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入6位以上的密码", Toast.LENGTH_SHORT).show();
                }
            } else { //验证码错误
                Toast.makeText(this, "验证码错误，请重新输入", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.btn_fgtpwd_cancel) { //点击“取消”
            finish();
        } else if (id == R.id.btn_fgtpwd_getverifycode) {
            real_verifyCode = String.format("%06d", new Random().nextInt(999999));
            phone = et_fgtpwd_phone.getText().toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("收到验证码");
            builder.setMessage("手机号：" + phone + "\n本次验证码为" + real_verifyCode + "，请输入验证码");
            builder.setPositiveButton("好的", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {

        }
    }

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
            if (mView.getId() == R.id.et_fgtpwd_first || mView.getId() == R.id.et_fgtpwd_second || mView.getId() == R.id.et_fgtpwd_verify) {
                pwd_first = et_fgtpwd_first.getText().toString();
                pwd_second = et_fgtpwd_second.getText().toString();
                now_verifyCode = et_fgtpwd_verify.getText().toString();
                //确保新密码、验证密码、验证码框都不为空
                btn_fgtpwd_change.setEnabled(!TextUtils.isEmpty(pwd_first) && !TextUtils.isEmpty(pwd_second)
                        && !TextUtils.isEmpty(now_verifyCode));
            }
        }
        // 在编辑框的输入文本变化后触发
        @Override
        public void afterTextChanged(Editable editable) {

        }


    }
}