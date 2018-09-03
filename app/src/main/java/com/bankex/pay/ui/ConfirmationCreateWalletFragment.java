package com.bankex.pay.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bankex.pay.viewmodel.WalletsViewModel;
import com.bankex.pay.viewmodel.WalletsViewModelFactory;
import com.bankex.pay.widget.EnterPassPhraseView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmationCreateWalletFragment extends BaseSystemViewFragment implements EnterPassPhraseView.OnNextClickListener {

    private static final String ARG_PASSPHRASE = "ARG_PASSPHRASE";
    private EnterPassPhraseView mAttentionWalletView;
    private String paramPassphrase;
    private ConfirmationListener mOnConfirmListener;

    public ConfirmationCreateWalletFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramPassphrase = getArguments().getString(ARG_PASSPHRASE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAttentionWalletView = new EnterPassPhraseView(getContext(), paramPassphrase);
        mAttentionWalletView.setOnNextClickListener(this);
        systemView.showEmpty(mAttentionWalletView);
    }


    public static ConfirmationCreateWalletFragment newInstance(String paramPassphrase) {
        ConfirmationCreateWalletFragment confirmationCreateWalletFragment = new ConfirmationCreateWalletFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PASSPHRASE, paramPassphrase);
        confirmationCreateWalletFragment.setArguments(args);
        return confirmationCreateWalletFragment;
    }

    @Override
    public void onConfirmed(View view) {
        mOnConfirmListener.onConfirm();
    }

    public void setOnConfirmListener(ConfirmationListener onConfirmListener) {
        mOnConfirmListener = onConfirmListener;
    }

    public interface ConfirmationListener {
        public void onConfirm();
    }
}
