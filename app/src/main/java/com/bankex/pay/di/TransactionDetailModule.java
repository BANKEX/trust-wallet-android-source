package com.bankex.pay.di;

import com.bankex.pay.interact.FindDefaultNetworkInteract;
import com.bankex.pay.interact.FindDefaultWalletInteract;
import com.bankex.pay.repository.EthereumNetworkRepositoryType;
import com.bankex.pay.repository.WalletRepositoryType;
import com.bankex.pay.router.ExternalBrowserRouter;
import com.bankex.pay.viewmodel.TransactionDetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionDetailModule {

    @Provides
    TransactionDetailViewModelFactory provideTransactionDetailViewModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            ExternalBrowserRouter externalBrowserRouter) {
        return new TransactionDetailViewModelFactory(
                findDefaultNetworkInteract, findDefaultWalletInteract, externalBrowserRouter);
    }

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType ethereumNetworkRepository) {
        return new FindDefaultNetworkInteract(ethereumNetworkRepository);
    }

    @Provides
    ExternalBrowserRouter externalBrowserRouter() {
        return new ExternalBrowserRouter();
    }

    @Provides
    FindDefaultWalletInteract findDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new FindDefaultWalletInteract(walletRepository);
    }
}
