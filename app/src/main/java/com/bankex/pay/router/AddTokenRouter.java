package com.bankex.pay.router;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.ui.AddTokenActivity;

public class AddTokenRouter {

    public void open(Context context) {
        Intent intent = new Intent(context, AddTokenActivity.class);
        context.startActivity(intent);
    }
}
