package com.bankex.pay.interact;

import com.bankex.pay.entity.Token;
import com.bankex.pay.entity.Wallet;
import com.bankex.pay.repository.TokenRepositoryType;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FetchTokensInteract {

    private final TokenRepositoryType tokenRepository;

    public FetchTokensInteract(TokenRepositoryType tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Observable<Token[]> fetch(Wallet wallet) {
        return tokenRepository.fetch(wallet.address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
