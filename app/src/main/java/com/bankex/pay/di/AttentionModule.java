package com.bankex.pay.di;

import com.bankex.pay.ui.AttentionCreateWalletFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface AttentionModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = {AccountsManageModule.class})
    AttentionCreateWalletFragment attentionCreateWalletFragment();
}
