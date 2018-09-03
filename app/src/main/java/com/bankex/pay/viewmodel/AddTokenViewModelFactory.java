package com.bankex.pay.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.bankex.pay.interact.AddTokenInteract;
import com.bankex.pay.interact.FindDefaultWalletInteract;
import com.bankex.pay.router.MyTokensRouter;

public class AddTokenViewModelFactory implements ViewModelProvider.Factory {

    private final AddTokenInteract addTokenInteract;
    private final FindDefaultWalletInteract findDefaultWalletInteract;
    private final MyTokensRouter myTokensRouter;

    public AddTokenViewModelFactory(
            AddTokenInteract addTokenInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            MyTokensRouter myTokensRouter) {
        this.addTokenInteract = addTokenInteract;
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.myTokensRouter = myTokensRouter;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTokenViewModel(addTokenInteract, findDefaultWalletInteract, myTokensRouter);
    }
}
