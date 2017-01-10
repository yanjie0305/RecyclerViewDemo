package com.example.eventbusdemo;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${郭艳杰} on 2017/1/9.
 */

public class SmsContentObserver extends ContentObserver {
    private final Handler handler;
    private final Context context;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SmsContentObserver(Handler handler, Context context) {
        super(handler);
        this.handler = handler;
        this.context = context;

    }
    public void onChange(boolean selfChange){
        super.onChange(selfChange);
        Uri smsUri = Uri.parse("content://sms/inbox");
        Cursor cursor = context.getContentResolver().query(smsUri, null, null, null, "date desc");
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor.moveToNext()){
            //String address = cursor.getString(cursor.getColumnIndex("address"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            stringBuilder.append(body);

        }
        Pattern pattern = Pattern.compile("(\\d{6})");//正则表达式
        Matcher matcher = pattern.matcher(stringBuilder.toString());
        if (matcher.find()) {
            String code = matcher.group(0);
            handler.obtainMessage(0, code).sendToTarget();
        }

    }
}
