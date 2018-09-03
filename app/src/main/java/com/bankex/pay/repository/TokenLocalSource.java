package com.bankex.pay.repository;

import com.bankex.pay.entity.NetworkInfo;
import com.bankex.pay.entity.TokenInfo;
import com.bankex.pay.entity.Wallet;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TokenLocalSource {
    Completable put(NetworkInfo networkInfo, Wallet wallet, TokenInfo tokenInfo);
    Single<TokenInfo[]> fetch(NetworkInfo networkInfo, Wallet wallet);
}
