package com.example.vincent.boxobox.views;

import android.app.Activity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.widget.Button;


import com.example.vincent.boxobox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends Activity {
    @BindView(R.id.button_alarm_noise) Button activateAlarmNoise;

    @BindView(R.id.button_alarm_light) Button activateAlarmLight;

    @BindView(R.id.button_alarm_noise_desactivate) Button desactivateAlarmNoise;

    @BindView(R.id.button_alarm_light_desactivate) Button desactivateAlarmLight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }
}
