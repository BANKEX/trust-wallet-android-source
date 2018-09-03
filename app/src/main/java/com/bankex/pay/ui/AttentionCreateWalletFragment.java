package com.bankex.pay.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.bankex.pay.C;
import com.bankex.pay.R;
import com.bankex.pay.entity.KeyStore;
import com.bankex.pay.entity.Wallet;
import com.bankex.pay.viewmodel.WalletsViewModel;
import com.bankex.pay.viewmodel.WalletsViewModelFactory;
import com.bankex.pay.widget.AttentionWalletView;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

import static android.content.Context.CLIPBOARD_SERVICE;

public class AttentionCreateWalletFragment extends BaseSystemViewFragment implements AttentionWalletView.OnCopyPhraseClickListener, AttentionWalletView.OnNextClickListener {


    private AttentionWalletView mAttentionWalletView;
    private Wallet wallet;

    @Inject
    WalletsViewModelFactory walletsViewModelFactory;
    WalletsViewModel viewModel;

    @Inject
    Gson gson;

    private OnNextClickListener mOnNextListener;

    public AttentionCreateWalletFragment() {

    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        //AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wallet = getArguments().getParcelable(C.AGR_EXTRA_WALLET);
        KeyStore keyStore = gson.fromJson(wallet.keyStore, KeyStore.class);
        String salt = keyStore.getCrypto().getKdfparams().getSalt();
        mAttentionWalletView = new AttentionWalletView(getContext(), wallet, salt);
        mAttentionWalletView.setOnCopyPhraseClickListener(this);
        mAttentionWalletView.setOnNextClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        systemView.showEmpty(mAttentionWalletView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCopyPhrase(String phrase) {
        viewModel = ViewModelProviders.of(this, walletsViewModelFactory)
                .get(WalletsViewModel.class);
        viewModel.copyTrue();
        Snackbar snackbar = Snackbar.make(systemView, getString(R.string.single_line), Snackbar.LENGTH_LONG);
        snackbar.setAction(getString(R.string.action), v -> snackbar.dismiss());
        snackbar.show();
        android.content.ClipboardManager c = (android.content.ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        c.setPrimaryClip(ClipData.newPlainText(phrase, phrase));
        if (mAttentionWalletView != null) mAttentionWalletView.setEnabledNext();
    }

    @Override
    public void onNext(String phrase) {
        mOnNextListener.onNext(phrase);
    }

    public static AttentionCreateWalletFragment newInstance(Wallet wallet) {
        AttentionCreateWalletFragment attentionCreateWalletFragment = new AttentionCreateWalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(C.AGR_EXTRA_WALLET, wallet);
        attentionCreateWalletFragment.setArguments(bundle);
        return attentionCreateWalletFragment;
    }

    public void setOnNextListener(CreateWalletActivity onNextListener) {
        mOnNextListener = onNextListener;
    }

    public interface OnNextClickListener {
        public void onNext(String phrase);
    }
}
