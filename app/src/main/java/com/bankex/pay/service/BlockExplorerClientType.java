package com.bankex.pay.service;

import com.bankex.pay.entity.Transaction;

import io.reactivex.Observable;

public interface BlockExplorerClientType {
	Observable<Transaction[]> fetchTransactions(String forAddress);
}
