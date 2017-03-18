package com.intplus.shoppingspace.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by harshas on 3/11/2017.
 */

public class ShopWebViewClient extends WebViewClient {

    private String pendingUrl = null;

    public void setPendingUrl(String pendingUrl) {
        this.pendingUrl = pendingUrl;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String newUrl = request.getUrl().toString();
        System.out.println("New url : "+newUrl);
        view.loadUrl(newUrl);
        return true;
        //return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        System.out.println("intercept : "+request.getUrl().toString());
        //urlList.add(request.getUrl().toString());
        return super.shouldInterceptRequest(view, request);
    }

}
