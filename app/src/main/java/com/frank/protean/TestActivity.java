package com.frank.protean;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * Created by maowenqiang on 16/9/27.
 */
public class TestActivity extends Activity implements AutoTextview.ChangeLayoutListener {
    private TextView test1;
    private AutoTextview test2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        test1 = (TextView) findViewById(R.id.test1);
        test2 = (AutoTextview) findViewById(R.id.test2);
        test2.setChangeLayoutListener(this,test2);
        test1.setText("我是毛文强毛文强毛文强毛文强毛文强毛文强毛文强毛文强毛文强毛文强毛文强毛文强");
        test2.setText("我是毛文强毛文强");
        test2.setText("stash stash");
    }

    @Override
    public void isChange(View view, String text) {
        if(text!=null&&text.length()>0) {
            test2.setText(text);   //重新更新textview
        }
    }

    @Override
    public boolean isOk(View view) {
        return true;
    }
}
