package com.example.vincent.boxobox.views.fragments.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.boxobox.R;

/**
 * Created by Vincent on 03/07/2017.
 */

public class HomeContainerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_container_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment homeFragment =  HomeListFragment.newInstance();
        FragmentTransaction fm = this.getActivity().getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.container_home_fragment, homeFragment);
        fm.commit();
    }

    public static HomeContainerFragment newInstance() {
        return new HomeContainerFragment();
    }
}
