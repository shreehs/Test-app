package com.intplus.shoppingspace;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import static com.intplus.shoppingspace.app.AppConstants.APPLOG;

public class HelpActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{

    ObservableWebView myWebView;
    LinearLayout toolBarLayout;
    int toolbarHeight;
    int oldScrollY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        toolBarLayout = (LinearLayout) findViewById(R.id.mytoolbar);
        myWebView = (ObservableWebView) this.findViewById(R.id.webview);
        myWebView.setScrollViewCallbacks(this);
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.setWebChromeClient(new WebChromeClient());
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);

        Log.d(APPLOG, "url : "+"http://amazon.in/");
        myWebView.loadUrl("http://amazon.in/");
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        toolBarLayout.animate().cancel();

        int scrollDelta = scrollY - oldScrollY;
        oldScrollY = scrollY;

        float currentYTranslation = -toolBarLayout.getTranslationY();
        float targetYTranslation = Math.min(Math.max(currentYTranslation + scrollDelta, 0), toolbarHeight);
        toolBarLayout.setTranslationY(-targetYTranslation);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        float currentYTranslation = -toolBarLayout.getTranslationY();
        int currentScroll = myWebView.getCurrentScrollY();

        if (currentScroll < toolbarHeight) {
            toolBarLayout.animate().translationY(0);
        } else if (currentYTranslation > toolbarHeight /2) {
            toolBarLayout.animate().translationY(-toolbarHeight);
        } else {
            toolBarLayout.animate().translationY(0);
        }
    }
}
