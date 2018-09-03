package com.bankex.pay.service;

import com.bankex.pay.entity.TokenInfo;

import io.reactivex.Observable;

public interface TokenExplorerClientType {
    Observable<TokenInfo[]> fetch(String walletAddress);
}