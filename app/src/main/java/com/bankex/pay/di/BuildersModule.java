package com.bankex.pay.di;

import com.bankex.pay.ui.AddTokenActivity;
import com.bankex.pay.ui.ConfirmationActivity;
import com.bankex.pay.ui.CreateWalletActivity;
import com.bankex.pay.ui.FirstActivity;
import com.bankex.pay.ui.GasSettingsActivity;
import com.bankex.pay.ui.ImportWalletActivity;
import com.bankex.pay.ui.MyAddressActivity;
import com.bankex.pay.ui.OnBoardingActivity;
import com.bankex.pay.ui.SendActivity;
import com.bankex.pay.ui.SettingsActivity;
import com.bankex.pay.ui.TokensActivity;
import com.bankex.pay.ui.TransactionDetailActivity;
import com.bankex.pay.ui.TransactionsActivity;
import com.bankex.pay.ui.WalletCreatedActivity;
import com.bankex.pay.ui.WalletsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract FirstActivity bindSplashModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract OnBoardingActivity bindOnboardingModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = AccountsManageModule.class)
    abstract WalletsActivity bindManageWalletsModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = AccountsManageModule.class)
    abstract WalletCreatedActivity bindManageWalletCreatedModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AttentionModule.class, ConfirmationFragmentModule.class})
    abstract CreateWalletActivity bindCreateWalletsModule();
/*
	@FragmentScope
	@ContributesAndroidInjector(modules = AccountsManageModule.class)
	abstract AttentionCreateWalletFragment bindAttentionWalletsModule();

	@FragmentScope
	@ContributesAndroidInjector(modules = AccountsManageModule.class)
	abstract ConfirmationCreateWalletFragment bindConfirmationWalletsModule();*/

    @ActivityScope
    @ContributesAndroidInjector(modules = ImportModule.class)
    abstract ImportWalletActivity bindImportWalletModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = TransactionsModule.class)
    abstract TransactionsActivity bindTransactionsModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = TransactionDetailModule.class)
    abstract TransactionDetailActivity bindTransactionDetailModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = SettingsModule.class)
    abstract SettingsActivity bindSettingsModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = SendModule.class)
    abstract SendActivity bindSendModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = ConfirmationModule.class)
    abstract ConfirmationActivity bindConfirmationModule();

    @ContributesAndroidInjector
    abstract MyAddressActivity bindMyAddressModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = TokensModule.class)
    abstract TokensActivity bindTokensModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = GasSettingsModule.class)
    abstract GasSettingsActivity bindGasSettingsModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = AddTokenModule.class)
    abstract AddTokenActivity bindAddTokenActivity();
}
