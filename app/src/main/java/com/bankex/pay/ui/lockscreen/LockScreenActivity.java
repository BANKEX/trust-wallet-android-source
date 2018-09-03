package com.bankex.pay.ui.lockscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.bankex.pay.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class LockScreenActivity extends AppCompatActivity implements LockScreenView {

    @Inject
    LockScreenPresenter presenter;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        editText = findViewById(R.id.pin);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.dispatchOnResume();
        editText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                presenter.onAuthByPinClick(editText.getText().toString());
            }
            return false;
        });
    }

    @Override
    protected void onPause() {
        presenter.dispatchOnPause();
        editText.setOnKeyListener(null);
        super.onPause();
    }


    @Override
    public void unlock() {

    }

    @Override
    public void showMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSensorStateMessage(int messageRes) {

    }
}
