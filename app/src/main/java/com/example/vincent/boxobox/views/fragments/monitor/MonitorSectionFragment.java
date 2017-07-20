package com.example.vincent.boxobox.views.fragments.monitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.api.BoxoboxService;
import com.example.vincent.boxobox.api.Connection;
import com.example.vincent.boxobox.model.Luminosity;
import com.example.vincent.boxobox.model.Humidity;
import com.example.vincent.boxobox.model.Record;
import com.example.vincent.boxobox.model.Temperature;
import com.example.vincent.boxobox.utils.MyXAxisValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vincent.boxobox.views.LoginActivity.TOKEN_AUTH;

public class MonitorSectionFragment extends Fragment {

    static String RECORD_TYPE = "RECORD_TYPE";
    public static String LUMINOSITY = "LUMINOSITY";
    public static String TEMPERATURE = "TEMPERATURE";
    public static String HUMIDITY = "HUMIDITY";

    List<Record> records;
    String recordsType;

    @BindView(R.id.chart)LineChart chart;
    @OnClick(R.id.refresh_monitor_button)void refreshData(){
        initGraph();
    }

    public MonitorSectionFragment() {
        // Required empty public constructor
    }

    public static MonitorSectionFragment newInstance(String recordsType) {
        Bundle args = new Bundle();

        args.putString(RECORD_TYPE, recordsType);

        MonitorSectionFragment fragment = new MonitorSectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordsType = getArguments().getString(RECORD_TYPE);
        records = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monitor, container, false);
        ButterKnife.bind(this,view);

        initGraph();




        return view;
    }

    private void initGraph() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String token = sharedPref.getString(TOKEN_AUTH, "");
        BoxoboxService service = Connection.get(token);

        if(recordsType.equals(LUMINOSITY)){
            service.getLuminosities().enqueue(new Callback<List<Luminosity>>() {
                @Override
                public void onResponse(Call<List<Luminosity>> call, Response<List<Luminosity>> response) {
                    if(response.body()!=null){
                        for (Luminosity lum : response.body()) {
                            records.add(lum);
                        }
                        setData();
                    }
                    else{
                        Log.e("ERROR Retrofit","Loading luminosity");
                    }
                }

                @Override
                public void onFailure(Call<List<Luminosity>> call, Throwable t) {
                    t.printStackTrace();
                    Log.e("ERROR Retrofit","Loading luminosity");
                }
            });
        }
        else if(recordsType.equals(TEMPERATURE)){
            service.getTemperatures().enqueue(new Callback<List<Temperature>>() {
                @Override
                public void onResponse(Call<List<Temperature>> call, Response<List<Temperature>> response) {
                    if(response.body()!=null){
                        for (Temperature temp : response.body()) {
                            records.add(temp);
                        }
                        setData();
                    }
                    else{
                        Log.e("ERROR Retrofit","Loading luminosity");
                    }
                }

                @Override
                public void onFailure(Call<List<Temperature>> call, Throwable t) {
                    t.printStackTrace();
                    Log.e("ERROR Retrofit","Loading luminosity");
                }
            });
        }
        else if(recordsType.equals(HUMIDITY)){
            service.getHumidities().enqueue(new Callback<List<Humidity>>() {
                @Override
                public void onResponse(Call<List<Humidity>> call, Response<List<Humidity>> response) {
                    if(response.body()!=null){
                        for (Humidity humidity : response.body()) {
                            records.add(humidity);
                        }
                        setData();
                    }
                    else{
                        Log.e("ERROR Retrofit","Loading luminosity");
                    }
                }

                @Override
                public void onFailure(Call<List<Humidity>> call, Throwable t) {
                    t.printStackTrace();
                    Log.e("ERROR Retrofit","Loading luminosity");
                }
            });
        }


    }

    private void setData(){

        Calendar calendar = Calendar.getInstance();

        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.DATE,-10);

        long diff = now - calendar.getTimeInMillis();
        float minutes = (int) (diff / (1000 * 60 * 60 * 60));


        XAxis xAxis = chart.getXAxis();
        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record record, Record t1) {
                return record.getDate().compareTo(t1.getDate());
            }
        });

        LineDataSet dataSet = new LineDataSet(convertData(), "Label");
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        xAxis.setLabelRotationAngle(-30);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setAxisMinimum(238 * 60);
        //xAxis.setAxisMaximum(242 * 60);
        xAxis.setValueFormatter(new MyXAxisValueFormatter());
        xAxis.setGranularity(1f);

        //chart.setTouchEnabled(false);
        chart.invalidate();
    }

    private List<Entry> convertData(){
        List<Entry> entries = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-10);


        for (Record data : records) {
            long diff = calendar.getTimeInMillis() - data.getDate().getTime() ;
            //float m = data.getDate().getTime();

            float minutes = (int) (diff / (60000));
            // turn your data into Entry objects
            //entries.add(new Entry(-minutes, (long)data.getValue()));
            entries.add(new Entry(-minutes, (long)data.getValue()));
        }
        return entries;
    }

}
