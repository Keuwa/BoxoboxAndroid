package com.example.vincent.boxobox.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Vincent on 07/07/2017.
 */


public class MyXAxisValueFormatter implements IAxisValueFormatter {


    public MyXAxisValueFormatter() {

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        value -= 240;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,(int)value);
        DateFormat format = new SimpleDateFormat("dd/MM/YYYY hh:mm");

        return format.format(calendar.getTime());
    }
}
