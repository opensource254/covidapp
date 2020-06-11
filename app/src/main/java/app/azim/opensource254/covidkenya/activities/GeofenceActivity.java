package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import app.azim.opensource254.covidkenya.R;

import android.app.MediaRouteButton;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Objects;

public class GeofenceActivity extends AppCompatActivity {
    WebView mwebView;
   public ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofence);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //setting dark text and white ontouch bottom ui
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        } else {
            //for lollipop and below use default dark theme
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }


        mwebView = findViewById(R.id.wbgeofence);
        mProgressbar = findViewById(R.id.progress_bar_geo);

        WebSettings webSettings = mwebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //improve webView performance
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSaveFormData(true);
        mwebView.getSettings().setGeolocationEnabled(true);
        mwebView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        mwebView.getSettings().setAllowFileAccess(true);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.setFocusableInTouchMode(true);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // load online by default
        mwebView.setWebViewClient(new GeofenceActivity.MyBrowser()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressbar.setVisibility(View.GONE);

            }
        });



        if (!isNetworkAvailable()) { // loading offline
            Toast ToastMessage = Toast.makeText(getApplicationContext(), "connect to the internet accessing offline", Toast.LENGTH_SHORT);
            ToastMessage.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            ToastMessage.show();
            mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        mwebView.loadUrl("https://geofence-demo-325.web.app/admin");


    }

//checking if network available
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            //Clearing the WebView
            try {
                webView.stopLoading();
            } catch (Exception ignored) {
            }
            try {
                webView.clearView();
            } catch (Exception ignored) {
            }
            if (webView.canGoBack()) {
                webView.goBack();
            }
          //  webView.loadUrl("file:///android_asset/error.html");

            super.onReceivedError(webView, errorCode, description, failingUrl);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (Objects.equals(Uri.parse(url).getHost(), "https://geofence-demo-325.web.app/admin")) {
                //open url contents in webview
                return false;
            } else {
                //here open external links in external browser or app
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }


        }

    }
}