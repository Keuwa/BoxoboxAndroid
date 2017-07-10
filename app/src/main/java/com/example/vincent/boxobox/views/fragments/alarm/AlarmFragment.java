package com.example.vincent.boxobox.views.fragments.alarm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.api.BoxoboxService;
import com.example.vincent.boxobox.api.Connection;
import com.example.vincent.boxobox.model.Alarm;
import com.example.vincent.boxobox.views.adapter.AlarmRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vincent.boxobox.views.LoginActivity.TOKEN_AUTH;

/**
 * Created by Vincent on 04/07/2017.
 */

public class AlarmFragment extends Fragment {


    @BindView(R.id.alarm_recycler_view)
    RecyclerView recycler;

    List<Alarm>alarms;


    public AlarmFragment() {
        // Required empty public constructor
    }


    public static AlarmFragment newInstance() {
        return new AlarmFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        ButterKnife.bind(this, view);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.my_divider));
        recycler.addItemDecoration(divider);

        loadAlarms();

        return view;
    }

    private void loadAlarms() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String token = sharedPref.getString(TOKEN_AUTH, "");
        BoxoboxService service = Connection.get(token);

        service.getAlarms().enqueue(new Callback<List<Alarm>>() {
            @Override
            public void onResponse(Call<List<Alarm>> call, Response<List<Alarm>> response) {
                //TODO add prgressbar
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                recycler.setAdapter(new AlarmRecyclerAdapter(response.body()));
                recycler.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Alarm>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
