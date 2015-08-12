package com.lugeek.schoolevents.utils.mylog;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lugeek on 2015/8/10.
 */
public class mylog {
    public static final boolean ISLOG = true;

    public static void logw(String name, String value){
        if (ISLOG){
            Log.w(name, value);
        }
    }

    public static void toast(Context context, String str){
        if (ISLOG){
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }
}
