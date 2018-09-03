package com.bankex.pay.repository;

import com.bankex.pay.entity.Transaction;
import com.bankex.pay.entity.Wallet;

import io.reactivex.Single;

public interface TransactionLocalSource {
	Single<Transaction[]> fetchTransaction(Wallet wallet);

	void putTransactions(Wallet wallet, Transaction[] transactions);

    void clear();
}
