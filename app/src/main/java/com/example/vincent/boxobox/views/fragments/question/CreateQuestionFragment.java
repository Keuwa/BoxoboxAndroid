package com.example.vincent.boxobox.views.fragments.question;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.api.BoxoboxService;
import com.example.vincent.boxobox.api.Connection;
import com.example.vincent.boxobox.model.Question;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vincent.boxobox.views.LoginActivity.TOKEN_AUTH;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateQuestionFragment extends Fragment {

    CreateQuestionInterface createQuestionInterface;

    @BindView(R.id.create_question_text)
    EditText questionText;

    @BindView(R.id.left_answer_text)
    EditText leftAnswer;

    @BindView(R.id.right_answer_text)
    EditText rightAnswer;

    @OnClick(R.id.cancel_button) void cancel(){
        createQuestionInterface.questionCanceled();
    }

    @OnClick(R.id.create_question_button)void createQuestion(){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String token = sharedPref.getString(TOKEN_AUTH, "");
        BoxoboxService service = Connection.get(token);

        Question question = new Question(questionText.getText().toString(),null,leftAnswer.getText().toString(),rightAnswer.getText().toString(),new Date(),null);

        service.postQuestion(question).enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                createQuestionInterface.questionCreated();
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                t.printStackTrace();
                Context context = getContext();
                CharSequence text = "Retrofit FAILED";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }



    public CreateQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateQuestionFragment.CreateQuestionInterface) {
            createQuestionInterface = (CreateQuestionInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        createQuestionInterface = null;
    }

    // TODO: Rename and change types and number of parameters
    public static CreateQuestionFragment newInstance() {
        CreateQuestionFragment fragment = new CreateQuestionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_question, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


    public interface CreateQuestionInterface {
        void questionCreated();
        void questionCanceled();
    }
}
