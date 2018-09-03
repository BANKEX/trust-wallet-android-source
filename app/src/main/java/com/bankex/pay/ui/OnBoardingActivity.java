package com.bankex.pay.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bankex.pay.R;
import com.bankex.pay.entity.Onboarding;
import com.bankex.pay.entity.Wallet;
import com.bankex.pay.repository.PreferenceRepositoryType;
import com.bankex.pay.router.ManageWalletsRouter;
import com.bankex.pay.router.TransactionsRouter;
import com.bankex.pay.viewmodel.SplashViewModel;
import com.bankex.pay.viewmodel.SplashViewModelFactory;
import com.rd.PageIndicatorView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PageIndicatorView pageIndicatorView;
    private TextView pageButton;

    Onboarding favourites = new Onboarding(R.drawable.x35, R.string.favorite_list, R.string.add_your_contacts);
    Onboarding standart = new Onboarding(R.drawable.x36, R.string.ERC20_standart, R.string.support_any_tokens);
    Onboarding network = new Onboarding(R.drawable.x37, R.string.custom_network, R.string.add_your_network);

    @Inject
    SplashViewModelFactory splashViewModelFactory;
    SplashViewModel splashViewModel;

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            pageIndicatorView.setSelection(position);
            if (position == 2) changeLabel();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_on_boarding);
        viewPager = findViewById(R.id.viewPager);
        pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageButton = findViewById(R.id.pageButton);
        Onboarding[] onboardings = new Onboarding[]{favourites, standart, network};
        viewPager.setAdapter(new OnBoardingPagerAdapter(onboardings, getSupportFragmentManager()));
    }

    class OnBoardingPagerAdapter extends FragmentPagerAdapter {

        private final Onboarding[] list;

        public OnBoardingPagerAdapter(Onboarding[] list, FragmentManager fm) {
            super(fm);
            this.list = list;
        }

        public Fragment getItem(int position) {
            Onboarding get = list[position];
            Fragment fragment = ContentOnBoardingFragment.newInstance(get.getId(), get.getTitle(), get.getDesc());
            return fragment;
        }

        public int getCount() {
            return 3;
        }
    }

    private void changeLabel() {
        pageButton.setText(R.string.btn_start);
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.removeOnPageChangeListener(onPageChangeListener);
        pageButton.setOnClickListener(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.addOnPageChangeListener(onPageChangeListener);
        pageButton.setOnClickListener(v -> {
                    int childCount = viewPager.getChildCount();
                    int currentItem = viewPager.getCurrentItem();
                    onBoardingNext(currentItem, childCount);
                }
        );
    }

    private void onBoardingNext(int currentItem, int childCount) {
        if (currentItem == childCount) {
            finishOnboarding();
        } else viewPager.setCurrentItem(currentItem + 1, true);
    }

    private void finishOnboarding() {
        splashViewModel = ViewModelProviders.of(this, splashViewModelFactory)
                .get(SplashViewModel.class);
        splashViewModel.onSetOnboardingTrue();
        splashViewModel.wallets().observe(this, this::onWallets);
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


