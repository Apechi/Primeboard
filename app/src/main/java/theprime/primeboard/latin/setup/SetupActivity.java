/*
 * Copyright (C) 2013 The Android Open Source Project
 * modified
 * SPDX-License-Identifier: Apache-2.0 AND GPL-3.0-only
 */

package theprime.primeboard.latin.setup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.adsmedia.adsmodul.AdsHelper;
import com.adsmedia.adsmodul.OpenAds;
import com.adsmedia.adsmodul.utils.AdsConfig;

import theprime.primeboard.latin.BuildConfig;
import theprime.primeboard.latin.config.AdsData;

public final class SetupActivity extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AdsData.getIDAds();
        AdsHelper.initializeAds(this, BuildConfig.APPLICATION_ID, AdsConfig.Game_App_ID);
        if (BuildConfig.DEBUG) {
            AdsHelper.debugMode(true);
        }
        OpenAds.LoadOpenAds(AdsConfig.Open_App_ID);
        OpenAds.AppOpenAdManager.showAdIfAvailable(this, new OpenAds.OnShowAdCompleteListener() {
            @Override
            public void onShowAdComplete() {
                final Intent intent = new Intent();
                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onFinish() {
                        intent.setClass(getBaseContext(), SetupWizardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        if (!isFinishing()) {
                            finish();
                        }
                    }
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                }.start();
            }
        });

    }
}
