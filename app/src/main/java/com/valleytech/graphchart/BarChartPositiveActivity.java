
package com.valleytech.graphchart;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.valleytech.graphchart.custom.BarChartMarkerView;
import com.valleytech.graphchart.custom.DayAxisValueFormatter;
import com.valleytech.graphchart.notimportant.DemoBase;
import com.valleytech.graphchart.utils.Data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BarChartPositiveActivity extends DemoBase implements OnChartValueSelectedListener {

    private BarChart chart;
    final List<Data> dataList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barchart_noseekbar);

        setTitle("BarChartPositiveNegative");
        com.github.mikephil.charting.formatter.ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

        chart = findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.WHITE);
        chart.setOnChartValueSelectedListener(this);
        chart.getDescription().setText("Datadddd");
        chart.setExtraTopOffset(10f);
        chart.setExtraBottomOffset(2f);
        chart.setExtraLeftOffset(12f);
        chart.setExtraRightOffset(12f);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);


        chart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setTypeface(tfRegular);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.BLUE);
        xAxis.setTextSize(13f);

        //xAxis.setLabelCount(5);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        //xAxis.setXOffset(500f);
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setDrawAxisLine(false);
        xAxis.setXOffset(100);
        //xAxis.setAxisLineWidth(100);



        YAxis left = chart.getAxisLeft();
        left.setDrawLabels(false);
        left.setSpaceTop(25f);
        left.setSpaceBottom(12f);
        left.setDrawAxisLine(false);
        left.setDrawGridLines(false);
        left.setDrawZeroLine(true); // draw a zero line
        left.setZeroLineColor(Color.GRAY);
        left.setZeroLineWidth(0.7f);
        chart.getAxisRight().setEnabled(false);


        chart.getLegend().setEnabled(false);
        BarChartMarkerView mv = new BarChartMarkerView(this,xAxisFormatter);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv);

        // THIS IS THE ORIGINAL DATA YOU WANT TO PLOT

        dataList.add(new Data(0f, 224.1f, "1990"));
        dataList.add(new Data(1f, 238.5f, "1993"));
        dataList.add(new Data(2f, 1280.1f, "1994"));
        dataList.add(new Data(3, 442.3f, "1997"));
        dataList.add(new Data(4f, 442.3f, "1998"));
        dataList.add(new Data(5f, 300.3f, "1999"));
        dataList.add(new Data(6f, 300.3f, "2000"));
        dataList.add(new Data(7f, 500.3f, "1990"));
        dataList.add(new Data(8f, 2280.1f, "2019"));

        xAxis.setLabelCount(dataList.size());
        xAxis.setEnabled(false);


        xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                String st="";

               /* if( value== dataList.size()-1) {

                        st = dataList.get((int) value).xAxisValue;

                }else if(value<0)
                {
                    st= dataList.get(0).xAxisValue;
                }*/


                return st;
            }
        });

       /* xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return data.get(Math.min(Math.max((int) value, 0), data.size()-1)).xAxisValue;
            }
        });*/

        setDataList(dataList);
    }

    private void setDataList(List<Data> dataList) {

        ArrayList<BarEntry> values = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        int green = Color.rgb(110, 190, 102);
        int red = Color.rgb(211, 74, 88);

        for (int i = 0; i < dataList.size(); i++) {

            Data d = dataList.get(i);
            BarEntry entry = new BarEntry(d.xValue, d.yValue,d);
            values.add(entry);

            // specific colors
            if (d.yValue >= 0)

                colors.add(red);
            else
                colors.add(green);
        }

        BarDataSet set;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set = new BarDataSet(values, "Values");
            set.setDrawValues(false);
            set.setHighLightColor(red);
            set.setColors(colors);
            set.setValueTextColors(colors);
            set.setBarBorderWidth(2);
            set.setBarBorderColor(getColor(R.color.white));

            BarData data = new BarData(set);

            data.setValueTextSize(13f);

            data.setValueTypeface(tfRegular);
            data.setValueFormatter(new ValueFormatter());
            data.setBarWidth(0.8f);

            chart.setData(data);

            chart.invalidate();
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        BarDataSet set = (BarDataSet) chart.getData().getDataSetByIndex(0);
        List<BarEntry> entryList= set.getValues();
        Data data= (Data) e.getData();

        for (BarEntry barEntry : entryList) {

            barEntry.setIcon(null);
            Data entryData=((Data)barEntry.getData());
            if(!data.xAxisValue.equals(entryData.xAxisValue)) {

                entryData.isSelected = false;

            }
            barEntry.setIcon(null);

        }


        data.isSelected=true;
        e.setIcon(getResources().getDrawable(R.drawable.ic_city_tick));







        chart.invalidate();

       /* if (e == null)
            return;

        RectF bounds = onValueSelectedRectF;
        chart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = chart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + chart.getLowestVisibleX() + ", high: "
                        + chart.getHighestVisibleX());

        MPPointF.recycleInstance(position);*/
    }

    @Override
    public void onNothingSelected() {

        BarDataSet set = (BarDataSet) chart.getData().getDataSetByIndex(0);
        List<BarEntry> entryList= set.getValues();

        for (BarEntry barEntry : entryList) {

            barEntry.setIcon(null);
            Data entryData=((Data)barEntry.getData());

            entryData.isSelected = false;

            barEntry.setIcon(null);

        }


    }

    /**
     * Demo class representing data.
     */


    private class ValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {

        private final DecimalFormat mFormat;

        ValueFormatter() {
            mFormat = new DecimalFormat("######.0");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(value);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.only_github, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.viewGithub) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/BarChartPositiveNegative.java"));
            startActivity(i);
        }

        return true;
    }

    @Override
    public void saveToGallery() { /* Intentionally left empty */ }
}
