package com.kuil.KuilLottery.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.retrofitwebservices.RequestUrl;


public class AboutUsFragment extends Fragment {


    View view;
    WebView webAbout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_us, container, false);
        webAbout = view.findViewById(R.id.webAbout);
        webAbout.getSettings().setJavaScriptEnabled(true);
        webAbout.getSettings().setBuiltInZoomControls(true);
        webAbout.getSettings().setJavaScriptEnabled(true);
        webAbout.loadUrl(RequestUrl.BASE_URL+"about-us");
        return view;
    }
}