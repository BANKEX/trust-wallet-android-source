package com.bankex.pay.ui.widget;

import android.view.View;

import com.bankex.pay.entity.Transaction;

public interface OnTransactionClickListener {
    void onTransactionClick(View view, Transaction transaction);
}
