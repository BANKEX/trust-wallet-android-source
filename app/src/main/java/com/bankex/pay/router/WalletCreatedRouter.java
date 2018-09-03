package com.bankex.pay.router;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.ui.WalletCreatedActivity;

/**
 * @author Denis Anisimov.
 */
public class WalletCreatedRouter {

    public void open(Context context, String passphrase) {
        Intent intent = new Intent(context, WalletCreatedActivity.class);
        intent.putExtra(WalletCreatedActivity.ARG_PASSPHRASE, passphrase);
        context.startActivity(intent);
    }
}
