package com.wfql.client.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.wfql.client.entity.Loginbak;

import java.util.ArrayList;
import java.util.List;

public class LoginDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "login.db";
    private static final int DB_VERSION = 1;
    public static final String TABLENAME = "login_info";
    private static LoginDBHelper mHelper = null;
    private SQLiteDatabase mDB = null;

    //创建子类调用父类
    public LoginDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //单例模式获取唯一实例
    public static LoginDBHelper getInstance(Context context) {
        if (mHelper == null){
            mHelper = new LoginDBHelper(context);
        }
        return mHelper;
    }

    public SQLiteDatabase openLink() {
        if (mDB == null || !mDB.isOpen()) {
            try {
                mDB = mHelper.getWritableDatabase();
                // do something with the database
            } catch (SQLException e) {
                Log.e("TAG", "Error while getting writable database", e);
            }
        }
        return mDB;
    }

    public void closeLink() {
        if (mDB != null && mDB.isOpen()) {
            mDB.close();
            mDB = null;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLENAME + " (" +
                "loginid VARCHAR PRIMARY KEY NOT NULL," +
                " loginpwd VARCHAR NOT NULL," +
                " isremember INTERGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public long insert(Loginbak login) {   //插入数据
        ContentValues values = new ContentValues();
        values.put("loginid", login.getLoginId());
        values.put("loginpwd", login.getLoginPwd());
        values.put("isremember", login.isRemember());
        mDB.delete(TABLENAME, "loginid="+login.getLoginId(), null);
        return mDB.insert(TABLENAME, null, values);
    }

    public long deleteByLoginId(String loginid){    //通过账号删除记录
        return mDB.delete(TABLENAME, "loginid=?", new String[]{loginid});
    }

    public long update(Loginbak login) {   //修改记录
        ContentValues values = new ContentValues();
        values.put("loginid", login.getLoginId());
        values.put("loginpwd", login.getLoginPwd());
        values.put("isremember", login.isRemember());
        return mDB.update(TABLENAME, values, "loginid=?", new String[]{login.getLoginId()});
    }

    public List<Loginbak> queryAll() {     //查询所有
        List<Loginbak> list = new ArrayList<>();
        Cursor cursor = mDB.query(TABLENAME, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            Loginbak login = new Loginbak();
            login.setLoginId(cursor.getString(0));
            login.setLoginPwd(cursor.getString(1));
            login.setRemember(cursor.getInt(2) == 1 );
            list.add(login);
        }
        return list;
    }

    public List<String> queryLoginIdArrayList() {   //查询所有账号并返回列表
        List<String> list = new ArrayList<>();
        Cursor cursor = mDB.query(TABLENAME, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            String loginid = cursor.getString(0);
            list.add(loginid);
        }
        return list;
    }

    public Loginbak queryByLoginId(String loginid){    //通过账号查询
        Loginbak mlogin = null;
        List<Loginbak> list = new ArrayList<>();
        Cursor cursor = mDB.query(TABLENAME, null, "loginid=?", new String[]{loginid}, null, null, null);
        while(cursor.moveToNext()){
            Loginbak login = new Loginbak();
            login.setLoginId(cursor.getString(0));
            login.setLoginPwd(cursor.getString(1));
            login.setRemember(cursor.getInt(2) == 1 );
            list.add(login);
        }
        if (list.size() > 0) {
            mlogin = list.get(0);
            return mlogin;
        }
        return mlogin;
    }


}
