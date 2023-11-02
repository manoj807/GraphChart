
package com.valleytech.graphchart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.valleytech.graphchart.notimportant.DemoBase;

import java.util.ArrayList;


public class AnotherDataBarActivity extends DemoBase {

    private BarChart chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_data_barchart);

        setTitle("AnotherBarActivity");



        chart = findViewById(R.id.chart1);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
       // chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(false);


        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setDrawGridLines(false);

        // add a nice and smooth animation
        chart.animateY(1500);

        chart.getLegend().setEnabled(false);
        onProgressChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar, menu);
        menu.removeItem(R.id.actionToggleIcons);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.viewGithub) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/AnotherBarActivity.java"));
            startActivity(i);
        } else if (itemId == R.id.actionToggleValues) {
            for (IDataSet set : chart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled());

            chart.invalidate();
            /*
            case R.id.actionToggleIcons: { break; }
             */
        } else if (itemId == R.id.actionToggleHighlight) {
            if (chart.getData() != null) {
                chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                chart.invalidate();
            }
        } else if (itemId == R.id.actionTogglePinch) {
            if (chart.isPinchZoomEnabled())
                chart.setPinchZoom(false);
            else
                chart.setPinchZoom(true);

            chart.invalidate();
        } else if (itemId == R.id.actionToggleAutoScaleMinMax) {
            chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
            chart.notifyDataSetChanged();
        } else if (itemId == R.id.actionToggleBarBorders) {
            for (IBarDataSet set : chart.getData().getDataSets())
                ((BarDataSet) set).setBarBorderWidth(set.getBarBorderWidth() == 1.f ? 0.f : 1.f);

            chart.invalidate();
        } else if (itemId == R.id.animateX) {
            chart.animateX(2000);
        } else if (itemId == R.id.animateY) {
            chart.animateY(2000);
        } else if (itemId == R.id.animateXY) {
            chart.animateXY(2000, 2000);
        } else if (itemId == R.id.actionSave) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                saveToGallery();
            } else {
                requestStoragePermission(chart);
            }
        }
        return true;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "AnotherBarActivity");
    }


    public void onProgressChanged() {


        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            float multi = (2+ 1);
            float val = (float) (Math.random() * multi) + multi / 3;
            values.add(new BarEntry(i*10, val));
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setBarBorderColor(getColor(R.color.white));
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "Data Set");
            //set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);
            set1.setColor(Color.rgb(104, 241, 175));
            set1.setBarBorderWidth(2);
            set1.setBarBorderColor(getColor(R.color.white));
           // set1.setFormLineWidth(20);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setBarWidth(10);

            chart.setData(data);
            chart.setFitBars(true);
        }

        chart.invalidate();
    }


}
