package com.bankex.pay.ui.setpin;

/**
 * @author Denis Anisimov.
 */
public interface SetPinView {
    void setPin();

    void showMessage(String message);

    void setSensorStateMessage(int messageRes);
}
