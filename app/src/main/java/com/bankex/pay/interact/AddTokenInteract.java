package com.bankex.pay.interact;

import com.bankex.pay.repository.TokenRepositoryType;
import com.bankex.pay.repository.WalletRepositoryType;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AddTokenInteract {
    private final TokenRepositoryType tokenRepository;
    private final WalletRepositoryType walletRepository;

    public AddTokenInteract(
            WalletRepositoryType walletRepository, TokenRepositoryType tokenRepository) {
        this.walletRepository = walletRepository;
        this.tokenRepository = tokenRepository;
    }

    public Completable add(String address, String symbol, int decimals) {
        return walletRepository
                .getDefaultWallet()
                .flatMapCompletable(wallet -> tokenRepository
                        .addToken(wallet, address, symbol, decimals)
                        .observeOn(AndroidSchedulers.mainThread()));
    }
}
