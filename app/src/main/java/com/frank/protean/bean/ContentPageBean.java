package com.frank.protean.bean;

import java.io.Serializable;

/**
 * Created by Frankmao on 2018/1/18.
 */

public class ContentPageBean implements Serializable {
    public String id;
    public String url;
    public int muilView;//0 item //1 lock 2 loading
    public String title;
}
