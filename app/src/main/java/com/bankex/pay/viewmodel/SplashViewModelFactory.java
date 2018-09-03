package com.bankex.pay.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.bankex.pay.interact.FetchOnboardingSettingsInteract;
import com.bankex.pay.interact.FetchWalletsInteract;

public class SplashViewModelFactory implements ViewModelProvider.Factory {

    private final FetchWalletsInteract fetchWalletsInteract;
    private final FetchOnboardingSettingsInteract fetchOnboardingSettingsInteract;

    public SplashViewModelFactory(FetchWalletsInteract fetchWalletsInteract, FetchOnboardingSettingsInteract fetchOnboardingSettingsInteract) {
        this.fetchWalletsInteract = fetchWalletsInteract;
        this.fetchOnboardingSettingsInteract = fetchOnboardingSettingsInteract;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SplashViewModel(fetchWalletsInteract, fetchOnboardingSettingsInteract);
    }
}
