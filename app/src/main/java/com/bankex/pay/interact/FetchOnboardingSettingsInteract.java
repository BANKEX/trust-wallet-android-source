package com.bankex.pay.interact;

import com.bankex.pay.repository.PreferenceRepositoryType;

public class FetchOnboardingSettingsInteract {
    private final PreferenceRepositoryType repository;

    public FetchOnboardingSettingsInteract(PreferenceRepositoryType repository) {
        this.repository = repository;
    }

    public boolean fetch() {
        return repository.getOnBoardingFlag();
    }

    public void post() {
        repository.setCurrentOnBoardingFlag(new Boolean(true));
    }

}
