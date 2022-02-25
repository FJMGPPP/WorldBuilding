package com.fjmg.worldbuilding.ui.Diagramas;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import com.fjmg.worldbuilding.databinding.FragmentDiagramBinding;


public class FragmentDiagram  extends Fragment
{
    FragmentDiagramBinding binding;
    WebView navegador;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentDiagramBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        navegador = binding.navegador;
        navegador.getSettings().setAllowFileAccess(true);
        navegador.getSettings().setJavaScriptEnabled(true);
        navegador.setWebChromeClient(new WebChromeClient());
        navegador.loadUrl("file:///android_asset/nucleo.html");

    }
}
