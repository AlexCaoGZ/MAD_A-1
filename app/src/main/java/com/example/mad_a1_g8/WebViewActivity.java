package com.example.mad_a1_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.wv);
        webView.getSettings().setJavaScriptEnabled(true);

        ProgressBar pb = findViewById(R.id.myProgressBar);

        webView.loadUrl("https://www.trivago.ca/?aDateRange%5Barr%5D=2022-03-27&aDateRange%5Bdep%5D=2022-03-28&aPriceRange%5Bfrom%5D=0&aPriceRange%5Bto%5D=0&iRoomType=7&aRooms%5B0%5D%5Badults%5D=2&cpt2=24999%2F200&hasList=1&hasMap=1&bIsSeoPage=0&sortingId=1&slideoutsPageItemId=&iGeoDistanceLimit=20000&address=&addressGeoCode=&offset=0&ra=&overlayMode=");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });

        webView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView wv,int progress){
                if(progress>=100){
                    pb.setVisibility(View.GONE);
                }else{
                    pb.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}