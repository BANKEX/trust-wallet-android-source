package com.bankex.pay.router;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.C;
import com.bankex.pay.entity.Wallet;
import com.bankex.pay.ui.AttentionCreateWalletFragment;
import com.bankex.pay.ui.CreateWalletActivity;
import com.bankex.pay.ui.WalletCreatedActivity;

/**
 * @author Denis Anisimov.
 */
public class CreateWalletRouter {

    public void open(Context context,Wallet wallet) {
        Intent intent = new Intent(context, CreateWalletActivity.class);
        intent.putExtra(C.AGR_EXTRA_WALLET,wallet);
        context.startActivity(intent);
    }
}
