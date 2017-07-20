package com.example.vincent.boxobox.views.fragments.settings;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.api.BoxoboxService;
import com.example.vincent.boxobox.api.Connection;
import com.example.vincent.boxobox.api.SocketInstance;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.socket.emitter.Emitter;

import static com.example.vincent.boxobox.views.LoginActivity.TOKEN_AUTH;

public class MessageListFragment extends Fragment {

    Activity activity;

    public static final int NOTIFICATION_ID_NOISE = 1;
    public static final int NOTIFICATION_ID_LUMINOSITY = 2;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            activity=(Activity) context;
        }
    }

    @OnClick(R.id.button_alarm_noise) void activateAlarmNoise(){
            SocketInstance.get().emit("activate-alarm-noise");
    }

    @OnClick(R.id.button_alarm_light) void activateAlarmLight(){
        SocketInstance.get().emit("activate-alarm-luminosity");
    }

    @OnClick(R.id.button_alarm_noise_desactivate) void desactivateAlarmNoise(){
        SocketInstance.get().emit("desactivate-alarm-noise");
    }

    @OnClick(R.id.button_alarm_light_desactivate) void desactivateAlarmLight(){
        SocketInstance.get().emit("desactivate-alarm-luminosity");
    }

    @BindView(R.id.interval_update_luminosity)EditText updateIntervalLum;

    @OnClick(R.id.interval_update_luminosity_button)void updateIntervalLum(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("interval", updateIntervalLum.getText().toString());
            SocketInstance.get().emit("set-interval-luminosity",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @BindView(R.id.interval_update_temperature)EditText updateIntervalTemperature;

    @OnClick(R.id.interval_update_temperature_button)void updateIntervalTemperature(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("interval", updateIntervalTemperature.getText().toString());
            SocketInstance.get().emit("set-interval-temperature",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @BindView(R.id.interval_update_humidity)EditText updateIntervalHumidity;

    @OnClick(R.id.interval_update_humidity_button)void updateIntervalHumidity(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("interval", updateIntervalHumidity.getText().toString());
            SocketInstance.get().emit("set-interval-humidity",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public MessageListFragment() {
        // Required empty public constructor
    }


    public static MessageListFragment newInstance() {
        return new MessageListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        ButterKnife.bind(this,view);

        SocketInstance.get().on("alarm-noise-activate",onAlarmNoiseActivated);
        SocketInstance.get().on("alarm-luminosity-activate",onAlarmLuminosityActivated);
        SocketInstance.get().on("alarm-noise-desactivate",onAlarmNoiseDesactivated);
        SocketInstance.get().on("alarm-luminosity-desactivate",onAlarmLuminosityDesactivated);

        SocketInstance.get().on("alarm-noise-trigger",onAlarmNoiseTriggered);
        SocketInstance.get().on("alarm-luminosity-trigger",onAlarmLuminosityTriggered);


        return view;

    }

    private Emitter.Listener onAlarmLuminosityTriggered = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(activity)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Boxobox alarme déclenchée")
                                    .setContentText("L'alarme luminosité à été déclenchée");
                    mBuilder.setVibrate(new long[] { 500, 500 });
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                    mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                    NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID_LUMINOSITY, mBuilder.build());
                }
            });
        }
    };
    private Emitter.Listener onAlarmNoiseTriggered = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(activity)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Boxobox alarme déclenchée")
                                    .setContentText("L'alarme bruit à été déclenchée");
                    mBuilder.setVibrate(new long[] { 500, 500 });
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                    mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                    NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID_NOISE, mBuilder.build());
                }
            });
        }
    };

    private Emitter.Listener onAlarmNoiseActivated = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(activity)
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("Boxobox alarme")
                                        .setContentText("Alarm bruit activée");
                        mBuilder.setVibrate(new long[] { 500, 500 });
                        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                        mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(NOTIFICATION_ID_NOISE, mBuilder.build());
                }
            });
        }
    };

    private Emitter.Listener onAlarmLuminosityActivated = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(activity)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Boxobox alarm")
                                    .setContentText("Alarme luminosité activée");
                    mBuilder.setVibrate(new long[] { 500, 500 });
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                    mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                    NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID_LUMINOSITY, mBuilder.build());
                }
            });
        }
    };

    private Emitter.Listener onAlarmNoiseDesactivated = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(activity)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Boxobox alarme")
                                    .setContentText("Alarme bruit désactivé");
                    mBuilder.setVibrate(new long[] { 500, 500 });
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                    mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                    NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID_NOISE, mBuilder.build());
                }
            });
        }
    };

    private Emitter.Listener onAlarmLuminosityDesactivated = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(activity)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Boxobox alarme")
                                    .setContentText("Alarme luminosité désactivé");
                    mBuilder.setVibrate(new long[] { 500, 500 });
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                    mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                    NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID_LUMINOSITY, mBuilder.build());
                }
            });
        }
    };


}
