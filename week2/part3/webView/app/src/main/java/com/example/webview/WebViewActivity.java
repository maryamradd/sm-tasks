package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;


public class WebViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);


        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://maryamradd.github.io/sm-tasks/week1/");

    }
}
