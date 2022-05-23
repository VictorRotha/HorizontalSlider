package de.victor.horizontalslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class DemoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        HorizontalSlider horizontalSlider = findViewById(R.id.slider);
        horizontalSlider.setData(getTestData());


    }




    private List<String> getTestData() {
        return Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    }
}