package com.frank.protean.bean;

import java.util.HashMap;

/**
 * Created by Frankmao on 2018/3/15.
 */

public class SourceContent {
    public boolean success = false;
    public String htmlCode = "";
    public String raw = "";
    public String title = "";
    public String description = "";
    public String url = "";
    public String finalUrl = "";
    public String cannonicalUrl = "";
    public HashMap<String, String> metaTags = new HashMap<String, String>();
}
