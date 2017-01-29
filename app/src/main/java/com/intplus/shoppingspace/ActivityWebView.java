package com.intplus.shoppingspace;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
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

public class ActivityWebView extends AppCompatActivity implements ObservableScrollViewCallbacks {

    ObservableWebView myWebView;
    int sid=0;
    AppController appController;
    Shop thisShop;
    String url = "http://www.amazon.in";
    Toolbar toolbar;
    NestedScrollView nestedScrollWeb;
    AppBarLayout appBarLayout;
    AppBarLayout.LayoutParams params;
    int toolbarHeight;
    int oldScrollY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.toolbar_layout);
        toolbarHeight = getActionBarHeight();

        params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        // params.setScrollFlags(0);  // clear all scroll flags

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

    private int getActionBarHeight() {
        int actionBarHeight = getSupportActionBar().getHeight();
        if (actionBarHeight != 0)
            return actionBarHeight;
        final TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        Log.d(APPLOG, "onScrollChanged");

        // appBarLayout.animate().cancel();
        // params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);

        int scrollDelta = scrollY - oldScrollY;
        oldScrollY = scrollY;
        System.out.println("Shop scroll Delta : "+scrollDelta);

        float currentYTranslation = -appBarLayout.getTranslationY();
        float targetYTranslation = Math.min(Math.max(currentYTranslation + scrollDelta, 0), toolbarHeight);
        appBarLayout.setTranslationY(-targetYTranslation);

    }

    @Override
    public void onDownMotionEvent() {
        Log.d(APPLOG, "onDownmotionEvent");
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        Log.d(APPLOG, "onUpOrCancel");
        /*
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
        */
        ActionBar ab = getSupportActionBar();
        float currentYTranslation = -appBarLayout.getTranslationY();
        //int currentScroll = myWebView.getCurrentScrollY();
        int currentScroll = appBarLayout.getTotalScrollRange();

        System.out.println("Shop AppBarL Height : "+appBarLayout.getTotalScrollRange());
        System.out.println("Shop current Scroll : "+currentScroll);

        if (currentScroll < toolbarHeight) {
            // appBarLayout.animate().translationY(0);
            Log.d(APPLOG,"Show1");
            appBarLayout.setExpanded(true, true);
        } else if (currentYTranslation > toolbarHeight /2) {
            // appBarLayout.animate().translationY(-toolbarHeight);
            Log.d(APPLOG,"Collaps");
            appBarLayout.setExpanded(false, true);
        } else {
            // appBarLayout.animate().translationY(0);
            Log.d(APPLOG,"show2");
            appBarLayout.setExpanded(true, true);
        }

    }
}

