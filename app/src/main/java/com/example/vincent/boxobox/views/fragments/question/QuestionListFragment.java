package com.example.vincent.boxobox.views.fragments.question;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.api.BoxoboxService;
import com.example.vincent.boxobox.api.Connection;
import com.example.vincent.boxobox.model.Alarm;
import com.example.vincent.boxobox.model.Question;
import com.example.vincent.boxobox.views.adapter.AlarmRecyclerAdapter;
import com.example.vincent.boxobox.views.adapter.QuestionRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vincent.boxobox.views.LoginActivity.TOKEN_AUTH;

///PUT DIVIDER
public class QuestionListFragment extends Fragment {

    @BindView(R.id.question_list)
    RecyclerView recyclerView;

    QuestionListInterface questionListInterface;
    List<Question> questions;

    @OnClick(R.id.fab) void showCreationFragment(){
        questionListInterface.showCreationFragment();
    }



    public QuestionListFragment() {
        // Required empty public constructor
    }

    public static QuestionListFragment newInstance() {
        return new QuestionListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof QuestionListFragment.QuestionListInterface) {
            questionListInterface = (QuestionListFragment.QuestionListInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        questionListInterface = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_container, container, false);
        ButterKnife.bind(this,view);


        DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.my_divider));
        recyclerView.addItemDecoration(divider);

        getQuestion();

        return view;
    }

    private void getQuestion() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String token = sharedPref.getString(TOKEN_AUTH, "");
        BoxoboxService service = Connection.get(token);

        service.getQuestions().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                questions = response.body();
                recyclerView.setAdapter(new QuestionRecyclerAdapter(questions));
                recyclerView.getAdapter().notifyDataSetChanged();

                recyclerView.addOnItemTouchListener(new RecylerItemClickListener(getContext(),recyclerView,new RecylerItemClickListener.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view, int position) {
                        questionListInterface.showDetailFragment(questions.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        deleteQuestion();
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void deleteQuestion() {
        Log.d("DELETE QUESTION","NOT IMPLEMENTED");
    }

    public interface QuestionListInterface {
        void showCreationFragment();
        void showDetailFragment(Question question);
    }
}
