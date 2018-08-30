package com.bankex.pay.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class FirstActivity extends AppCompatActivity {
    private CountDownTimer mTimer;
    private boolean mTimerFinished;

    private static final int SPLASH_DURATION = 2000;

    @Inject
    SplashViewModelFactory splashViewModelFactory;
    SplashViewModel splashViewModel;

    @Inject
    PreferenceRepositoryType preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.ac_splash);
        Fabric.with(this, new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build(), new Crashlytics());

    }

    private void onWallets(Wallet[] wallets) {
        // Start home activity
        if (wallets.length == 0) {
            new ManageWalletsRouter().open(this, true);
        } else {
            new TransactionsRouter().open(this, true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cancelTimer();
        mTimer = getSplashTimer();
        mTimer.start();
    }

    @Override
    protected void onPause() {
        cancelTimer();
        super.onPause();
    }

    private void cancelTimer() {
        mTimerFinished = false;
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private CountDownTimer getSplashTimer() {
        return new CountDownTimer(SPLASH_DURATION, SPLASH_DURATION) {
            @Override
            public void onTick(long millisUntilFinished) {
                // nothing
            }

            @Override
            public void onFinish() {
                mTimerFinished = true;
                if (preferences.getOnBoardingFlag()) {
                    splashViewModel = ViewModelProviders.of(FirstActivity.this, splashViewModelFactory)
                            .get(SplashViewModel.class);
                    splashViewModel.wallets().observe(FirstActivity.this, FirstActivity.this::onWallets);
                } else {
                    new OnBoardingRouter().open(FirstActivity.this);
                }
            }
        };
    }

    private void openNextActivity() {
        if (mTimerFinished) {
            new ManageWalletsRouter().open(this, true);
        }
    }
}
