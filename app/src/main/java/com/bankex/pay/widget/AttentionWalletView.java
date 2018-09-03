package com.bankex.pay.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bankex.pay.R;
import com.bankex.pay.entity.Wallet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import io.github.novacrypto.bip39.WordList;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;


public class AttentionWalletView extends FrameLayout implements View.OnClickListener {
    public static final int WORD_COUNT = 12;
    private OnNextClickListener onNextClickListener;
    private OnCopyPhraseClickListener onCopyPhraseClickListener;
    private TextView tvfaker;
    private View next;
    private Wallet wallet;
    private String salt;

    public AttentionWalletView(Context context, Wallet wallet, String salt) {
        this(context, R.layout.layout_dialog_add_account, wallet, salt);
    }

    private AttentionWalletView(Context context, @LayoutRes int layoutId, Wallet wallet, String salt) {
        super(context);
        this.wallet = wallet;
        this.salt = salt;
        init(layoutId);
    }

    public static String createMnemonic(
            final byte[] entropy,
            final WordList wordList) throws NoSuchAlgorithmException {
        //prechecks
        final int ent = entropy.length * 8;
        if (ent < 128)
            throw new RuntimeException("Entropy too low, 128-256 bits allowed");
        if (ent > 256)
            throw new RuntimeException("Entropy too high, 128-256 bits allowed");
        if (ent % 32 > 0)
            throw new RuntimeException("Number of entropy bits must be divisible by 32");

        //checksum length (bits)
        final int cs = ent / 32;
        //mnemonic length (words)
        final int ms = (ent + cs) / 11;

        //make a copy with space for one more
        final byte[] entropyWithChecksum = Arrays.copyOf(entropy, entropy.length + 1);
        //hash the original
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hash = digest.digest(entropy);
        //append the first byte of the hash to the copy
        entropyWithChecksum[entropy.length] = hash[0];

        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ent + cs; i += 11) {
            final int wordIndex = next11Bits(entropyWithChecksum, i);
            sb.append(wordList.getWord(wordIndex));
            sb.append(wordList.getSpace());
        }
        //drop the last space
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    static int next11Bits(byte[] bytes, int offset) {
        final int skip = offset / 8;
        final int lowerBitsToRemove = (3 * 8 - 11) - (offset % 8);
        return (((int) bytes[skip] & 0xff) << 16 |
                ((int) bytes[skip + 1] & 0xff) << 8 |
                (lowerBitsToRemove < 8
                        ? ((int) bytes[skip + 2] & 0xff)
                        : 0)) >> lowerBitsToRemove & (1 << 11) - 1;
    }

    private void init(@LayoutRes int layoutId) {
        LayoutInflater.from(getContext()).inflate(layoutId, this, true);
        findViewById(R.id.copyPhrase).setOnClickListener(this);
        next = findViewById(R.id.next);
        next.setOnClickListener(this);

        tvfaker = findViewById(R.id.faker);
        try {
            byte[] entropy = new byte[Words.TWELVE.byteLength()];
            new SecureRandom().nextBytes(entropy);
            tvfaker.setText(createMnemonic(entropy, English.INSTANCE));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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
