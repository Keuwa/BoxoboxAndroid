package com.example.vincent.boxobox.views.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.model.Alarm;
import com.example.vincent.boxobox.model.Section;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vincent on 03/07/2017.
 */

public class AlarmViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.alarm_type)
    TextView type;

    @BindView(R.id.alarm_date_start)
    TextView dateStart;

    @BindView(R.id.alarm_date_end)
    TextView dateEnd;

    @BindView(R.id.user_name)
    TextView userName;

    public AlarmViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Alarm alarm){

        DateFormat format = new SimpleDateFormat("dd/MM/YY hh:mm", Locale.FRANCE);
        type.setText(alarm.getSensor());
        dateStart.setText(format.format(alarm.getStartDate()));
        dateEnd.setText(format.format(alarm.getEndDate()));
        userName.setText(alarm.getUser().getUsername());

    }
}
