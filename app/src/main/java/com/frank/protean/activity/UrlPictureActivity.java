package com.frank.protean.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.frank.protean.R;
import com.frank.protean.bean.SourceContent;
import com.frank.protean.utils.Regex;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Frankmao on 2018/2/7.
 */

public class UrlPictureActivity extends AppCompatActivity {
    private ImageView img_icon;
    private TextView tv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_pic);
        img_icon = (ImageView) findViewById(R.id.img_icon);
        tv_title = (TextView) findViewById(R.id.tv_title);
        String url = "http://www.baidu.com/";
        final SourceContent sourceContent = new SourceContent();
        sourceContent.finalUrl = url;
        Log.e("UrlPictureActivity", "result" + extendedTrim(url));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = getDocument(sourceContent);
                    sourceContent.htmlCode = extendedTrim(doc.toString());
                    HashMap<String, String> metaTags = getMetaTags(sourceContent
                            .htmlCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    protected Document getDocument(SourceContent sourceContent) throws IOException {
        return Jsoup.connect(sourceContent.finalUrl).userAgent("Mozilla").get();
    }

    public static String extendedTrim(String content) {
        return content.replaceAll("\\s+", " ").replace("\n", " ")
                .replace("\r", " ").trim();
    }

    /**
     * Returns meta tags from html code
     */
    private HashMap<String, String> getMetaTags(String content) {

        HashMap<String, String> metaTags = new HashMap<String, String>();
        metaTags.put("url", "");
        metaTags.put("title", "");
        metaTags.put("description", "");
        metaTags.put("image", "");

        List<String> matches = Regex.pregMatchAll(content,
                Regex.METATAG_PATTERN, 1);

        for (String match : matches) {
            final String lowerCase = match.toLowerCase();
            if (lowerCase.contains("property=\"og:url\"")
                    || lowerCase.contains("property='og:url'")
                    || lowerCase.contains("name=\"url\"")
                    || lowerCase.contains("name='url'"))
                updateMetaTag(metaTags, "url", separeMetaTagsContent(match));
            else if (lowerCase.contains("property=\"og:title\"")
                    || lowerCase.contains("property='og:title'")
                    || lowerCase.contains("name=\"title\"")
                    || lowerCase.contains("name='title'"))
                updateMetaTag(metaTags, "title", separeMetaTagsContent(match));
            else if (lowerCase
                    .contains("property=\"og:description\"")
                    || lowerCase
                    .contains("property='og:description'")
                    || lowerCase.contains("name=\"description\"")
                    || lowerCase.contains("name='description'"))
                updateMetaTag(metaTags, "description", separeMetaTagsContent(match));
            else if (lowerCase.contains("property=\"og:image\"")
                    || lowerCase.contains("property='og:image'")
                    || lowerCase.contains("name=\"image\"")
                    || lowerCase.contains("name='image'"))
                updateMetaTag(metaTags, "image", separeMetaTagsContent(match));
        }

        return metaTags;
    }

    public void updateMetaTag(HashMap<String, String> metaTags, String url, String value) {
        if (value != null && (value.length() > 0)) {
            metaTags.put(url, value);
        }
    }

    /**
     * Gets content from metatag
     */
    public String separeMetaTagsContent(String content) {
        String result = Regex.pregMatch(content, Regex.METATAG_CONTENT_PATTERN,
                1);
        return htmlDecode(result);
    }

    /**
     * Transforms from html to normal string
     */
    public String htmlDecode(String content) {
        return Jsoup.parse(content).text();
    }
}
