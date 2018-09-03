package com.bankex.pay.repository;

import com.bankex.pay.entity.Transaction;
import com.bankex.pay.entity.Wallet;

import java.math.BigInteger;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface TransactionRepositoryType {
	Observable<Transaction[]> fetchTransaction(Wallet wallet);
	Maybe<Transaction> findTransaction(Wallet wallet, String transactionHash);
	Single<String> createTransaction(Wallet from, String toAddress, BigInteger subunitAmount, BigInteger gasPrice, BigInteger gasLimit, byte[] data, String password);
}
