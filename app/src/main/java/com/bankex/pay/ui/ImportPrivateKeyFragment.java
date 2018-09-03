package com.bankex.pay.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bankex.pay.R;
import com.bankex.pay.ui.barcode.BarcodeCaptureActivity;
import com.bankex.pay.ui.widget.OnImportPrivateKeyListener;

public class ImportPrivateKeyFragment extends Fragment implements View.OnClickListener {

    private static final OnImportPrivateKeyListener dummyOnImportPrivateKeyListener = key -> {
    };

    protected EditText privateKey;
    protected TextView scanQR;
    protected TextView paste;
    private OnImportPrivateKeyListener onImportPrivateKeyListener;
    protected View importButton;

    public static ImportPrivateKeyFragment create() {
        return new ImportPrivateKeyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_import_private_key, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        privateKey = view.findViewById(R.id.private_key);
        scanQR = view.findViewById(R.id.scan_qr);
        paste = view.findViewById(R.id.paste);
        importButton = view.findViewById(R.id.generateWalletButton);
        importButton.setOnClickListener(this);
        view.findViewById(R.id.scan_qr).setOnClickListener(this);
        view.findViewById(R.id.paste).setOnClickListener(this);
        privateKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) importButton.setEnabled(true);
                else importButton.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.private_key: {
                privateKey.setError(null);
                String value = privateKey.getText().toString();
                if (TextUtils.isEmpty(value) || value.length() != 64) {
                    privateKey.setError(getString(R.string.error_field_required));
                } else {
                    onImportPrivateKeyListener.onPrivateKey(privateKey.getText().toString());
                }
            }
            break;
            case R.id.scan_qr: {
                Intent intent = new Intent(getActivity().getApplicationContext(), BarcodeCaptureActivity.class);
                startActivityForResult(intent, SendActivity.BARCODE_READER_REQUEST_CODE);
            }
            break;
            case R.id.paste: {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard == null) return;
                ClipData clip = clipboard.getPrimaryClip();
                if (clip == null) return;
                ClipData.Item item = clip.getItemAt(0);
                if (item == null) return;
                CharSequence textToPaste = item.getText();
                if (textToPaste == null) return;
                privateKey.setText(textToPaste);
            }
            break;
        }

    }

    public void setOnImportPrivateKeyListener(OnImportPrivateKeyListener onImportPrivateKeyListener) {
        this.onImportPrivateKeyListener = onImportPrivateKeyListener == null
                ? dummyOnImportPrivateKeyListener
                : onImportPrivateKeyListener;
    }
}
