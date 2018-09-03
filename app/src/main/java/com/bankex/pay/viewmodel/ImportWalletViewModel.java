package com.bankex.pay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.bankex.pay.entity.Wallet;
import com.bankex.pay.interact.ImportWalletInteract;
import com.bankex.pay.ui.widget.OnImportKeystoreListener;
import com.bankex.pay.ui.widget.OnImportPassphraseListener;
import com.bankex.pay.ui.widget.OnImportPrivateKeyListener;

public class ImportWalletViewModel extends BaseViewModel implements OnImportKeystoreListener, OnImportPrivateKeyListener, OnImportPassphraseListener {

    private final ImportWalletInteract importWalletInteract;
    private final MutableLiveData<Wallet> wallet = new MutableLiveData<>();

    ImportWalletViewModel(ImportWalletInteract importWalletInteract) {
        this.importWalletInteract = importWalletInteract;
    }

    @Override
    public void onKeystore(String keystore, String password) {
        progress.postValue(true);
        importWalletInteract
                .importKeystore(keystore, password)
                .subscribe(this::onWallet, this::onError);
    }

    @Override
    public void onPrivateKey(String key) {
        progress.postValue(true);
        importWalletInteract
                .importPrivateKey(key)
                .subscribe(this::onWallet, this::onError);
    }

    public LiveData<Wallet> wallet() {
        return wallet;
    }

    private void onWallet(Wallet wallet) {
        progress.postValue(false);
        this.wallet.postValue(wallet);
    }

    @Override
    public void onPassphrase(String passphrase) {

    }
}
