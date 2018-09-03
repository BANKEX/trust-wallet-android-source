package com.bankex.pay.interact;


import com.bankex.pay.entity.GasSettings;
import com.bankex.pay.repository.PreferenceRepositoryType;

public class FetchGasSettingsInteract {
    private final PreferenceRepositoryType repository;

    public FetchGasSettingsInteract(PreferenceRepositoryType repository) {
        this.repository = repository;
    }

    public GasSettings fetch(boolean forTokenTransfer) {
        return repository.getGasSettings(forTokenTransfer);
    }

}
