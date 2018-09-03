package com.bankex.pay.interact.rx.operator;

import com.bankex.pay.entity.Wallet;
import com.bankex.pay.repository.PasswordStore;
import com.bankex.pay.repository.WalletRepositoryType;

import io.reactivex.CompletableOperator;
import io.reactivex.SingleTransformer;

public class Operators {

    public static SingleTransformer<Wallet, Wallet> savePassword(
            PasswordStore passwordStore, WalletRepositoryType walletRepository, String password) {
        return new SavePasswordOperator(passwordStore, walletRepository, password);
    }

    public static CompletableOperator completableErrorProxy(Throwable throwable) {
        return new CompletableErrorProxyOperator(throwable);
    }
}
