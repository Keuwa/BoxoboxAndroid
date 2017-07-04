package com.example.vincent.boxobox.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.model.Section;
import com.example.vincent.boxobox.views.viewholder.SectionCardViewHolder;

import java.util.List;

/**
 * Created by Vincent on 03/07/2017.
 */

public class SectionRecyclerAdapter extends RecyclerView.Adapter<SectionCardViewHolder> {

    private List<Section> sections;

    public SectionRecyclerAdapter(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public SectionCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_card_view_holder,parent,false);
        return new SectionCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionCardViewHolder holder, int position) {
        holder.bind(sections.get(position));
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }
}
