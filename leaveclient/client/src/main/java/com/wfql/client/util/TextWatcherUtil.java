package com.wfql.client.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TextWatcherUtil implements TextWatcher {
    private EditText mView; // 声明一个编辑框对象
    private int mMaxLength; // 声明一个最大长度变量

    public TextWatcherUtil(EditText v, int maxLength) {
        super();
        mView = v;
        mMaxLength = maxLength;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
