package com.bankex.pay.ui;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bankex.pay.BuildConfig;
import com.bankex.pay.R;
import com.bankex.pay.entity.Wallet;
import com.bankex.pay.repository.PreferenceRepositoryType;
import com.bankex.pay.router.ManageWalletsRouter;
import com.bankex.pay.router.OnBoardingRouter;
import com.bankex.pay.router.TransactionsRouter;
import com.bankex.pay.viewmodel.SplashViewModel;
import com.bankex.pay.viewmodel.SplashViewModelFactory;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    @Inject
    SplashViewModelFactory splashViewModelFactory;
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.ac_splash);
        Fabric.with(this, new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build());
        if (splashViewModel.getOnboardingFlag()) {
            splashViewModel = ViewModelProviders.of(this, splashViewModelFactory)
                    .get(SplashViewModel.class);
            splashViewModel.wallets().observe(this, this::onWallets);
        } else {
            new OnBoardingRouter().open(this);
        }
    }

    private void onWallets(Wallet[] wallets) {
        // Start home activity
        if (wallets.length == 0) {
            new ManageWalletsRouter().open(this, true);
        } else {
            new TransactionsRouter().open(this, true);
        }
    }
}
