package com.bankex.pay.interact;

import com.bankex.pay.entity.Wallet;
import com.bankex.pay.repository.WalletRepositoryType;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FetchWalletsInteract {

	private final WalletRepositoryType accountRepository;

	public FetchWalletsInteract(WalletRepositoryType accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Single<Wallet[]> fetch() {
		return accountRepository
				.fetchWallets()
				.observeOn(AndroidSchedulers.mainThread());
	}
}
