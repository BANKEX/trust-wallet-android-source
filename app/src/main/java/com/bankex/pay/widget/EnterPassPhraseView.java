package com.bankex.pay.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bankex.pay.R;
import com.bankex.pay.util.PhraseUtils;
import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Denis Anisimov.
 */
public class EnterPassPhraseView extends FrameLayout implements View.OnClickListener {
    private OnNextClickListener mOnNextClickListener;


    private ChipView chipsOriginalTV;
    private ChipView chipsTV;
    private final List<Chip> chips;
    private List<Chip> chipRandom;
    private TextView next;
    private TextView wrongOrder;


    public EnterPassPhraseView(Context context, String passphrase) {
        super(context);
        chips = PhraseUtils.getChips(passphrase);
        init(R.layout.layout_dialog_enter_passphrase, passphrase);
    }

    private void init(@LayoutRes int layoutId, String passphrase) {
        LayoutInflater.from(getContext()).inflate(layoutId, this, true);
        chipsOriginalTV = findViewById(R.id.chip_original);
        chipsTV = findViewById(R.id.chip_verify);
        wrongOrder = findViewById(R.id.wrong_order);
        next = findViewById(R.id.next);
        next.setOnClickListener(this::onClick);
        String[] suggestions = passphrase.split("\\W+");
        chipRandom = new ArrayList<>();
        chipRandom.addAll(chips);
        Collections.shuffle(chipRandom);
        chipsOriginalTV.setChipLayoutRes(R.layout.w_chip);
        chipsTV.setChipLayoutRes(R.layout.w_chip);
      /*  chipsTV.setAdapter(new ChipViewAdapter(getContext()) {

            @Override
            public int getLayoutRes(int position) {
                return R.layout.w_chip1;
            }

            @Override
            public int getBackgroundRes(int position) {
                return 0;
            }

            @Override
            public int getBackgroundColor(int position) {
                return 0;
                *//*PhraseUtils.Tag chip = (PhraseUtils.Tag) chips.get(position);
                if (chip.getType() == 0)
                    return android.R.color.transparent;
                else return R.color.white;*//*
            }

            @Override
            public int getBackgroundColorSelected(int position) {
                return android.R.color.transparent;
            }

            @Override
            public void onLayout(View view, int position) {

            }

        });*/
        chipsOriginalTV.setChipList(chipRandom);
        chipsOriginalTV.setOnChipClickListener(chip -> {
            chipsTV.add(chip);
            chipsTV.refresh();
            chipsOriginalTV.remove(chip);
            if (chipsOriginalTV.getChipList().size() == 0)
                next.setEnabled(true);
        /*    PhraseUtils.Tag chip1 = (PhraseUtils.Tag) chip;
            //int position = chip1.getPosition();
            int count = chipsTV.getAdapter().count();
            String text = chip.getText();
            Chip chip2 = chips.get(count);
            if (text.equals(chip2.getText())) {

            }*/
        });

        findViewById(R.id.next).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next: {
                if (mOnNextClickListener != null) {
                    mOnNextClickListener.onConfirmed(view);
                } else wrongOrder.setEnabled(true);
            }
            break;
        }
    }

    private boolean confirm() {
        List<Chip> chipList = chipsTV.getChipList();
        int i = 0;
        for (Chip chip : chipList) {
            if (chip.getText().equals(chips.get(i).getText())) i++;
            else return false;
        }
        return true;
    }

    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.mOnNextClickListener = onNextClickListener;
    }

    public interface OnNextClickListener {
        void onConfirmed(View view);
    }
}
