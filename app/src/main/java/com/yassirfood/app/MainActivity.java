package com.yassirfood.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;

public class MainActivity extends Activity {

    private WebView webView;

    private final String MENU_URL =
        "https://yassir-food-menu.malleable-spoonbill-f37.workers.dev/menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(
                WebView view, WebResourceRequest request) {

                String url = request.getUrl().toString();

                if (url.startsWith("https://") ||
                    url.startsWith("http://")) {
                    if (url.contains("wa.me") ||
                        url.contains("whatsapp.com")) {
                        startActivity(new Intent(
                            Intent.ACTION_VIEW, Uri.parse(url)));
                        return true;
                    }
                    return false;
                }

                try {
                    startActivity(new Intent(
                        Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (Exception e) {
                }

                return true;
            }
        });

        webView.loadUrl(MENU_URL);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
