package com.client.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.client.entity.Login;

import java.util.ArrayList;
import java.util.List;

public class LoginDao extends SQLiteOpenHelper {

    private static final String DB_NAME = "login.db";
    private static final int DB_VERSION = 1;
    public static final String TABLENAME = "login_info";
    private static LoginDao mDao = null;
    private SQLiteDatabase mDB = null;

    //创建子类调用父类
    public LoginDao(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //单例模式获取唯一实例
    public static LoginDao getInstance(Context context) {
        if (mDao == null){
            mDao = new LoginDao(context);
        }
        return mDao;
    }

    public SQLiteDatabase openLink() {
        if (mDB == null || !mDB.isOpen()) {
            try {
                mDB = mDao.getWritableDatabase();
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

    public long insert(Login login) {   //插入
        ContentValues values = new ContentValues();
        values.put("loginid", login.getLoginid());
        values.put("loginpwd", login.getLoginpwd());
        values.put("isremember", login.getIsremember());
        mDB.delete(TABLENAME, "loginid="+login.getLoginid(), null);
        return mDB.insert(TABLENAME, null, values);
    }

    public long deleteByLoginId(String loginid){    //通过账号删除记录
        return mDB.delete(TABLENAME, "loginid=?", new String[]{loginid});
    }

    public long update(Login login) {   //修改记录
        ContentValues values = new ContentValues();
        values.put("loginid", login.getLoginid());
        values.put("loginpwd", login.getLoginpwd());
        values.put("isremember", login.getIsremember());
        return mDB.update(TABLENAME, values, "loginid=?", new String[]{login.getLoginid()});
    }

    public List<Login> queryAll() {
        List<Login> list = new ArrayList<>();
        Cursor cursor = mDB.query(TABLENAME, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            Login login = new Login();
            login.setLoginid(cursor.getString(0));
            login.setLoginpwd(cursor.getString(1));
            login.setIsremember(cursor.getInt(2) == 1 );
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

    public Login queryByLoginId(String loginid){
        Login mlogin = null;
        List<Login> list = new ArrayList<>();
        Cursor cursor = mDB.query(TABLENAME, null, "loginid=?", new String[]{loginid}, null, null, null);
        while(cursor.moveToNext()){
            Login login = new Login();
            login.setLoginid(cursor.getString(0));
            login.setLoginpwd(cursor.getString(1));
            login.setIsremember(cursor.getInt(2) == 1 );
            list.add(login);
        }
        if (list.size() > 0) {
            mlogin = list.get(0);
            return mlogin;
        }
        return mlogin;
    }


}
