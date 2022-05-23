package de.victor.horizontalslider;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.recyclerview.widget.LinearSmoothScroller;

public class CustomSmoothScroller extends LinearSmoothScroller {


    private static final String LOGTAG = "CustomSmoothScroller";

    private float animMillisPerInch = 150f;

    public CustomSmoothScroller(Context context, float animMillisPerInch) {
        super(context);
        if (animMillisPerInch > 0)
            this.animMillisPerInch = animMillisPerInch;
    }

    @Override
    public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
        return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2);

    }

    @Override
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return animMillisPerInch / displayMetrics.densityDpi;
    }
}
