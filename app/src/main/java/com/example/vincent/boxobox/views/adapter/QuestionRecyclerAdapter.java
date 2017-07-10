package com.example.vincent.boxobox.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.model.Alarm;
import com.example.vincent.boxobox.model.Question;
import com.example.vincent.boxobox.views.viewholder.AlarmViewHolder;
import com.example.vincent.boxobox.views.viewholder.QuestionViewHolder;

import java.util.List;

/**
 * Created by Vincent on 03/07/2017.
 */

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionViewHolder> {

    private List<Question> questions;

    public QuestionRecyclerAdapter(List<Question> sections) {
        this.questions = sections;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_view_holder,parent,false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        holder.bind(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
