package com.bankex.pay.router;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.ui.OnBoardingActivity;

/**
 * @author Denis Anisimov.
 */
public class OnBoardingRouter {
    public void open(Context context) {
        Intent intent = new Intent(context, OnBoardingActivity.class);
        context.startActivity(intent);
    }
}
