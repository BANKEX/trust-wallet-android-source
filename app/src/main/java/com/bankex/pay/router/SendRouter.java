package com.bankex.pay.router;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.ui.SendActivity;

public class SendRouter {

    public void open(Context context) {
        Intent intent = new Intent(context, SendActivity.class);
        context.startActivity(intent);
    }
}
