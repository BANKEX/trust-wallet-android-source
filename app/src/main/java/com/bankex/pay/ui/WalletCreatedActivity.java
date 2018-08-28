package com.bankex.pay.ui;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bankex.pay.R;
import com.bankex.pay.entity.Wallet;
import com.bankex.pay.repository.RealmWalletSource;
import com.bankex.pay.router.ManageWalletsRouter;
import com.bankex.pay.viewmodel.WalletsViewModel;
import com.bankex.pay.viewmodel.WalletsViewModelFactory;

import java.util.LinkedList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class WalletCreatedActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ARG_PASSPHRASE = "ARG_PASSPHRASE";
    private TextView edit;
    private TextView next;
    private TextView walletAddress;
    private Dialog dialog;

    @Inject
    RealmWalletSource realmWalletSource;
    @Inject
    WalletsViewModelFactory walletsViewModelFactory;

    WalletsViewModel viewModel;
    Wallet wallet;
    private TextView walletName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_created);

        viewModel = ViewModelProviders.of(this, walletsViewModelFactory)
                .get(WalletsViewModel.class);
        viewModel.createdWallet().observe(this, this::onCreatedWallet);
        //viewModel.newWallet(getIntent().getStringExtra(ARG_PASSPHRASE));
        edit = findViewById(R.id.edit);
        next = findViewById(R.id.next);
        walletAddress = findViewById(R.id.wallet_addr_content);
        walletName = findViewById(R.id.eth_wallet_);
        edit.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next: {
                new ManageWalletsRouter().open(this, true);
            }
            break;
            case R.id.edit: {
                EditText input = new EditText(this);
                input.setPadding((int) getResources().getDimension(R.dimen.margin_large), 0, 0, 0);
                input.setBackgroundColor(Color.WHITE);
                dialog = buildDialog()
                        .setMessage(R.string.papi_pacify)
                        .setView(input)
                        .setPositiveButton(R.string.action_save, (dialog, which) -> {
                            hideDialog();
                            String name = input.getText().toString();
                            walletName.setText(name);
                            wallet.setName(name);
                            realmWalletSource.put(wallet);
                        })
                        .setNegativeButton(R.string.cancel,
                                (dialog, which) -> {
                                    hideDialog();
                                })
                        .create();
                dialog.show();
            }
            break;
        }
    }

    private void onCreatedWallet(Wallet wallet) {
        this.wallet = wallet;
        walletAddress.setText(wallet.address);
    }

    private AlertDialog.Builder buildDialog() {
        hideDialog();
        return new AlertDialog.Builder(this);
    }

    private void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
