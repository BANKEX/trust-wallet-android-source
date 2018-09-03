package com.bankex.pay.di;

import com.bankex.pay.interact.FetchOnboardingSettingsInteract;
import com.bankex.pay.interact.FetchWalletsInteract;
import com.bankex.pay.repository.PreferenceRepositoryType;
import com.bankex.pay.repository.WalletRepositoryType;
import com.bankex.pay.viewmodel.SplashViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class OnBoardingModule {

    @Provides
    SplashViewModelFactory provideSplashViewModelFactory(FetchWalletsInteract fetchWalletsInteract, FetchOnboardingSettingsInteract fetchOnboardingSettingsInteract) {
        return new SplashViewModelFactory(fetchWalletsInteract, fetchOnboardingSettingsInteract);
    }

    @Provides
    FetchWalletsInteract provideFetchOnboardingSettingsInteract(WalletRepositoryType walletRepository) {
        return new FetchWalletsInteract(walletRepository);
    }
}
