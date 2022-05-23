package de.victor.horizontalslider;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearSmoothScroller;

public class CustomSmoothScroller extends LinearSmoothScroller {


    private static final String LOGTAG = "CustomSmoothScroller";

    public CustomSmoothScroller(Context context) {
        super(context);
    }

    @Override
    public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
        return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2);


    }
}
