package com.bankex.pay.di;

import com.bankex.pay.interact.ImportWalletInteract;
import com.bankex.pay.repository.PasswordStore;
import com.bankex.pay.repository.WalletRepositoryType;
import com.bankex.pay.viewmodel.ImportWalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class ImportModule {
    @Provides
    ImportWalletViewModelFactory provideImportWalletViewModelFactory(
            ImportWalletInteract importWalletInteract) {
        return new ImportWalletViewModelFactory(importWalletInteract);
    }

    @Provides
    ImportWalletInteract provideImportWalletInteract(
            WalletRepositoryType walletRepository, PasswordStore passwordStore) {
        return new ImportWalletInteract(walletRepository, passwordStore);
    }
}
