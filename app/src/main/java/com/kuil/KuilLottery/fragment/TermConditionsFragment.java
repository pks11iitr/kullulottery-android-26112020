package com.kuil.KuilLottery.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.retrofitwebservices.RequestUrl;

public class TermConditionsFragment extends Fragment {

    View view;
    WebView webTerm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_term_conditions, container, false);
        webTerm = view.findViewById(R.id.webTerm);
        webTerm.getSettings().setJavaScriptEnabled(true);
        webTerm.getSettings().setBuiltInZoomControls(true);
        webTerm.getSettings().setJavaScriptEnabled(true);
        webTerm.loadUrl(RequestUrl.BASE_URL+"terms-condition");
        return view;
    }
}