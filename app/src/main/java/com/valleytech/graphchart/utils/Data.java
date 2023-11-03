package com.valleytech.graphchart.utils;

public class Data {

    public final String xAxisValue;
    public final float yValue;
    public final float xValue;
    public boolean isSelected=false;

    public Data(float xValue, float yValue, String xAxisValue) {
        this.xAxisValue = xAxisValue;
        this.yValue = yValue;
        this.xValue = xValue;
    }
}