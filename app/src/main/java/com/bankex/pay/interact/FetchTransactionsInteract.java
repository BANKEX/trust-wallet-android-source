package com.bankex.pay.interact;

import com.bankex.pay.entity.Transaction;
import com.bankex.pay.entity.Wallet;
import com.bankex.pay.repository.TransactionRepositoryType;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FetchTransactionsInteract {

    private final TransactionRepositoryType transactionRepository;

    public FetchTransactionsInteract(TransactionRepositoryType transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Observable<Transaction[]> fetch(Wallet wallet) {
        return transactionRepository
                .fetchTransaction(wallet)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
