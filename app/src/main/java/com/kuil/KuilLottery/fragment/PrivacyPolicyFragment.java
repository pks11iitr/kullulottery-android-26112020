package com.kuil.KuilLottery.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.retrofitwebservices.RequestUrl;


public class PrivacyPolicyFragment extends Fragment {


    View view;
    WebView webPriacy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_privacy_policy, container, false);
        webPriacy = view.findViewById(R.id.webPriacy);
        webPriacy.getSettings().setJavaScriptEnabled(true);
        webPriacy.getSettings().setBuiltInZoomControls(true);
        webPriacy.getSettings().setJavaScriptEnabled(true);
        webPriacy.loadUrl(RequestUrl.BASE_URL+"privacy-policy");
        return view;
    }
}