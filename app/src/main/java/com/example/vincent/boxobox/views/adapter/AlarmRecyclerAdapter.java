package com.example.vincent.boxobox.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.model.Alarm;
import com.example.vincent.boxobox.views.viewholder.AlarmViewHolder;
import com.example.vincent.boxobox.views.viewholder.SectionCardViewHolder;

import java.util.List;

/**
 * Created by Vincent on 03/07/2017.
 */

public class AlarmRecyclerAdapter extends RecyclerView.Adapter<AlarmViewHolder> {

    private List<Alarm> alarms;

    public AlarmRecyclerAdapter(List<Alarm> sections) {
        this.alarms = sections;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_view_holder,parent,false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        holder.bind(alarms.get(position));
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }
}
