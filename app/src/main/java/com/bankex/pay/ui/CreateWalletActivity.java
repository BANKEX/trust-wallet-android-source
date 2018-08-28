package com.bankex.pay.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.bankex.pay.C;
import com.bankex.pay.R;
import com.bankex.pay.router.WalletCreatedRouter;
import com.bankex.pay.util.FragmentUtils;

import dagger.android.support.DaggerAppCompatActivity;

public class CreateWalletActivity extends DaggerAppCompatActivity implements AttentionCreateWalletFragment.OnNextClickListener, ConfirmationCreateWalletFragment.ConfirmationListener {

    private String phrase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.create_wall);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AttentionCreateWalletFragment fragment = AttentionCreateWalletFragment.newInstance(getIntent().getParcelableExtra(C.AGR_EXTRA_WALLET));
        fragment.setOnNextListener(this);
        FragmentUtils.replaceFragment(getSupportFragmentManager(), fragment);
    }


    @Override
    public void onNext(String phrase) {
        this.phrase = phrase;
        ConfirmationCreateWalletFragment fragment = ConfirmationCreateWalletFragment.newInstance(phrase);
        fragment.setOnConfirmListener(this);
        FragmentUtils.replaceFragment(getSupportFragmentManager(), fragment);
    }

    @Override
    public void onConfirm() {
        new WalletCreatedRouter().open(this, phrase);
    }
}
