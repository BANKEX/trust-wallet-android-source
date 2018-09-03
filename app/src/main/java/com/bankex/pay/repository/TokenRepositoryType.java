package com.bankex.pay.repository;

import com.bankex.pay.entity.Token;
import com.bankex.pay.entity.Wallet;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface TokenRepositoryType {

    Observable<Token[]> fetch(String walletAddress);

    Completable addToken(Wallet wallet, String address, String symbol, int decimals);
}
