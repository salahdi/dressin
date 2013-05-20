package com.mustafaiev.tair.dressin.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import com.mustafaiev.tair.dressin.R;

public class Utils {
    public static final String VIEW_DEBUGGING_TAG = "AlarmClock";

    public static void checkRequestLayout(View v) {
        boolean inLayout = false;
        final View root = v.getRootView();

        if (root == null || v.isLayoutRequested()) {
            return;
        }

        final Error e = new Error();
        for (StackTraceElement ste : e.getStackTrace()) {
            if ("android.view.ViewGroup".equals(ste.getClassName()) && "layout".equals(ste.getMethodName())) {
                inLayout = true;
                break;
            }
        }
        if (inLayout && !v.isLayoutRequested()) {
            LogUtils.i(VIEW_DEBUGGING_TAG, e, "WARNING: in requestLayout during layout pass, view=%s", v);
        }
    }

    /**
     * Initializes progress dialog
     *
     * @param context - {@link android.content.Context}
     * @return {@link android.app.ProgressDialog}
     */
    public static ProgressDialog initProgressDialog(final Context context) {
        final ProgressDialog dialog = new ProgressDialog(context, R.style.ProgressBarTheme);
        dialog.setTitle(context.getText(R.string.data_is_loading_wait));
        dialog.setMessage(context.getText(R.string.data_is_loading));
        return dialog;
    }
}
