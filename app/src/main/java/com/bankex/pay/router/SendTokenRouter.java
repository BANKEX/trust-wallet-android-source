package com.bankex.pay.router;


import android.content.Context;
import android.content.Intent;

import com.bankex.pay.C;
import com.bankex.pay.ui.SendActivity;

public class SendTokenRouter {
    public void open(Context context, String address, String symbol, int decimals) {
        Intent intent = new Intent(context, SendActivity.class);
        intent.putExtra(C.EXTRA_SENDING_TOKENS, true);
        intent.putExtra(C.EXTRA_CONTRACT_ADDRESS, address);
        intent.putExtra(C.EXTRA_SYMBOL, symbol);
        intent.putExtra(C.EXTRA_DECIMALS, decimals);
        context.startActivity(intent);
    }
}
