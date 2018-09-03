package com.bankex.pay.router;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.entity.Wallet;
import com.bankex.pay.ui.TokensActivity;

import static com.bankex.pay.C.Key.WALLET;

public class MyTokensRouter {

    public void open(Context context, Wallet wallet) {
        Intent intent = new Intent(context, TokensActivity.class);
        intent.putExtra(WALLET, wallet);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
