package com.bankex.pay.ui.lockscreen;

/**
 * @author Denis Anisimov.
 */
public interface LockScreenView {
    void unlock();

    void showMessage(int message);

    void setSensorStateMessage(int messageRes);
}
