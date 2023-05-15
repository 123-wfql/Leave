package com.wfql.server.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class YNAlertDialogUtil extends AlertDialog {
    public static boolean dialogResult = false;

    public YNAlertDialogUtil(Context context) {
        super(context);
    }

    public static Builder createBuilder(Context context, String title, String message, String positiveButtonText, String negativeButtonText) {
        Builder builder = new Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonText, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogResult = true;
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(negativeButtonText, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogResult = false;
                dialog.dismiss();
            }
        });
        return builder;



    }
}

