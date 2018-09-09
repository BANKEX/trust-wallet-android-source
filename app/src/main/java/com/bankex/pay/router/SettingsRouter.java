package com.bankex.pay.router;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.ui.OldSettingsActivity;

public class SettingsRouter {

    public void open(Context context) {
        Intent intent = new Intent(context, OldSettingsActivity.class);
        context.startActivity(intent);
    }
}
