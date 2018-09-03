package com.bankex.pay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.bankex.pay.entity.Wallet;
import com.bankex.pay.interact.FetchOnboardingSettingsInteract;
import com.bankex.pay.interact.FetchWalletsInteract;

public class SplashViewModel extends ViewModel {
    private final FetchWalletsInteract fetchWalletsInteract;
    private final FetchOnboardingSettingsInteract fetchOnboardingSettingsInteract;
    private MutableLiveData<Wallet[]> wallets = new MutableLiveData<>();

    SplashViewModel(FetchWalletsInteract fetchWalletsInteract, FetchOnboardingSettingsInteract fetchOnboardingSettingsInteract) {
        this.fetchWalletsInteract = fetchWalletsInteract;
        this.fetchOnboardingSettingsInteract = fetchOnboardingSettingsInteract;

        fetchWalletsInteract
                .fetch()
                .subscribe(wallets::postValue, this::onError);
    }

    public void onSetOnboardingTrue() {
        fetchOnboardingSettingsInteract.post();
    }

    public boolean getOnboardingFlag() {
        return fetchOnboardingSettingsInteract.fetch();
    }

    private void onError(Throwable throwable) {
        wallets.postValue(new Wallet[0]);
    }

    public LiveData<Wallet[]> wallets() {
        return wallets;
    }
}
