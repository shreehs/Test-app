package com.intplus.shoppingspace;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.intplus.shoppingspace.controller.AppController;
import com.intplus.shoppingspace.model.Shop;

import static com.intplus.shoppingspace.app.AppConstants.APPLOG;

public class ActivityWebView extends AppCompatActivity {

    WebView myWebView;
    int sid=1;
    AppController appController;
    Shop thisShop;
    String url = "http://www.amazon.in";
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    AppBarLayout.LayoutParams params;

    AlertDialog alertDialog;
    private static final String SPK_IGNORE_SSL = "ignore_ssl";
    SharedPreferences webPref;
    SharedPreferences.Editor webPrefEditor;

    // View objects.
    View checkBoxView;
    CheckBox cbSslOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.toolbar_layout);
        //toolbarHeight = getActionBarHeight();

        params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        // params.setScrollFlags(0);  // clear all scroll flags

        webPref = getApplicationContext().getSharedPreferences("WebPref", 0);
        webPrefEditor = webPref.edit();

        checkBoxView = View.inflate(this, R.layout.util_checkbox, null);
        cbSslOption = (CheckBox) checkBoxView.findViewById(R.id.cb_remember);
        cbSslOption.setChecked(true);

        appController = new AppController(this);
        Intent mIntent = getIntent();
        sid = mIntent.getIntExtra("sid", 0);
        thisShop = appController.getShopById(sid);
        url = thisShop.url;

        myWebView = (WebView) this.findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //super.onReceivedSslError(view, handler, error);
                if (webPref.getBoolean(SPK_IGNORE_SSL, false)){
                    handler.proceed();
                }
                else {
                    AlertDialog certAlertDialog = getCertAlertDialog(handler);
                    if (!certAlertDialog.isShowing()) {
                        certAlertDialog.show();
                        System.out.println("Show new alert dialog.");
                    }
                }
            }
        });
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);

        Log.d(APPLOG, "url : "+url);
        myWebView.loadUrl(url);
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

    public AlertDialog getCertAlertDialog(final SslErrorHandler handler) {

        if (alertDialog != null) {
            return alertDialog;
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("Your Title");

        // set dialog message
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                //.setCancelable(false)
                .setView(checkBoxView)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        System.out.println("Positive click");
                        webPrefEditor.putBoolean(SPK_IGNORE_SSL, cbSslOption.isChecked());
                        webPrefEditor.commit();
                        handler.proceed();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        System.out.println("Negative click");
                        dialog.cancel();
                    }
                });

        // create alert dialog
        alertDialog = alertDialogBuilder.create();
        return alertDialog;
    }
}

