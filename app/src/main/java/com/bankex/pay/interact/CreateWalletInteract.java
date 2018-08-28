package com.bankex.pay.interact;

import com.bankex.pay.entity.Wallet;
import com.bankex.pay.interact.rx.operator.Operators;
import com.bankex.pay.repository.PasswordStore;
import com.bankex.pay.repository.WalletRepositoryType;

import io.reactivex.Single;

public class CreateWalletInteract {

	private final WalletRepositoryType walletRepository;
	private final PasswordStore passwordStore;

	public CreateWalletInteract(WalletRepositoryType walletRepository, PasswordStore passwordStore) {
		this.walletRepository = walletRepository;
		this.passwordStore = passwordStore;
	}

	public Single<Wallet> create() {
		return passwordStore.generatePassword()
				.flatMap(password -> walletRepository
						.createWallet(password)
						.compose(Operators.savePassword(passwordStore, walletRepository, password)));

	}
}
