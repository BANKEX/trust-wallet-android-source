package com.bankex.pay.ui.setpin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.bankex.pay.R;

import javax.inject.Inject;


public class SetPinActivity extends AppCompatActivity implements SetPinView {

    @Inject
    SetPinPresenter presenter;
    private TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);
        editText = findViewById(R.id.pin);
    }

    @Override
    protected void onResume() {
        super.onResume();

        editText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                presenter.savePin(editText.getText().toString());
            }
            return false;
        });
    }

    @Override
    protected void onPause() {
        editText.setOnKeyListener(null);
        super.onPause();
    }

    @Override
    public void setPin() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSensorStateMessage(int messageRes) {

    }
}
