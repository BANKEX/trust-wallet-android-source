package com.bankex.pay.repository;

import com.bankex.pay.entity.Transaction;
import com.bankex.pay.entity.Wallet;

import io.reactivex.Single;

public class TransactionInDiskSource implements TransactionLocalSource {
	@Override
	public Single<Transaction[]> fetchTransaction(Wallet wallet) {
		return null;
	}

	@Override
	public void putTransactions(Wallet wallet, Transaction[] transactions) {

	}

    @Override
    public void clear() {

    }
}
