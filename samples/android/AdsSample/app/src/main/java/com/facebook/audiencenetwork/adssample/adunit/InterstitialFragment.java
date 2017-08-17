package com.facebook.audiencenetwork.adssample.adunit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.audiencenetwork.adssample.R;


public class InterstitialFragment extends Fragment {
    private InterstitialAd interstitialAd;
    private Button loadInterstitialButton;
    private Button showInterstitialButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interstitial, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadInterstitialButton = (Button) view.findViewById(R.id
                .btn_load_interstitial);
        showInterstitialButton = (Button) view.findViewById(R.id
                .btn_show_interstitial);

        loadInterstitialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                loadInterstitialButton.setEnabled(false);
                loadInterstitial();
            }
        });

        showInterstitialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                showInterstitial();
            }
        });
    }

    public void loadInterstitial() {
        // Instantiate an InterstitialAd object
        interstitialAd = new InterstitialAd(getContext(), "YOUR_PLACEMENT_ID");
        interstitialAd.loadAd();

        // Set listeners for the Interstitial Ad
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                Toast.makeText(getContext(), "Interstitial Ad displayed!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                Toast.makeText(getContext(), "Interstitial Ad dismissed!",
                        Toast.LENGTH_LONG).show();
                loadInterstitialButton.setEnabled(true);
                showInterstitialButton.setEnabled(false);
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Toast.makeText(getContext(), "Error: " + adError.getErrorMessage(),
                        Toast.LENGTH_LONG).show();
                loadInterstitialButton.setEnabled(true);
                showInterstitialButton.setEnabled(false);
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Toast.makeText(getContext(), "Ad Loaded!",
                        Toast.LENGTH_LONG).show();
                showInterstitialButton.setEnabled(true);
            }

            @Override
            public void onAdClicked(Ad ad) {
                Toast.makeText(getContext(), "Interstitial Ad clicked!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Toast.makeText(getContext(), "Impression logged!", Toast
                        .LENGTH_LONG).show();
            }
        });


    }

    public void showInterstitial() {
        interstitialAd.show();
    }
}
