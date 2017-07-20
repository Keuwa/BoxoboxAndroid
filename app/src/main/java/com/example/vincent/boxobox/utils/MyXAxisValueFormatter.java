package com.example.vincent.boxobox.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vincent on 07/07/2017.
 */


public class MyXAxisValueFormatter implements IAxisValueFormatter {


    public MyXAxisValueFormatter() {

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        value -= 240 * 60;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,(int)value);
        DateFormat format = new SimpleDateFormat("dd/MM/YY hh");

        return format.format(calendar.getTime());
    }
}
