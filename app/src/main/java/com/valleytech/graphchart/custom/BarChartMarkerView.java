
package com.valleytech.graphchart.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.card.MaterialCardView;
import com.valleytech.graphchart.BarChartPositiveActivity;
import com.valleytech.graphchart.R;
import com.valleytech.graphchart.utils.Data;

import java.text.DecimalFormat;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
@SuppressLint("ViewConstructor")
public class BarChartMarkerView extends MarkerView {

    private final TextView tvContent;
    private final ValueFormatter xAxisValueFormatter;

    private final DecimalFormat format;

    public BarChartMarkerView(Context context, ValueFormatter xAxisValueFormatter) {
        super(context, R.layout.custom_bar_chart_marker_view);
        this.xAxisValueFormatter = xAxisValueFormatter;
        tvContent = findViewById(R.id.tvContent);
        ConstraintLayout constraintLayout=findViewById(R.id.cl_data);
         int mWidth = this.getResources().getDisplayMetrics().widthPixels;
        constraintLayout.setLayoutParams(new MaterialCardView.LayoutParams(
                MaterialCardView.LayoutParams.MATCH_PARENT,MaterialCardView.LayoutParams.MATCH_PARENT));


        /*constraintLayout.setLayoutParams(new CardView.LayoutParams(
                mWidth,CardView.LayoutParams.MATCH_PARENT));*/

        format = new DecimalFormat("###.0");
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        Data data=  (Data) e.getData();

        tvContent.setText("X->"+data.xValue+":Y->"+data.yValue+":AxisValue->"+data.xAxisValue);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), getHeight()- Utils.convertDpToPixel(70));
    }
}
