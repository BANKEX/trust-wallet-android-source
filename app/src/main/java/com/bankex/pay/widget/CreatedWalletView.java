package com.bankex.pay.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bankex.pay.R;


public class CreatedWalletView extends FrameLayout implements View.OnClickListener {
    public static final int WORD_COUNT = 12;
    private OnNextClickListener onNextClickListener;
    private OnCopyPhraseClickListener onCopyPhraseClickListener;
    private TextView tvfaker;
    private View next;

    public CreatedWalletView(Context context) {
        this(context, R.layout.activity_wallet_created);
    }

    private CreatedWalletView(Context context, @LayoutRes int layoutId) {
        super(context);

        init(layoutId);
    }

    private void init(@LayoutRes int layoutId) {
        LayoutInflater.from(getContext()).inflate(layoutId, this, true);
        findViewById(R.id.copyPhrase).setOnClickListener(this);
        next = findViewById(R.id.next);
        next.setOnClickListener(this);

        tvfaker = findViewById(R.id.faker);
        tvfaker.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.copyPhrase: {
                if (onCopyPhraseClickListener != null) {
                    onCopyPhraseClickListener.onCopyPhrase(tvfaker.getText().toString());
                }
            }
            break;
            case R.id.next: {
                if (onNextClickListener != null) {
                    onNextClickListener.onNext(tvfaker.getText().toString());
                }
            }
            break;
        }
    }

    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.onNextClickListener = onNextClickListener;
    }

    public void setOnCopyPhraseClickListener(OnCopyPhraseClickListener onCopyPhraseClickListener) {
        this.onCopyPhraseClickListener = onCopyPhraseClickListener;
    }

    public void setEnabledNext() {
        next.setEnabled(true);
    }

    public interface OnNextClickListener {
        void onNext(String phrase);
    }

    public interface OnCopyPhraseClickListener {
        void onCopyPhrase(String phrase);
    }
}
