package br.com.objetiveti.pedidodevendas.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import br.com.objetiveti.pedidodevendas.R;

public class AcRelatorios extends AppCompatActivity {
    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);

        barChart = (BarChart) findViewById(R.id.bargrahp);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44f,0));
        barEntries.add(new BarEntry(88f,1));
        barEntries.add(new BarEntry(66f,2));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");


        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("April");
        theDates.add("May");
        theDates.add("June");

       // BarData theData = new BarData(theDates, barDataSet);
      //  barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }
}
