package com.mustafaiev.tair.dressin.widget.propositioncardslist;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;
import com.mustafaiev.tair.dressin.utils.*;

public class PropositionCardsListView extends ListView implements PropositionCardsHelper.Callback {

    private PropositionCardsHelper mCardsHelper;
    private boolean mEnableSwipe = false;

    public static final String LOG_TAG = PropositionCardsListView.class.getCanonicalName();

    private OnItemSwipeListener mOnItemSwipeListener;


    public PropositionCardsListView(Context context) {
        this(context, null);

    }

    public PropositionCardsListView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PropositionCardsListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        float densityScale = getResources().getDisplayMetrics().density;
        float pagingTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
        mCardsHelper = new PropositionCardsHelper(context, PropositionCardsHelper.X, this, densityScale, pagingTouchSlop);
        setItemsCanFocus(true);
    }

    /**
     * Enable swipe gestures.
     */
    public void enableSwipe(boolean enable) {
        mEnableSwipe = enable;
    }

    public boolean isSwipeEnabled() {
        return mEnableSwipe;
    }

    public void setOnItemSwipeListener(OnItemSwipeListener listener) {
        mOnItemSwipeListener = listener;
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        float densityScale = getResources().getDisplayMetrics().density;
        mCardsHelper.setDensityScale(densityScale);
        float pagingTouchSlop = ViewConfiguration.get(getContext()).getScaledPagingTouchSlop();
        mCardsHelper.setPagingTouchSlop(pagingTouchSlop);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public void requestLayout() {
        Utils.checkRequestLayout(this);
        super.requestLayout();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mEnableSwipe) {
            return mCardsHelper.onInterceptTouchEvent(ev) || super.onInterceptTouchEvent(ev);
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mEnableSwipe) {
            return mCardsHelper.onTouchEvent(ev) || super.onTouchEvent(ev);
        } else {
            return super.onTouchEvent(ev);
        }
    }

    @Override
    public View getChildAtPosition(MotionEvent ev) {
        // find the view under the pointer, accounting for GONE views
        final int count = getChildCount();
        int touchY = (int) ev.getY();
        int childIdx = 0;
        View slidingChild;
        for (; childIdx < count; childIdx++) {
            slidingChild = getChildAt(childIdx);
            if (slidingChild.getVisibility() == GONE) {
                continue;
            }
            if (touchY >= slidingChild.getTop() && touchY <= slidingChild.getBottom()) {
                return slidingChild;
            }
        }
        return null;
    }

    @Override
    public View getChildContentView(View view) {
        return view;
    }

    @Override
    public void onScroll() {
    }

    @Override
    public boolean canChildBeDismissed(View v) {
        return true;
    }

    @Override
    public void onBeginDrag(View v) {
    }

    @Override
    public void onChildDismissed(final View v) {
        if (v != null) {
            if (mOnItemSwipeListener != null) {
                mOnItemSwipeListener.onSwipe(v);
            }
        }
    }

    @Override
    public void onDragCancelled(View v) {
    }

    private void redraw(View v) {
        int start = getFirstVisiblePosition();
        for (int i = start, j = getLastVisiblePosition(); i <= j; i++) {
            if (v == getItemAtPosition(i)) {
                View view = getChildAt(i - start);
                getAdapter().getView(i, view, this);
            }
        }
    }

    public interface OnItemSwipeListener {
        public void onSwipe(View view);
    }
}
