package com.bankex.pay.router;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.entity.Wallet;
import com.bankex.pay.ui.MyAddressActivity;

import static com.bankex.pay.C.Key.WALLET;

public class MyAddressRouter {

    public void open(Context context, Wallet wallet) {
        Intent intent = new Intent(context, MyAddressActivity.class);
        intent.putExtra(WALLET, wallet);
        context.startActivity(intent);
    }
}
