package com.frank.protean.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import com.frank.protean.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;

public class SynchronizedActivity extends BaseActivity {
    public static final String TAG = "SynchronizedActivity";
    String data = "{\t\"url\": \"https:\\/\\/cms-api.sgmlink.com\\/ue\\/index.html?sid=5b5efe35c3b83&cmid=5b5efe4177f3a&cf=content&id=5b5f02d159922966790978c9\"}";
    String url = "https%3A%2F%2Fcms-api.sgmlink.com%2Fue%2Findex.html%3Fsid%3D5b5efe35c3b83%26cmid%3D5b5efe4177f3a%26cf%3Dcontent%26id%3D5c231c9c00147d96099bf879";
    String url64 = "aHR0cHM6Ly9jbXMtYXBpLnNnbWxpbmsuY29tL3VlL2luZGV4Lmh0bWw/c2lkPTViNWVmZTM1YzNiODMmYW1wO2NtaWQ9NWI1ZWZlNDE3N2YzYSZhbXA7Y2Y9Y29udGVudCZhbXA7aWQ9NWI1ZjAyZDE1OTkyMjk2Njc5MDk3OGM5";
    String h5Url = "{\"errno\":0,\"message\":\"success\",\"raw_text\":\"\\u4fe1\\u606f\\u5a31\\u4e50\\u7cfb\\u7edf\\u600e\\u4e48\\u66f4\\u65b0\\uff1f\",\"data_resource\":91,\"result\":[{\"data\":{\"url\":\"https:\\/\\/cms-api.sgmlink.com\\/ue\\/index.html?sid=5b5efe35c3b83&cmid=5b5efe4177f3a&cf=content&id=5b5f02d159922966790978c9\"},\"card_type\":\"electronic_manual\",\"intent\":\"FAQ_100068_ELECTIONBOOK\",\"tts_status\":{\"tts\":\"\\u4fe1\\u606f\\u5a31\\u4e50\\u7cfb\\u7edf\\u53ef\\u4ee5\\u901a\\u8fc7\\u65e0\\u7ebf\\u8fde\\u63a5\\u4e0b\\u8f7d\\u548c\\u5b89\\u88c5\\u6240\\u9009\\u7684\\u8f6f\\u4ef6\\u66f4\\u65b0\\uff0c\\u4e5f\\u53ef\\u4ee5\\u9009\\u62e9\\u624b\\u52a8\\u68c0\\u67e5\\u66f4\\u65b0\\u3002\"},\"bot_session\":{\"bot_id\":\"100068\",\"bot_session_id\":\"session-1547200238473-2428923745-8013-242\"}}],\"logid\":\"0574801903\",\"spend_time\":\"6201ms\",\"uuid\":\"qa_test_dumitest-2076997483\",\"time\":1547201381}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronized);
//        byte[] result = Base64.decode(url64,Base64.DEFAULT);
        startActivity(new Intent(this, WebViewActivity.class));

        String str = Uri.decode(url);
//        String str = new String(result);
        Log.d(TAG, "url:" + str);
//        try {
//            JSONObject jsonObject = new JSONObject(h5Url);
//            String result = jsonObject.getJSONArray("result").getJSONObject(0).getJSONObject("data").getString("url");
//            Log.d(TAG, "url:" + result);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        String result = Uri.decode(url).replaceAll("&amp;", "&");
//        Log.d(TAG, "url::" + result);
        Thread thread1 = new Thread(MainRunable.mainRunable);
        Thread thread2 = new Thread(MainRunable.mainRunable);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
            Log.e(TAG, "num::" + MainRunable.num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static class MainRunable implements Runnable {
        static MainRunable mainRunable = new MainRunable();
        static int num = 0;
        Object lock1 = new Object();
//        Object lock2 = new Object();

        @Override
        public void run() {
            synchronized (lock1) {
                Log.e(TAG, "我是lock1部分，我的线程名字叫" + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, Thread.currentThread().getName() + "运行结束!");
            }
//            synchronized (lock1) {
//                Log.e(TAG, "我是lock2部分，我的线程名字叫" + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Log.e(TAG, Thread.currentThread().getName() + "运行结束!");
//            }
            synchronized (MainRunable.class) {
                Log.e(TAG, "我是类锁，我的线程名字叫" + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, Thread.currentThread().getName() + "运行结束!");
            }

//            synchronized (mainRunable) {
//                for (int i = 0; i < 10000; i++) {
//                    num++;
//                }
//            }

        }
    }
}
