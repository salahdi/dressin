package com.mustafaiev.tair.dressin.widget.propositioncardslist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Project what-wear-today
 *
 * @autor tair_mustafaiev
 * Date: 5/16/13
 * Time: 11:00 AM
 */
public class PropositionCardLayout extends LinearLayout {

    public PropositionCardLayout(Context context) {
        super(context);
    }

    public PropositionCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PropositionCardLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return true;
    }
}
