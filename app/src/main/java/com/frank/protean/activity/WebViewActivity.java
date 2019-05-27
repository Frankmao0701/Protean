package com.frank.protean.activity;

import android.content.DialogInterface;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.frank.protean.R;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class WebViewActivity extends AppCompatActivity {
    private LinearLayout llContent;
    private WebView mWebView;
    String url = "https://api-cms.cw.sgmlink.com/ue/index.html?sid=5b5efe35c3b83&cmid=5c2c19c8f04a9&cf=content&id=5c22f52a00147d96099bf86d";
//    https://api-cms.cw.sgmlink.com/ue/index.html?sid=5b5efe35c3b83&cmid=5c2c19c8f04a9&cf=content&id=5c22f2ab00147d96099bf86b
//    String url = "https://www.baidu.com/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        llContent = findViewById(R.id.ll_content);
        findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl(url);
            }
        });
        initWebView();
//        mWebView.loadUrl(url);
    }

    private void initWebView() {
        mWebView = new WebView(this);
        llContent.addView(mWebView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        //init WebView
//        WebSettings settings = mWebView.getSettings();

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        // 解决图片不显示
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 支持显示放大缩小 controler
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        // 不显示webview缩放按钮
        settings.setDisplayZoomControls(false);
        // 可以缩放
        settings.setSupportZoom(true);
        // 为图片添加放大缩小
        settings.setUseWideViewPort(true);
        // 关闭webview中缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        // 支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");
        // 设置编码格式
        settings.setLoadWithOverviewMode(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view,
                                           final SslErrorHandler handler, SslError error) {
                Log.i("Electronic", "onReceivedSslError::" + error.toString() + "::" + error.getPrimaryError());

                if (error.getPrimaryError() == SslError.SSL_DATE_INVALID  // 日期不正确
                        || error.getPrimaryError() == SslError.SSL_EXPIRED // 日期不正确
                        || error.getPrimaryError() == SslError.SSL_INVALID // webview BUG
                        || error.getPrimaryError() == SslError.SSL_UNTRUSTED) { // 根证书丢失
                    if (chkMySSLCNCert(error.getCertificate())) {
                        handler.proceed();  // 如果证书一致，忽略错误
                    }
                }
            }


        });

    }

    private boolean chkMySSLCNCert(SslCertificate cert) {
        //C9 E0 B6 BC 09 7F 5F 18 00 E2 93 4B 39 E3 82 36 33 07 AC B6 4C 9C A7 EC 1A E1 55 46 89 35 38 FF
        String fingerprint = "C9E0B6BC097F5F1800E2934B39E382363307ACB64C9CA7EC1AE15546893538FF";
        byte[] MySSLCNSHA256 = hexStringToBytes(fingerprint);
        Log.i("Electronic", "chkMySSLCNCert::" + MySSLCNSHA256.toString());

        Bundle bundle = SslCertificate.saveState(cert);
        byte[] bytes = bundle.getByteArray("x509-certificate");
        if (bytes != null) {
            try {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                Certificate ca = cf.generateCertificate(new ByteArrayInputStream(bytes));
                MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
                byte[] key = sha256.digest(ca.getEncoded());
                return Arrays.equals(key, MySSLCNSHA256);
            } catch (Exception Ex) {
                Log.i("Electronic", "Exception::" + Ex.getMessage());
            }
        }
        return false;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            //请求一个空页面，修复退出页面时，webview仍在后台请求网页资源
            mWebView.loadUrl("about:blank");
        }
    }
}
