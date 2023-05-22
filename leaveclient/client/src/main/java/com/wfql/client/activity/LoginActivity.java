package com.wfql.client.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import android.widget.Toast;

import com.wfql.client.R;
import com.wfql.client.content.DepartmentContent;
import com.wfql.client.content.InstitutionContent;
import com.wfql.client.content.LoginContent;
import com.wfql.client.content.UserContent;
import com.wfql.client.util.TextWatcherUtil;
import com.wfql.client.util.YNAlertDialogUtil;

import java.util.ArrayList;
import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;

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
    private Button btn_clear_login;


    private String phone;
    private String loginId;
    private String loginPwd;
    private boolean remember;
    private List<String> loginIdArray;
    private ArrayAdapter<String> loginidAdapter;
    //private SharedPreferences pref_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        // 初始化控件
        initialize();
        // 设置样式
        setViewStyles();
        // 设置文本变化监听器
        setTextChangeListeners();





        //返回结果
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        });

    }

//    private void resetLastLoginInfo() {
//        String lastloginId = pref_login.getString("loginId", "");
//        et_login_userid.setText(lastloginId);
//        setLoginInfoById(lastloginId);
//    }

    private void setTextChangeListeners() {
        et_login_userid.addTextChangedListener(new CodeTextWatcher(et_login_userid, 11));
        et_login_userid.addTextChangedListener(new TextWatcherUtil(et_login_userid, 11));
        et_login_pwd.addTextChangedListener(new CodeTextWatcher(et_login_pwd, 18));
    }

    private void setViewStyles() {
        //图标
        Drawable ic_user_info = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_user_id, null);
        Drawable ic_user_pwd = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_user_pwd, null);
        ic_user_info.setBounds(0,0,40,40);
        ic_user_pwd.setBounds(0, 0, 40, 40);
        et_login_userid.setCompoundDrawables(ic_user_info, null, null, null);
        et_login_pwd.setCompoundDrawables(ic_user_pwd, null ,null, null);
        //字体下划线
        SpannableString content = new SpannableString(btn_clear_login.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        btn_clear_login.setText(content);
    }

    private void initialize() {
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
        //pref_login = getSharedPreferences("share_login", Context.MODE_PRIVATE);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListeners();
        //resetLastLoginInfo();
        refreshLoginIdArray();
        if (loginidAdapter.getCount() > 0) {
            et_login_userid.setText(loginidAdapter.getItem(loginidAdapter.getCount() - 1));
        }
        et_login_userid.setSelection(0);
        setLoginInfoById(et_login_userid.getText().toString());

        Intent intent = getIntent();
        Class<?> sourceActivityClass = (Class<?>) intent.getSerializableExtra("sourceActivity");
        if (sourceActivityClass == RegisterActivity.class) {
            et_login_userid.setText(intent.getStringExtra("userId"));
            et_login_pwd.setText(intent.getStringExtra("userPwd"));
        }


    }

    private void setListeners() {
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

        //OnItemSelected
        //sp_userid.setOnItemSelectedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    private void refreshLoginIdArray() {
        //重新加载输入框提示列表
        Uri uri = Uri.parse(LoginContent.CONTENT_URI_TABLE);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int loginIdColumnIndex = cursor.getColumnIndexOrThrow("loginId");
            loginIdArray = new ArrayList<>();
            do {
                String loginId = cursor.getString(loginIdColumnIndex);
                loginIdArray.add(loginId);
                Log.d("query", "the cursor "+ loginId + " has added to Array");
            } while (cursor.moveToNext());
        } else loginIdArray = new ArrayList<>();
        cursor.close();
        loginidAdapter = new ArrayAdapter<>(this, R.layout.stringarray_select, loginIdArray);
        et_login_userid.setAdapter(loginidAdapter);
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
                if(loginIdArray != null){
                    et_login_userid.showDropDown();
                }
            }
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_login){ //点击“登录”
            if(checkLogin()){
                Log.d("check", "校验密码成功");
                if(recordLogin()){
                    Log.d("record", "记录登录信息成功");
                    if(recordUserToApp()){
                        successLogin();
                    }

                }

            } else {
                Log.d("check", "校验密码失败");
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }

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
            //删除登录列表的信息
            YNAlertDialogUtil dialogUtil = new YNAlertDialogUtil(this);
            AlertDialog.Builder builder = dialogUtil.createBuilder(LoginActivity.this, "", "是否清除该条登录记录", "清除", "取消");
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if (dialogUtil.dialogResult){
                        Uri uri = Uri.parse(LoginContent.CONTENT_URI_SQL + "");
                        int rowsDeleted = getContentResolver().delete(uri, "loginId=?", new String[]{loginId});
                        //int rowsDeleted = getContentResolver().delete(uri, null, null);
                        et_login_userid.setText("");
                        et_login_pwd.setText("");
                        Toast.makeText(LoginActivity.this, "删除了"+Integer.toString(rowsDeleted)+"条记录", Toast.LENGTH_SHORT).show();
                        refreshLoginIdArray();
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

//            //删除共享参数中的信息
//            if(loginId.equals(pref_login.getString("loginId", ""))){
//                SharedPreferences.Editor editor = pref_login.edit();
//                editor.putString("loginId", "");
//                editor.putString("password", "");
//                editor.putBoolean("isRemember", false);
//                editor.commit();
//            }

        } else {
            //
        }

    }

    private boolean recordUserToApp() {
        Uri uri = Uri.parse(UserContent.CONTENT_URI_SQL + "");
        Cursor cursor = getContentResolver().query(uri, null, "userId=?", new String[]{loginId}, null);
        if (cursor != null && cursor.getCount() > 0) {
            //获取各个属性
            cursor.moveToFirst();
            int userIdColumnIndex = cursor.getColumnIndexOrThrow("userId");
            int userNameColumnIndex = cursor.getColumnIndexOrThrow("userName");
            int verifyPhoneColumnIndex = cursor.getColumnIndexOrThrow("verifyPhone");
            int userGenderColumnIndex = cursor.getColumnIndexOrThrow("userGender");
            int enterYearColumnIndex = cursor.getColumnIndexOrThrow("enterYear");
            int iconUrlColumnIndex = cursor.getColumnIndexOrThrow("iconUrl");
            int checkerLvColumnIndex = cursor.getColumnIndexOrThrow("checkerLv");
            int userRemarkColumnIndex = cursor.getColumnIndexOrThrow("userRemark");
            int departmentColumnIndex = cursor.getColumnIndexOrThrow("department");
            int governLvColumnIndex = cursor.getColumnIndexOrThrow("governLv");
            int jobColumnIndex = cursor.getColumnIndexOrThrow("job");
            String userId = cursor.getString(userIdColumnIndex);
            String userName = cursor.getString(userNameColumnIndex);
            String verifyPhone = cursor.getString(verifyPhoneColumnIndex);
            String userGender = cursor.getString(userGenderColumnIndex);
            int enterYear = cursor.getInt(enterYearColumnIndex);
            String iconUrl = cursor.getString(iconUrlColumnIndex);
            int checkerLv = cursor.getInt(checkerLvColumnIndex);
            String userRemark = cursor.getString(userRemarkColumnIndex);
            String department = cursor.getString(departmentColumnIndex);
            int governLv = cursor.getInt(governLvColumnIndex);
            String job = cursor.getString(jobColumnIndex);
            cursor.close();
            //根据部门级信息查找机构及用户类型
            String institution = "";
            String userType = "";
            Uri uriDep = Uri.parse(DepartmentContent.CONTENT_URI_SQL + "");
            Cursor cursorDep = getContentResolver().query(uriDep, null, "department=?", new String[]{department}, null);
            cursorDep.moveToFirst();
            if (cursorDep != null && cursorDep.getCount() > 0){
                int institutionColumnIndex = cursorDep.getColumnIndexOrThrow("institution");
                institution = cursorDep.getString(institutionColumnIndex);
            }
            cursorDep.close();
            Uri uriIns = Uri.parse(InstitutionContent.CONTENT_URI_SQL + "");
            Cursor cursorIns = getContentResolver().query(uriIns, null, "institution=?", new String[]{institution}, null);
            cursorIns.moveToFirst();
            if (cursorIns != null && cursorIns.getCount() > 0){
                int userTypeColumnIndex = cursorIns.getColumnIndexOrThrow("userType");
                userType = cursorIns.getString(userTypeColumnIndex);
            }
            cursorIns.close();
            //将属性打包到app的全局变量
            ClientApplication clientApplication = ClientApplication.getInstance();
            clientApplication.infoMap.put("userId", userId);
            clientApplication.infoMap.put("userName", userName);
            clientApplication.infoMap.put("verifyPhone", verifyPhone);
            clientApplication.infoMap.put("userGender", userGender);
            clientApplication.infoMap.put("enterYear", Integer.toString(enterYear));
            clientApplication.infoMap.put("iconUrl", iconUrl);
            clientApplication.infoMap.put("checkerLv", Integer.toString(checkerLv));
            clientApplication.infoMap.put("userRemark", userRemark);
            clientApplication.infoMap.put("department", department);
            clientApplication.infoMap.put("governLv", Integer.toString(governLv));
            clientApplication.infoMap.put("job", job);
            clientApplication.infoMap.put("institution", institution);
            clientApplication.infoMap.put("userType", userType);

        } else {
            Toast.makeText(LoginActivity.this, "找不到该账号", Toast.LENGTH_SHORT).show();
            cursor.close();
            return false;
        }
        return true;
    }

    private boolean checkLogin() {
        loginId = et_login_userid.getText().toString();
        loginPwd = et_login_pwd.getText().toString();
        remember = ck_remember_pwd.isChecked();
        String userPwd = "";
        Uri uri = Uri.parse(UserContent.CONTENT_URI_SQL + "");
        Cursor cursor = getContentResolver().query(uri, null, "userId=?", new String[]{loginId}, null);
        if (cursor != null && cursor.getCount() > 0) {
            Log.d("check", "cursor is not null");
            cursor.moveToFirst();
            int userPwdColumnIndex = cursor.getColumnIndexOrThrow("userPwd");
            userPwd = cursor.getString(userPwdColumnIndex);
            Log.d("check", userPwd);
        } else {
            Toast.makeText(LoginActivity.this, "找不到该账号", Toast.LENGTH_SHORT).show();
            cursor.close();
            return false;
        }
        cursor.close();
        if (BCrypt.verifyer().verify(loginPwd.toCharArray(), userPwd).verified) {
            Log.d("check", "is same");
            return true;
        } else {
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            return false;
        }



    }

    private void successLogin() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }

    //登录成功
    private boolean recordLogin() {
//        //使用共享参数
//        SharedPreferences.Editor editor = pref_login.edit();
//        editor.putString("loginId", et_login_userid.getText().toString());
//        editor.commit();

        //使用contentProvider传输数据
        ContentValues values = new ContentValues();
        values.put("loginId", loginId);
        values.put("loginPwd", loginPwd);
        values.put("remember", remember);
        Uri uri = Uri.parse(LoginContent.CONTENT_URI_SQL + "");
        int deleteColumn = getContentResolver().delete(uri, "loginId=?", new String[]{loginId});
        uri = Uri.parse(LoginContent.CONTENT_URI_TABLE);
        getContentResolver().insert(uri, values);
        return true;

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

    private void setLoginInfoById(String loginId) {
        try {
            Log.d("query", "loginId is " + loginId);
            Uri uri = Uri.parse(LoginContent.CONTENT_URI_SQL);
            Log.d("query", "uri is " + uri);
            Cursor cursor = getContentResolver().query(uri, null, "loginId=?", new String[]{loginId}, null);
            if (cursor != null && cursor.getCount() > 0) {
                Log.d("query", "cursor from query is not null");
                cursor.moveToFirst();
                int isRememberColumnIndex = cursor.getColumnIndexOrThrow("remember");
                int remember = cursor.getInt(isRememberColumnIndex);
                Log.d("query", "remember is "+ remember);
                int loginPwdColumnIndex = cursor.getColumnIndexOrThrow("loginPwd");
                String loginPwd = cursor.getString(loginPwdColumnIndex);
                if (remember == 1){
                    et_login_pwd.setText(loginPwd);
                } else et_login_pwd.setText("");
                ck_remember_pwd.setChecked(remember == 1);
                cursor.close();
                btn_clear_login.setVisibility(View.VISIBLE);
            } else {
                btn_clear_login.setVisibility(View.GONE);
            }
        } catch (Exception e){
            Log.e("wfql", "哎呀，查找账号记录出错了", e);
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
            if (mView.getId() == R.id.et_login_userid || mView.getId() == R.id.et_login_pwd) {
                loginId = et_login_userid.getText().toString();
                loginPwd = et_login_pwd.getText().toString();
                btn_login.setEnabled(!TextUtils.isEmpty(loginId) && !TextUtils.isEmpty(loginPwd));    //登录按钮
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
                    setLoginInfoById(loginId);
                }


            } else if (mView.getId() == R.id.et_login_pwd) {
                btn_del_pwd.setVisibility(str.length() >= 1 ? View.VISIBLE : View.GONE);
            }
        }


    }
}