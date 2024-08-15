/*
 * Copyright (C) 2012 The Android Open Source Project
 * modified
 * SPDX-License-Identifier: Apache-2.0 AND GPL-3.0-only
 */

package theprime.primeboard.latin.settings;

import static android.preference.PreferenceActivity.EXTRA_NO_HEADERS;
import static android.preference.PreferenceActivity.EXTRA_SHOW_FRAGMENT;

import android.content.Intent;
import android.os.Bundle;

import theprime.primeboard.latin.BuildConfig;
import theprime.primeboard.latin.permissions.PermissionsManager;
import theprime.primeboard.latin.utils.ActivityThemeUtils;
import theprime.primeboard.latin.utils.NewDictionaryAdder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.adsmedia.adsmodul.AdsHelper;
import com.adsmedia.adsmodul.utils.AdsConfig;

public final class  SettingsActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final String DEFAULT_FRAGMENT = SettingsFragment.class.getName();

    public static final String EXTRA_ENTRY_KEY = "entry";
    public static final String EXTRA_ENTRY_VALUE_APP_ICON = "app_icon";

    @Override
    protected void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        final Intent i = getIntent();
        if (Intent.ACTION_VIEW.equals(i.getAction()) && i.getData() != null) {
            new NewDictionaryAdder(this, null).addDictionary(i.getData(), null);
            setIntent(new Intent()); // avoid opening again
        }
        if (getSupportFragmentManager().getFragments().isEmpty())
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                    .commit();

        ActivityThemeUtils.setActivityTheme(this);

        AdsHelper.initializeAdsPrime(this, BuildConfig.APPLICATION_ID, AdsConfig.Game_App_ID);
        AdsHelper.loadInterstitialPrime(
                this,
                AdsConfig.Interstitial_ID
        );


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public Intent getIntent() {
        final Intent intent = super.getIntent();
        final String fragment = intent.getStringExtra(EXTRA_SHOW_FRAGMENT);
        if (fragment == null) {
            intent.putExtra(EXTRA_SHOW_FRAGMENT, DEFAULT_FRAGMENT);
        }
        intent.putExtra(EXTRA_NO_HEADERS, true);
        return intent;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.get(this).onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
