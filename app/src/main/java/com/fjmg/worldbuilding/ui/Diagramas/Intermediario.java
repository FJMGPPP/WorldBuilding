package com.fjmg.worldbuilding.ui.Diagramas;

import android.annotation.SuppressLint;
import android.webkit.WebView;

public class Intermediario
{
    WebView web;
    @SuppressLint("JavascriptInterface")
    public  Intermediario(WebView web)
    {
        this.web = web;
    }
}
