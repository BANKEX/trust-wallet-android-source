package com.bankex.pay.router;


import android.app.Activity;
import android.content.Intent;

import com.bankex.pay.C;
import com.bankex.pay.entity.GasSettings;
import com.bankex.pay.ui.GasSettingsActivity;
import com.bankex.pay.viewmodel.GasSettingsViewModel;

public class GasSettingsRouter {
    public void open(Activity context, GasSettings gasSettings) {
        Intent intent = new Intent(context, GasSettingsActivity.class);
        intent.putExtra(C.EXTRA_GAS_PRICE, gasSettings.gasPrice.toString());
        intent.putExtra(C.EXTRA_GAS_LIMIT, gasSettings.gasLimit.toString());
        context.startActivityForResult(intent, GasSettingsViewModel.SET_GAS_SETTINGS);
    }
}
