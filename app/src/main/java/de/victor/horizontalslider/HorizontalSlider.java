package de.victor.horizontalslider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalSlider extends ConstraintLayout {

    private LinearLayoutManager layoutManager;
    private Adapter adapter;

    public HorizontalSlider(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflator.inflate(R.layout.slider_main, this, true);

        layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        adapter = new Adapter();

        RecyclerView rcView = findViewById(R.id.rc_view);

        rcView.setLayoutManager(layoutManager);
        rcView.setAdapter(adapter);

    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<String> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }


}
