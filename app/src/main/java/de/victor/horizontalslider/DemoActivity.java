package de.victor.horizontalslider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class DemoActivity extends AppCompatActivity implements HorizontalSlider.SnapListener {

    private static final String LOGTAG = "DemoActivity";

    private TextView tvOne;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        
        data = getTestData();

        HorizontalSlider horizontalSlider = findViewById(R.id.slider);
        horizontalSlider.setData(data);
        horizontalSlider.setSnapListener(this);
        horizontalSlider.setAnimMillisPerInch(300);
        horizontalSlider.setPosition(2);
        tvOne = findViewById(R.id.tv_one);

    }

    private List<String> getTestData() {
        return Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    }

    @Override
    public void onSnapChanged(int idx) {
        Log.d(LOGTAG, "onSnapChanged: " + idx);
        tvOne.setText(data.get(idx));
    }
}