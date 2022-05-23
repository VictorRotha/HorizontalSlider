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
    private SnapListener snapListener;

    private int snapPosition = 1;

    public interface SnapListener {
        void onSnapChanged(int idx);
    }

    public HorizontalSlider(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflator.inflate(R.layout.slider_main, this, true);

        initRecycler(context);

        View buttonLeft = getChildAt(0);
        buttonLeft.setOnClickListener(v -> {
            if (snapPosition > 1)
                scrollToPosition(snapPosition - 1);
        });

        View buttonRight = getChildAt(1);
        buttonRight.setOnClickListener(v -> {
            if (snapPosition < adapter.getItemCount()-2)
                scrollToPosition(snapPosition + 1);
        });


    }

    private void initRecycler(Context context) {

        layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        adapter = new Adapter();

        RecyclerView rcView = findViewById(R.id.rc_view);

        rcView.setLayoutManager(layoutManager);
        rcView.setAdapter(adapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rcView);

        rcView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view;
                int snapPos;

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && (view = snapHelper.findSnapView(layoutManager)) != null
                        && ((snapPos = layoutManager.getPosition(view)) != snapPosition)) {

                            if (snapPos < 1) {
                                scrollToPosition(1);
                                return;
                            }
                            if (snapPos > adapter.getItemCount() - 2) {
                                scrollToPosition(adapter.getItemCount() - 2);
                                return;
                            }
                            snapPosition = snapPos;

                            if (snapListener != null) {
                                snapListener.onSnapChanged(snapPos - 1);
                            }
                }
            }
        });
    }

    private void scrollToPosition(int pos) {
        CustomSmoothScroller smoothScroller = new CustomSmoothScroller(getContext());
        smoothScroller.setTargetPosition(pos);
        layoutManager.startSmoothScroll(smoothScroller);

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

        scrollToPosition(pos);


    }

    public void setSnapListener(SnapListener snapListener) {
        this.snapListener = snapListener;
    }




}
