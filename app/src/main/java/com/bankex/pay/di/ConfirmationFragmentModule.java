package com.bankex.pay.di;

import com.bankex.pay.ui.AttentionCreateWalletFragment;
import com.bankex.pay.ui.ConfirmationCreateWalletFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ConfirmationFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = {AccountsManageModule.class})
    ConfirmationCreateWalletFragment confirmationCreateWalletFragment();
}
