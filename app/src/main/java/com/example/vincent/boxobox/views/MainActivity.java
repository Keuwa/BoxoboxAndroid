package com.example.vincent.boxobox.views;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.api.SocketInstance;
import com.example.vincent.boxobox.model.Question;
import com.example.vincent.boxobox.views.fragments.alarm.AlarmFragment;
import com.example.vincent.boxobox.views.fragments.question.CreateQuestionFragment;
import com.example.vincent.boxobox.views.fragments.question.QuestionContainerFragment;
import com.example.vincent.boxobox.views.fragments.settings.MessageContainerFragment;
import com.example.vincent.boxobox.views.fragments.monitor.MonitorContainerFragment;
import com.example.vincent.boxobox.views.fragments.monitor.MonitorSectionFragment;
import com.example.vincent.boxobox.views.fragments.question.QuestionDetailsFragment;
import com.example.vincent.boxobox.views.fragments.question.QuestionListFragment;
import com.example.vincent.boxobox.views.viewholder.SectionCardViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity implements SectionCardViewHolder.SectionButtonClickInterface,
        CreateQuestionFragment.CreateQuestionInterface,QuestionListFragment.QuestionListInterface {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private boolean isConnected = false;
    private static int NOTIFICATION_ID_CONNECTION = 5;


    @BindView(R.id.container) ViewPager mViewPager;
    @BindView(R.id.tab_layout)TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SocketInstance.get().connect();

        SocketInstance.get().on(Socket.EVENT_CONNECT, onConnect);

        SocketInstance.get().on("boxobox-online",onBoxoboxOnline);
        SocketInstance.get().on("boxobox-offline",onBoxoboxOffline);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabLayout.setupWithViewPager(mViewPager);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);

    }

    private Emitter.Listener onBoxoboxOnline = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Boxobox online")
                                    .setContentText("Votre Boxobox est connectée");
                    mBuilder.setVibrate(new long[] { 500, 500 });
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                    mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                    NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID_CONNECTION, mBuilder.build());
                }
            });
        }
    };

    private Emitter.Listener onBoxoboxOffline = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Boxobox offline")
                                    .setContentText("Votre Boxobox est déconnecté");
                    mBuilder.setVibrate(new long[] { 500, 500 });
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                    mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                    NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID_CONNECTION, mBuilder.build());
                }
            });
        }
    };

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"SocketIO conected !!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
    }


    //Interface for handling click on button in SectionRecyclerViews
    @Override
    public void onButtonClicked(List<String> types) {
        if(types.get(0).equals("MONITOR")){
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_monitor_fragment, MonitorSectionFragment.newInstance(types.get(1)),"MonitorFragment");
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void questionCreated() {
        SocketInstance.get().emit("question-create");
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void questionCanceled() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showCreationFragment() {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_question_fragment, CreateQuestionFragment.newInstance(),"CreationFragment");
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void showDetailFragment(Question question) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_question_fragment, QuestionDetailsFragment.newInstance(question),"DetailQuestionFragment");
        ft.addToBackStack(null);
        ft.commit();
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return MonitorContainerFragment.newInstance();
                case 1:
                    return QuestionContainerFragment.newInstance();
                case 2:
                    return AlarmFragment.newInstance();
                case 3:
                    return MessageContainerFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Surveillance";
                case 1:
                    return "Question";
                case 2:
                    return "Alarme";
                case 3:
                    return "Parametres";
            }
            return null;
        }
    }
}
