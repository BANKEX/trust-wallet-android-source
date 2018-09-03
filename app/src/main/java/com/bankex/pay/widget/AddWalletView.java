package com.bankex.pay.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.bankex.pay.R;


public class AddWalletView extends FrameLayout implements View.OnClickListener {
	private OnNewWalletClickListener onNewWalletClickListener;
	private OnImportWalletClickListener onImportWalletClickListener;

	public AddWalletView(Context context) {
		this(context, R.layout.layout_dialog_add_account);
	}

	public AddWalletView(Context context, @LayoutRes int layoutId) {
		super(context);

		init(layoutId);
	}

	private void init(@LayoutRes int layoutId) {
		LayoutInflater.from(getContext()).inflate(layoutId, this, true);
		findViewById(R.id.createWallet).setOnClickListener(this);
		findViewById(R.id.importWallet).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.createWallet: {
				if (onNewWalletClickListener != null) {
					onNewWalletClickListener.onNewWallet(view);
				}
			} break;
			case R.id.importWallet: {
				if (onImportWalletClickListener != null) {
					onImportWalletClickListener.onImportWallet(view);
				}
			} break;
		}
	}
	
	public void setOnNewWalletClickListener(OnNewWalletClickListener onNewWalletClickListener) {
		this.onNewWalletClickListener = onNewWalletClickListener;
	}
	
	public void setOnImportWalletClickListener(OnImportWalletClickListener onImportWalletClickListener) {
		this.onImportWalletClickListener = onImportWalletClickListener;
	}

	public interface OnNewWalletClickListener {
		void onNewWallet(View view);
	}

	public interface OnImportWalletClickListener {
		void onImportWallet(View view);
	}



    private static class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
