package com.frank.protean.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.frank.protean.R;
import com.frank.protean.fragment.FinishedFragment;
import com.frank.protean.fragment.RunningFragment;

/**
 * Created by maowenqiang on 16/8/6.
 */
public class DownloadActivity extends Activity implements View.OnClickListener {
    private TextView tv_download_running;
    private TextView tv_download_finished;
    private Fragment mRunning;
    private Fragment mFinished;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_download);
        initView();
    }

    private void initView() {
        tv_download_running = (TextView) findViewById(R.id.tv_download_running);
        tv_download_finished = (TextView) findViewById(R.id.tv_download_finished);
        tv_download_running.setOnClickListener(this);
        tv_download_finished.setOnClickListener(this);
        setDefaultFragment();
    }

    private void setDefaultFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mRunning = new RunningFragment();
        transaction.replace(R.id.fl_container, mRunning);
        transaction.commit();
    }

    @Override
    public void onClick(View v)
    {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务  
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId())
        {
            case R.id.tv_download_running:
                if (mRunning == null)
                {
                    mRunning = new RunningFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件  
                transaction.replace(R.id.fl_container, mRunning);
                break;
            case R.id.tv_download_finished:
                if (mFinished == null)
                {
                    mFinished = new FinishedFragment();
                }
                transaction.replace(R.id.fl_container, mFinished);
                break;
        }
        // transaction.addToBackStack();  
        // 事务提交  
        transaction.commit();
    }
}
