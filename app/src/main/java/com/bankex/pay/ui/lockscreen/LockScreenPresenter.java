package com.bankex.pay.ui.lockscreen;


import android.util.Log;

import com.bankex.pay.R;
import com.bankex.pay.repository.SharedPreferenceRepository;
import com.elegion.littlefinger.LittleFinger;
import com.elegion.littlefinger.crypto.CryptoAlgorithm;
import com.elegion.littlefinger.fingerprint.AuthResult;

import javax.inject.Inject;

import static io.fabric.sdk.android.Fabric.TAG;

/**
 * @author Denis Anisimov.
 */
public class LockScreenPresenter {

    public final static String KEY_ALIAS = "KEY_ALIAS";

    private SharedPreferenceRepository preferenceRepositoryType;
    private LittleFinger littleFinger;
    private LockScreenView view;

    @Inject
    public LockScreenPresenter(SharedPreferenceRepository preferenceRepositoryType, LittleFinger littleFinger, LockScreenView mView) {
        this.preferenceRepositoryType = preferenceRepositoryType;
        this.littleFinger = littleFinger;
        this.view = mView;
    }

    public void onAuthByPinClick(String pin) {
        if (checkIsPinCorrect(pin)) view.unlock();
        else view.showMessage(R.string.pins_don_t_);
    }


    public void dispatchOnResume() {
        if (littleFinger.isReadyToUse() && preferenceRepositoryType.isPinEncoded()) {
            String encoded = preferenceRepositoryType.encodedPin();
            littleFinger.decode(encoded, KEY_ALIAS, CryptoAlgorithm.RSA, this::handleCallback);
        } else {
            view.setSensorStateMessage(R.string.fp_auth_by_fp_unavailable);
        }
    }

    private void handleCallback(AuthResult result) {
        switch (result.getState()) {
            case SUCCESS:
                if (checkIsPinCorrect(result.getData())) view.unlock();
                else view.showMessage(R.string.fp_touch_failed);
                break;
            case HELP:
                view.showMessage(R.string.fp_touch_help);
            case ERROR:
                view.showMessage(R.string.fp_touch_failed);
            case EXCEPTION: {
                if (result.isKeyInvalidated()) {
                    view.setSensorStateMessage(R.string.fp_added_or_removed_fp);
                    preferenceRepositoryType.setEncodedPin(null);
                } else {
                    view.showMessage(R.string.fp_auth_by_fp_unavailable);
                }
                Log.d(TAG, "exc", result.getThrowable());
            }
        }
    }

    private boolean checkIsPinCorrect(String data) {
        return data == preferenceRepositoryType.pin();
    }

    public void dispatchOnPause() {
        littleFinger.cancelAuth();
    }
}
