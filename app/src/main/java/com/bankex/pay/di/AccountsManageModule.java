package com.bankex.pay.di;

import com.bankex.pay.interact.CreateWalletInteract;
import com.bankex.pay.interact.DeleteWalletInteract;
import com.bankex.pay.interact.ExportWalletInteract;
import com.bankex.pay.interact.FetchWalletsInteract;
import com.bankex.pay.interact.FindDefaultWalletInteract;
import com.bankex.pay.interact.SetDefaultWalletInteract;
import com.bankex.pay.repository.PasswordStore;
import com.bankex.pay.repository.WalletRepositoryType;
import com.bankex.pay.router.CreateWalletRouter;
import com.bankex.pay.router.ImportWalletRouter;
import com.bankex.pay.router.TransactionsRouter;
import com.bankex.pay.viewmodel.WalletsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class AccountsManageModule {

    @Provides
    WalletsViewModelFactory provideAccountsManageViewModelFactory(
            CreateWalletInteract createWalletInteract,
            SetDefaultWalletInteract setDefaultWalletInteract,
            DeleteWalletInteract deleteWalletInteract,
            FetchWalletsInteract fetchWalletsInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            ExportWalletInteract exportWalletInteract,
            ImportWalletRouter importWalletRouter,
            CreateWalletRouter createWalletRouter,
            TransactionsRouter transactionsRouter) {
        return new WalletsViewModelFactory(createWalletInteract,
                setDefaultWalletInteract,
                deleteWalletInteract,
                fetchWalletsInteract,
                findDefaultWalletInteract,
                exportWalletInteract,
                importWalletRouter,
                createWalletRouter,
                transactionsRouter);
    }

    @Provides
    CreateWalletInteract provideCreateAccountInteract(
            WalletRepositoryType accountRepository, PasswordStore passwordStore) {
        return new CreateWalletInteract(accountRepository, passwordStore);
    }

    @Provides
    SetDefaultWalletInteract provideSetDefaultAccountInteract(WalletRepositoryType accountRepository) {
        return new SetDefaultWalletInteract(accountRepository);
    }

    @Provides
    DeleteWalletInteract provideDeleteAccountInteract(
            WalletRepositoryType accountRepository, PasswordStore store) {
        return new DeleteWalletInteract(accountRepository, store);
    }

    @Provides
    FetchWalletsInteract provideFetchAccountsInteract(WalletRepositoryType accountRepository) {
        return new FetchWalletsInteract(accountRepository);
    }

    @Provides
    FindDefaultWalletInteract provideFindDefaultAccountInteract(WalletRepositoryType accountRepository) {
        return new FindDefaultWalletInteract(accountRepository);
    }

    @Provides
    ExportWalletInteract provideExportWalletInteract(
            WalletRepositoryType walletRepository, PasswordStore passwordStore) {
        return new ExportWalletInteract(walletRepository, passwordStore);
    }

    @Provides
    ImportWalletRouter provideImportAccountRouter() {
        return new ImportWalletRouter();
    }

    @Provides
    CreateWalletRouter provideWalletRouter() {
        return new CreateWalletRouter();
    }

    @Provides
    TransactionsRouter provideTransactionsRouter() {
        return new TransactionsRouter();
    }
}
