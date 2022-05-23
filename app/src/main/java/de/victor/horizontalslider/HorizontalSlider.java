package de.victor.horizontalslider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalSlider extends ConstraintLayout {

    private static final String LOGTAG = "HorizontalSlider";
    private LinearLayoutManager layoutManager;
    private Adapter adapter;
    private RecyclerView rcView;
    private CustomSmoothScroller smoothScroller;

    private int startPosition = 1;

    public HorizontalSlider(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflator.inflate(R.layout.slider_main, this, true);

        initRecycler(context);

        View buttonLeft = getChildAt(0);
        buttonLeft.setOnClickListener(v -> {
            adapter.decrementSelectedPosition();
            scrollToPosition(adapter.getSelectedPos());
        });

        View buttonRight = getChildAt(1);
        buttonRight.setOnClickListener(v -> {
            adapter.incrementSelectedPos();
            scrollToPosition(adapter.getSelectedPos());
        });


    }

    private void initRecycler(Context context) {

        layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        adapter = new Adapter();

        rcView = findViewById(R.id.rc_view);

        rcView.setLayoutManager(layoutManager);
        rcView.setAdapter(adapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rcView);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<String> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    public void setPosition(int pos) {

        if (pos > adapter.getItemCount() - 3) {
            pos = adapter.getItemCount() - 2;
        } else if (pos >= 0) {
            pos = pos + 1;
        } else {
            pos = 1;
        }

        adapter.setSelectedPos(pos);
        scrollToPosition(pos);


    }


    public void scrollToPosition(int pos) {
        smoothScroller = new CustomSmoothScroller(getContext());
        smoothScroller.setTargetPosition(pos);
        layoutManager.startSmoothScroll(smoothScroller);

    }


}
