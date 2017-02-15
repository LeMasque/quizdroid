package edu.washington.swalden1.quizdroid;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TOPIC = "topic";

    private int topic;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(int param1) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TOPIC, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.topic = getArguments().getInt(ARG_TOPIC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        Topic topic = Quiz.topic;
        int question_number = topic.getQuestion();
        Question q = topic.getQuestions().get(question_number);
        String question = q.getQuestionText();
        String[] responses = q.getAnswers();

        TextView question_header = (TextView) v.findViewById(R.id.question_number);
        question_header.setText("Question "+(question_number+1));
        TextView question_question = (TextView) v.findViewById(R.id.question_question);
        question_question.setText(question);
        RadioGroup rg = (RadioGroup) v.findViewById(R.id.question_responses);
        for (int i = 0; i < responses.length; i++) {
            String response = responses[i];
            final int index = i;
            RadioButton rb = new RadioButton(getActivity());
            rb.setText(response);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Quiz.response = index;
                    Quiz.button.setEnabled(true);
                    Log.i(MainActivity.TAG, "question response clicked");
                }
            });
            rg.addView(rb);
        }
        return v;
    }
}