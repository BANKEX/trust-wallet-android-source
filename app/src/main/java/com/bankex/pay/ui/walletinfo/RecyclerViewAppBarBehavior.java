package com.bankex.pay.ui.walletinfo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.Behavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * @author Denis Anisimov.
 */
public class RecyclerViewAppBarBehavior extends Behavior {
    private final HashMap scrollListenerMap;

    @Override
    public boolean onNestedFling(@NotNull CoordinatorLayout coordinatorLayout, @NotNull AppBarLayout child, @Nullable View target, float velocityX, float velocityY, boolean consumed) {
        boolean isConsumed = consumed;
        if (target instanceof RecyclerView) {
            RecyclerViewAppBarBehavior.RecyclerViewScrollListener recyclerViewScrollListener;
            if (this.scrollListenerMap.get(target) == null) {
                recyclerViewScrollListener = new RecyclerViewAppBarBehavior.RecyclerViewScrollListener(coordinatorLayout, child, this);
                this.scrollListenerMap.put(target, recyclerViewScrollListener);
                ((RecyclerView) target).addOnScrollListener(recyclerViewScrollListener);
            }

            RecyclerViewAppBarBehavior.RecyclerViewScrollListener viewScrollListener = (RecyclerViewAppBarBehavior.RecyclerViewScrollListener) this.scrollListenerMap.get(target);
            if (viewScrollListener != null) {
                recyclerViewScrollListener = viewScrollListener;
                recyclerViewScrollListener.setVelocity(velocityY);
                isConsumed = recyclerViewScrollListener.getScrolledY() > 0;
            }
        }

        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, isConsumed);
    }

    public RecyclerViewAppBarBehavior(@NotNull Context context, @NotNull AttributeSet attrs) {

        super(context, attrs);
        this.scrollListenerMap = new HashMap();
    }


    private static final class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        private int scrolledY;
        private float velocity;
        private boolean dragging;
        private final WeakReference coordinatorLayoutRef;
        private final WeakReference childRef;
        private final WeakReference behaviorWeakReference;

        public final int getScrolledY() {
            return this.scrolledY;
        }

        private final void setScrolledY(int var1) {
            this.scrolledY = var1;
        }

        public final float getVelocity() {
            return this.velocity;
        }

        public final void setVelocity(float var1) {
            this.velocity = var1;
        }

        public void onScrollStateChanged(@Nullable RecyclerView recyclerView, int newState) {
            this.dragging = newState == 1;
        }

        public void onScrolled(@Nullable RecyclerView recyclerView, int dx, int dy) {
            this.scrolledY += dy;
            if (this.scrolledY <= 0 && !this.dragging && this.childRef.get() != null && this.coordinatorLayoutRef.get() != null && this.behaviorWeakReference.get() != null) {
                RecyclerViewAppBarBehavior recyclerViewAppBarBehavior = (RecyclerViewAppBarBehavior) this.behaviorWeakReference.get();
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.coordinatorLayoutRef.get();
                recyclerViewAppBarBehavior.onNestedFling(coordinatorLayout, (AppBarLayout) this.childRef.get(), (View) recyclerView, 0.0F, this.velocity, false);
            }

        }

        public RecyclerViewScrollListener(@NotNull CoordinatorLayout coordinatorLayout, @NotNull AppBarLayout child, @NotNull RecyclerViewAppBarBehavior barBehavior) {
            super();
            this.coordinatorLayoutRef = new WeakReference(coordinatorLayout);
            this.childRef = new WeakReference(child);
            this.behaviorWeakReference = new WeakReference(barBehavior);
        }
    }
}