package com.wfql.client.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.wfql.client.R;
import com.wfql.client.content.LoginContent;
import com.wfql.client.dao.AdminDao;
import com.wfql.client.dao.LoginDBHelper;
import com.wfql.client.dao.LoginDao;
import com.wfql.client.entity.Login;
import com.wfql.client.entity.Loginbak;
import com.wfql.client.util.YNAlertDialogUtil;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener, AdapterView.OnItemSelectedListener, View.OnTouchListener {
    private AutoCompleteTextView et_login_userid;
    private EditText et_login_pwd;
    private ImageButton btn_del_id;
    private ImageButton btn_del_pwd;
    private Button btn_login;
    private ActivityResultLauncher<Intent> register;
    private Button btn_register;
    private Button btn_forget_pwd;
    private CheckBox ck_remember_pwd;
    private CheckBox ck_show_pwd;
    private Button btn_via_verify;
    private String phone;
    private String userid;
    private String pwd;
    private Spinner sp_userid;
    private List<String> loginidArray;
    private SharedPreferences pref_login;
    //private LoginDBHelper loginDBHelper;
    private boolean isremember;
    private Button btn_clear_login;
    private ArrayAdapter<String> loginidAdapter;
    private AdminDao adminDao;
    private LoginDao loginDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        btn_del_id = findViewById(R.id.btn_del_id);
        btn_del_pwd = findViewById(R.id.btn_del_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_via_verify = findViewById(R.id.btn_via_verify);
        btn_forget_pwd = findViewById(R.id.btn_forget_pwd);
        ck_remember_pwd = findViewById(R.id.ck_remember_pwd);
        ck_show_pwd = findViewById(R.id.ck_show_pwd);
        et_login_userid = findViewById(R.id.et_login_userid);
        et_login_pwd = findViewById(R.id.et_login_pwd);
        btn_clear_login = findViewById(R.id.btn_clear_login);

        //样式重调
        Drawable ic_user_info = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_user_id, null);
        Drawable ic_user_pwd = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_user_pwd, null);
        ic_user_info.setBounds(0,0,40,40);
        ic_user_pwd.setBounds(0, 0, 40, 40);
        et_login_userid.setCompoundDrawables(ic_user_info, null, null, null);
        et_login_pwd.setCompoundDrawables(ic_user_pwd, null ,null, null);

        SpannableString content = new SpannableString(btn_clear_login.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        btn_clear_login.setText(content);



        //OnClick
        btn_del_id.setOnClickListener(this);
        btn_del_pwd.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_forget_pwd.setOnClickListener(this);
        btn_via_verify.setOnClickListener(this);
        btn_clear_login.setOnClickListener(this);

        //OnCheckChange
        ck_remember_pwd.setOnCheckedChangeListener(this);
        ck_show_pwd.setOnCheckedChangeListener(this);

        //OnFocusChange
        et_login_userid.setOnFocusChangeListener(this);
        et_login_pwd.setOnFocusChangeListener(this);

        //OnTouch
        et_login_userid.setOnTouchListener(this);

        //TextChange
        et_login_userid.addTextChangedListener(new CodeTextWatcher(et_login_userid, 11));
        et_login_pwd.addTextChangedListener(new CodeTextWatcher(et_login_pwd, 18));

        //ItemSelected
        //sp_userid.setOnItemSelectedListener(this);

        //使用共享参数存储最后登录的用户名
        pref_login = getSharedPreferences("share_login", Context.MODE_PRIVATE);

        //获取数据库实例
        adminDao = MyApplication.getInstance().getAdminDatabase().adminDao();
        loginDao = MyApplication.getInstance().getLoginDatabase().loginDao();

        //开启连接
//        loginDBHelper = LoginDBHelper.getInstance(this);
//        loginDBHelper.openLink();

        //重新进入首页
        refreshLogin();

        //返回结果
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
//        loginDBHelper.openLink();
        refreshLogin();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        loginDBHelper.closeLink();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        loginDBHelper.openLink();
        refreshLogin();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        loginDBHelper.closeLink();
    }

    private void refreshLogin() {
        //重新加载输入框提示列表
//        loginidArray = loginDBHelper.queryLoginIdArrayList();
        loginidArray = loginDao.queryLoginIdArrayList();
        loginidAdapter = new ArrayAdapter<>(this, R.layout.stringarray_select, loginidArray);
        et_login_userid.setAdapter(loginidAdapter);
        et_login_userid.setSelection(0);

        //将最后一次的登录的用户账号显示在输入框
        String userid = pref_login.getString("userid", "");
        et_login_userid.setText(userid);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //et_login_userid.setText(useridArray[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP){
            if (view.getId() == R.id.et_login_userid){
                et_login_userid.showDropDown();
            }
        }

        return false;
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
            if (mView.getId() == R.id.et_login_userid || mView.getId() == R.id.et_login_pwd) {
                userid = et_login_userid.getText().toString();
                pwd = et_login_pwd.getText().toString();
                btn_login.setEnabled(!TextUtils.isEmpty(userid) && !TextUtils.isEmpty(pwd));    //登录按钮
            }
        }
        // 在编辑框的输入文本变化后触发
        @Override
        public void afterTextChanged(Editable editable) {
            String str = editable.toString(); // 获得已输入的文本字符串
            if (mView.getId() == R.id.et_login_userid) {
                if (str.length() == 0){ //未输入提示账户列表
                    btn_del_id.setVisibility(View.GONE);
                } else {
                    btn_del_id.setVisibility(View.VISIBLE);
                }
                Login mlogin = null;
                try {   //根据账号查找并自动填入密码信息
//                    loginDBHelper = LoginDBHelper.getInstance(LoginActivity.this);
//                    mlogin = loginDBHelper.queryByLoginId(userid);      //查找列表中的账号
                    mlogin = loginDao.queryByLoginId(userid);
                    if (mlogin != null) {
                        if (mlogin.isRemember()) {
                            et_login_pwd.setText(mlogin.getLoginPwd());
                            ck_remember_pwd.setChecked(true);
                            btn_clear_login.setVisibility(View.VISIBLE);
                            btn_del_pwd.setVisibility(View.GONE);
                        } else {
                            et_login_pwd.setText("");
                            ck_remember_pwd.setChecked(false);
                            btn_clear_login.setVisibility(View.VISIBLE);
                            btn_del_pwd.setVisibility(View.GONE);
                        }
                    } else {
                        btn_clear_login.setVisibility(View.GONE);
                    }

                } catch (Exception e){
                    Log.e("TAG", "哎呀，查找账号记录出错了", e);
                }
            } else if (mView.getId() == R.id.et_login_pwd) {
                btn_del_pwd.setVisibility(str.length() >= 1 ? View.VISIBLE : View.GONE);
            }
        }


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_login){ //点击“登录”
            userid = et_login_userid.getText().toString();
            pwd = et_login_pwd.getText().toString();
            isremember = ck_remember_pwd.isChecked();
            //if (userid.equals("31195182") && pwd.equals("123455")) {
                //记录登录信息
                recordLogin();
            //}
            //验证数据库中的账号密码，成功则判断用户类型进入普通用户界面或管理员界面


            //Intent intentMain = new Intent(this, AskLeaveActivity.class);
            //startActivity(intentMain);
        } else if(id == R.id.btn_register){ //点击“注册”
            Intent intentRegister = new Intent(this, RegisterActivity.class);
            startActivity(intentRegister);
        } else if(id == R.id.btn_forget_pwd){ //点击“忘记密码”
            phone = et_login_userid.getText().toString();
            if (TextUtils.isEmpty(phone) || phone.length() != 11 || phone.toCharArray()[0] != '1') { //错误的手机格式
                et_login_userid.requestFocus();
                btn_del_id.setVisibility(View.VISIBLE);
                Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            } else {    //手机传入“忘记密码”界面
                btn_del_id.setVisibility(View.GONE);
                Intent intentForgetPwd = new Intent(this, ForgetPwdActivity.class);
                intentForgetPwd.putExtra("phone", phone);
                register.launch(intentForgetPwd);
            }
        } else if(id == R.id.btn_del_id) {  //清空账号输入框
            btn_del_id.setVisibility(View.GONE);
            et_login_userid.setText("");
        } else if (id == R.id.btn_del_pwd) { //清空密码输入框
            et_login_pwd.setText("");
            btn_del_pwd.setVisibility(View.GONE);
        } else if (id == R.id.btn_via_verify) { //切换验证码登录
            phone = et_login_userid.getText().toString();
            if (TextUtils.isEmpty(phone) || phone.length() != 11 || phone.toCharArray()[0] != '1') {
                et_login_userid.requestFocus();
                btn_del_id.setVisibility(View.VISIBLE);
                Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            } else {
                btn_del_id.setVisibility(View.GONE);
                Intent intentVerifyLogin = new Intent(this, VerifyLoginActivity.class);
                intentVerifyLogin.putExtra("phone", phone);
                register.launch(intentVerifyLogin);
            }
        } else if (id == R.id.btn_clear_login) {    //清除当前账号登录记录
            //登录列表的信息
            YNAlertDialogUtil dialogUtil = new YNAlertDialogUtil(this);
            AlertDialog.Builder builder = dialogUtil.createBuilder(LoginActivity.this, "", "是否清除该条登录记录", "清除", "取消");
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if (dialogUtil.dialogResult){
//                        loginDBHelper.deleteByLoginId(userid);
                        loginDao.deleteByLoginId(userid);
                        et_login_userid.setText("");
                        et_login_pwd.setText("");
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            //共享参数中的信息
            if(userid == pref_login.getString("userid", "")){
                SharedPreferences.Editor editor = pref_login.edit();
                editor.putString("userid", "");
                editor.putString("password", "");
                editor.putBoolean("isRemember", false);
                editor.commit();
            }

        } else {
            //
        }

    }

    //登录成功
    private void recordLogin() {
        SharedPreferences.Editor editor = pref_login.edit();
        editor.putString("userid", et_login_userid.getText().toString());
        editor.commit();

        //Room框架下存数据操作
        Login login = new Login();
        login.setRemember(isremember);
        login.setLoginId(userid);
        login.setLoginPwd(pwd);
        //loginDBHelper.insert(login);  //Helper方式存数据
        loginDao.deleteByLoginId(userid);
        loginDao.insert(login);

//        ContentValues values = new ContentValues();
//        values.put("loginId", userid);
//        values.put("loginPwd", pwd);
//        values.put("remember", isremember);
//        getContentResolver().insert(LoginContent.CONTENT_URI, values);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (compoundButton.getId() == R.id.ck_show_pwd) { //显示密码或隐藏密码
            et_login_pwd.setInputType(isChecked ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                    InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            et_login_pwd.setSelection(et_login_pwd.getText().length());
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (view.getId() == R.id.et_login_pwd && hasFocus ) {  //监听焦点从账号框转换到密码框
            btn_del_id.setVisibility(View.GONE);
            btn_del_pwd.setVisibility(TextUtils.isEmpty((et_login_pwd.getText().toString())) ? View.GONE : View.VISIBLE);
        } else if (view.getId() == R.id.et_login_userid && hasFocus) {
            btn_del_pwd.setVisibility(View.GONE);
            btn_del_id.setVisibility(TextUtils.isEmpty((et_login_userid.getText().toString())) ? View.GONE : View.VISIBLE);
        }
    }
}