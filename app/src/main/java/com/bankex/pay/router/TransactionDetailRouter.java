package com.bankex.pay.router;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.entity.Transaction;
import com.bankex.pay.ui.TransactionDetailActivity;

import static com.bankex.pay.C.Key.TRANSACTION;

public class TransactionDetailRouter {

    public void open(Context context, Transaction transaction) {
        Intent intent = new Intent(context, TransactionDetailActivity.class);
        intent.putExtra(TRANSACTION, transaction);
        context.startActivity(intent);
    }
}
