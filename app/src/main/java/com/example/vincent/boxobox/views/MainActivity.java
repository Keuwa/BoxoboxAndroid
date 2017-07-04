package com.example.vincent.boxobox.views;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
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
import android.view.View;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.views.fragments.Game.GameContainerFragment;
import com.example.vincent.boxobox.views.fragments.Game.GameFragment;
import com.example.vincent.boxobox.views.fragments.Message.MessageContainerFragment;
import com.example.vincent.boxobox.views.fragments.home.HomeContainerFragment;
import com.example.vincent.boxobox.views.fragments.monitor.MonitorContainerFragment;
import com.example.vincent.boxobox.views.fragments.monitor.MonitorSectionFragment;
import com.example.vincent.boxobox.views.viewholder.SectionCardViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SectionCardViewHolder.SectionButtonClickInterface {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.container) ViewPager mViewPager;
    @BindView(R.id.tab_layout)TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabLayout.setupWithViewPager(mViewPager);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Interface for handling click on button in SectionRecyclerViews
    @Override
    public void onButtonClicked(String title) {


        if(title.equals("Piano")){//TODO Better switch
            Log.d(this.getClass().toString(),"Piano");
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_game_fragment, GameFragment.newInstance(),"testFragment");
            ft.addToBackStack(null);
            ft.commit();
        }
        if(title.equals("Monitor")){
            Log.d(this.getClass().toString(),"Monitor");
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_monitor_fragment, MonitorSectionFragment.newInstance(),"testFragment");
            ft.addToBackStack(null);
            ft.commit();
        }


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
                    return HomeContainerFragment.newInstance();
                case 1:
                    return GameContainerFragment.newInstance();
                case 2:
                    return MonitorContainerFragment.newInstance();
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
                    return "Home";
                case 1:
                    return "Jeux";
                case 2:
                    return "Surveillance";
                case 3:
                    return "Messages";
            }
            return null;
        }
    }
}
