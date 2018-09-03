package com.bankex.pay.di;


import com.bankex.pay.interact.CreateTransactionInteract;
import com.bankex.pay.interact.FetchGasSettingsInteract;
import com.bankex.pay.interact.FindDefaultWalletInteract;
import com.bankex.pay.repository.PasswordStore;
import com.bankex.pay.repository.PreferenceRepositoryType;
import com.bankex.pay.repository.TransactionRepositoryType;
import com.bankex.pay.repository.WalletRepositoryType;
import com.bankex.pay.router.GasSettingsRouter;
import com.bankex.pay.viewmodel.ConfirmationViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfirmationModule {
    @Provides
    public ConfirmationViewModelFactory provideConfirmationViewModelFactory(
            FindDefaultWalletInteract findDefaultWalletInteract,
            FetchGasSettingsInteract fetchGasSettingsInteract,
            CreateTransactionInteract createTransactionInteract,
            GasSettingsRouter gasSettingsRouter
    ) {
        return new ConfirmationViewModelFactory(findDefaultWalletInteract, fetchGasSettingsInteract, createTransactionInteract, gasSettingsRouter);
    }

    @Provides
    FindDefaultWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new FindDefaultWalletInteract(walletRepository);
    }

    @Provides
    FetchGasSettingsInteract provideFetchGasSettingsInteract(PreferenceRepositoryType preferenceRepositoryType) {
        return new FetchGasSettingsInteract(preferenceRepositoryType);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository, PasswordStore passwordStore) {
        return new CreateTransactionInteract(transactionRepository, passwordStore);
    }

    @Provides
    GasSettingsRouter provideGasSettingsRouter() {
        return new GasSettingsRouter();
    }
}
