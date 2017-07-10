package com.example.vincent.boxobox.views.fragments.question;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.api.BoxoboxService;
import com.example.vincent.boxobox.api.Connection;
import com.example.vincent.boxobox.model.Answer;
import com.example.vincent.boxobox.model.Question;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vincent.boxobox.views.LoginActivity.TOKEN_AUTH;



public class QuestionDetailsFragment extends Fragment {


    Question question;

    @BindView(R.id.bar_chart)
    BarChart barchart;

    @BindView(R.id.question_display)
    TextView textView;

    public QuestionDetailsFragment() {
        // Required empty public constructor
    }

    public static QuestionDetailsFragment newInstance(Question question) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("QUESTION",question);
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        question = getArguments().getParcelable("QUESTION");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_detail, container, false);
        ButterKnife.bind(this,view);
        textView.setText(question.getQuestion());

        getAnswerData();


        return view;
    }

    private void getAnswerData() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String token = sharedPref.getString(TOKEN_AUTH, "");
        BoxoboxService service = Connection.get(token);

        Map<String, String> data = new HashMap<>();
        data.put("question",question.get_id());

        service.getAnswerForQuestion(data).enqueue(new Callback<List<Answer>>() {
            @Override
            public void onResponse(Call<List<Answer>> call, Response<List<Answer>> response) {
                if(response.body()!=null){
                    int answerCount = response.body().size();
                    int leftCount = 0;
                    for (Answer answer:
                            response.body()) {
                        if(answer.getAnswer().equals(question.getAnswer_left())){
                            leftCount++;
                        }
                    }
                    setUpChart(answerCount,leftCount);
                }else{
                    retrevingDataFailed();
                }


            }

            @Override
            public void onFailure(Call<List<Answer>> call, Throwable t) {
                t.printStackTrace();
                retrevingDataFailed();
            }
        });
    }

    private void setUpChart(int answerCount, int leftCount) {

        barchart.getXAxis().setEnabled(false);
        barchart.getAxisLeft().setEnabled(false);
        barchart.getAxisRight().setEnabled(false);
        barchart.getDescription().setEnabled(false);


        List<BarEntry>entries = new ArrayList<>();


        entries.add(new BarEntry(2,leftCount));
        entries.add(new BarEntry(4,answerCount-leftCount));


        BarDataSet dataSetLeft = new BarDataSet(entries,question.getAnswer_left());
        dataSetLeft.setColor(Color.CYAN);
        dataSetLeft.setValueTextSize(15);
        dataSetLeft.setValueFormatter(new MyValueFormatter());
        BarDataSet dataSetRight = new BarDataSet(entries,question.getAnswer_right());
        dataSetRight.setColor(Color.RED);
        dataSetRight.setValueTextSize(15);
        dataSetRight.setValueFormatter(new MyValueFormatter());

        BarData data = new BarData(dataSetLeft,dataSetRight);
        data.setBarWidth(1.0f);
        barchart.setData(data);
        barchart.invalidate();
    }

    private class MyValueFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here
            return mFormat.format(value);
        }
    }

    private void retrevingDataFailed() {
        Log.e("Error Retrofit Answer","FAILED");
    }

}
