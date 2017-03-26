package com.intplus.shoppingspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        WebView helpWebView = (WebView) findViewById(R.id.webView_about);
        helpWebView.loadUrl("file:///android_asset/about.html");
    }
}
