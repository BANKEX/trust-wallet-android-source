package com.bankex.pay.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bankex.pay.ui.widget.OnImportPassphraseListener;

/**
 * @author Denis Anisimov.
 */
public class ImportPassPhraseFragment extends ImportPrivateKeyFragment {

    private static final OnImportPassphraseListener DUMMY_ON_IMPORT_PASSPHRASE_LISTENER = key -> {
    };
    private OnImportPassphraseListener onImportPassphraseListener;

    public static ImportPassPhraseFragment create() {
        return new ImportPassPhraseFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scanQR.setVisibility(View.GONE);
    }

    public void setOnImportPassphraseListener(OnImportPassphraseListener onImportPassphraseListener) {
        this.onImportPassphraseListener = onImportPassphraseListener == null
                ? DUMMY_ON_IMPORT_PASSPHRASE_LISTENER
                : onImportPassphraseListener;
    }
}
