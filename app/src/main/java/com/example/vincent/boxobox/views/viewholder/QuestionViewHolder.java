package com.example.vincent.boxobox.views.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.model.Alarm;
import com.example.vincent.boxobox.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vincent on 03/07/2017.
 */

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.question)
    TextView textQuestion;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.question_date)
    TextView questionDate;


    public QuestionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Question question){
        textQuestion.setText(question.getQuestion());
        questionDate.setText(question.getDate().toString());
    }
}
