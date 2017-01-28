package com.intplus.shoppingspace;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.intplus.shoppingspace.controller.AppController;
import com.intplus.shoppingspace.model.Shop;

import static com.intplus.shoppingspace.app.AppConstants.APPLOG;

/*
Actionbar animation
http://www.devexchanges.info/2015/09/android-showhide-actionbar-when.html

 */
public class ActivityWebView extends AppCompatActivity implements ObservableScrollViewCallbacks {

    ObservableWebView myWebView;
    int sid=0;
    AppController appController;
    Shop thisShop;
    String url = "http://www.amazon.in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appController = new AppController(this);
        Intent mIntent = getIntent();
        sid = mIntent.getIntExtra("sid", 0);
        thisShop = appController.getShopById(sid);
        url = thisShop.url;

        myWebView = (ObservableWebView) this.findViewById(R.id.webview);
        myWebView.setScrollViewCallbacks(this);
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.setWebChromeClient(new WebChromeClient());
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);

        Log.d(APPLOG, "url : "+url);
        myWebView.loadUrl(url);
        myWebView.postDelayed(new Runnable() {

            @Override
            public void run() {
                myWebView.loadUrl(url);
            }
        }, 1500);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myWebView.destroy();
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        // Log.d(APPLOG, "onScrollChanged");
    }

    @Override
    public void onDownMotionEvent() {
        Log.d(APPLOG, "onDownmotionEvent");
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        Log.d(APPLOG, "onUpOrCancel");
        ActionBar ab = getSupportActionBar();
        if (ab == null) {
            return;
        }
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }
}
