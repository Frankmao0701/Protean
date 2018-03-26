package com.frank.protean.bean;

import java.io.Serializable;

/**
 * Created by Frankmao on 2018/1/18.
 */

public class ContentPageBean implements Serializable {
    public String url;
    public boolean showLock;//0 item //1 lock 2 loading
    public String title;
    public int position;
    public int total;


}
