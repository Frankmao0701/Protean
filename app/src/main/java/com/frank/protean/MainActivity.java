package com.frank.protean;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.frank.protean.activity.CoordinatorActivity;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    public static final String TAG = "MainActivity";
    private Handler checkHandler;
    public static final String UPLOAD_RESULT = "com.zhy.blogcodes.intentservice.UPLOAD_RESULT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mRequestQueue = Volley.newRequestQueue(this);
//        loadData();
        Log.i(TAG,"aaaa");
        Log.i(TAG,"bbbb");
        Log.i(TAG,"bbbb");
        registerReceiver();
        Log.d("MainActivity","Mainname::"+Thread.currentThread().getName());
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWindow(v);
            }
        });
        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showWindow(v);
                Intent intent = new Intent(MainActivity.this, CoordinatorActivity.class);
                startActivity(intent);
            }
        });
    }
    private void registerReceiver()
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_RESULT);
        registerReceiver(uploadImgReceiver, filter);
    }


    private BroadcastReceiver uploadImgReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {

            if (intent.getAction() == UPLOAD_RESULT)
            {
                Log.d("MainActivity","onReceiver::"+Thread.currentThread().getName());

            }

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
//        checkHandler.sendEmptyMessageDelayed(110, 1000);
    }

    private void loadData() {
        String url ="http://app.jyall.com/jypay-cashier/v1/queryBusinType";
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG,response.toString());
                Log.d(TAG,Thread.currentThread().getName());

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.getMessage());
            }
        });
        mRequestQueue.add(jr);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(uploadImgReceiver);
    }
    private PopupWindow popupWindow;

    private View view;


    private void showWindow(View parent) {

        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.group_list_pop, null);
            popupWindow = new PopupWindow(view, 200, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的宽度的一半
        int xPos = windowManager.getDefaultDisplay().getWidth() / 2
                - popupWindow.getWidth() / 2;
        Log.i("coder", "xPos:" + xPos);

        popupWindow.showAsDropDown(parent, parent.getLayoutParams().width / 2, 0);

    }

}
