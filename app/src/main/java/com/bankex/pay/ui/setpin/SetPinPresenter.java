package com.bankex.pay.ui.setpin;

import com.bankex.pay.R;
import com.bankex.pay.repository.SharedPreferenceRepository;
import com.bankex.pay.ui.lockscreen.LockScreenPresenter;
import com.elegion.littlefinger.LittleFinger;
import com.elegion.littlefinger.crypto.CryptoAlgorithm;
import com.elegion.littlefinger.fingerprint.AuthResult;

import javax.inject.Inject;

/**
 * @author Denis Anisimov.
 */
public class SetPinPresenter {

    private SharedPreferenceRepository preferenceRepositoryType;
    private LittleFinger littleFinger;
    private SetPinView view;

    @Inject
    public SetPinPresenter(SharedPreferenceRepository preferenceRepositoryType, LittleFinger littleFinger, SetPinView setPinView) {
        this.preferenceRepositoryType = preferenceRepositoryType;
        this.littleFinger = littleFinger;
        this.view = setPinView;
    }

    public void savePin(String pin) {
        preferenceRepositoryType.setPin(pin);
        if (littleFinger.isReadyToUse()) {
            littleFinger.encode(pin, LockScreenPresenter.KEY_ALIAS, CryptoAlgorithm.RSA, this::handleResult);
        } else view.setSensorStateMessage(R.string.fp_sensor_not_supported);
    }

    private void handleResult(AuthResult authResult) {
        switch (authResult.getState()) {
            case SUCCESS: {
                String encoded = authResult.getData();
                preferenceRepositoryType.setEncodedPin(encoded);
                view.setPin();
                break;
            }
            default: {
                Throwable throwable = authResult.getThrowable();
                if (throwable != null) {
                    view.showMessage(throwable.getMessage());
                }
            }
        }
    }
}
