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
import android.widget.TextView;

public class AnswerFragment extends Fragment {

    private static final String ARG_TOPIC = "topic";

    private int topic;

    public AnswerFragment() {
        // Required empty public constructor
    }

    public static AnswerFragment newInstance(int param1) {
        AnswerFragment fragment = new AnswerFragment();
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
        View v = inflater.inflate(R.layout.fragment_answer, container, false);

        int response_number = Quiz.response;
        Topic topic = QuizApp.getInstance().getTopics()[this.topic];

        final int question_number = topic.getQuestion();
        Question q = topic.getQuestions().get(question_number);
        String question = q.getQuestionText();
        String answer = q.getAnswers()[q.getAnswer()];
        String response = q.getAnswers()[response_number];

        TextView aa = (TextView) v.findViewById(R.id.answer_answer);
        TextView an = (TextView) v.findViewById(R.id.answer_number);
        TextView aq = (TextView) v.findViewById(R.id.answer_question);
        TextView av = (TextView) v.findViewById(R.id.answer_verdict);
        TextView as = (TextView) v.findViewById(R.id.answer_status);
        TextView ar = (TextView) v.findViewById(R.id.answer_response);

        aa.setText("Correct Answer: " + answer);
        an.setText("Question "+(question_number+1));
        aq.setText(question);
        ar.setText("You answered: " + response);
        if (response_number == q.getAnswer()) {
            // CORRECT
            int rightColor = 0xFF33AA33;
            topic.nextQuestion(true);
            av.setText("CORRECT!");
            ar.setTextColor(rightColor);
            av.setTextColor(rightColor); // 0x33AA33
        } else {
            //WRONG
            int wrongColor = 0xFFAA3333;
            topic.nextQuestion(false);
            av.setText("WRONG!");
            ar.setTextColor(wrongColor);
            av.setTextColor(wrongColor); // 0xAA3333
        }
        as.setText("You've answered "+topic.getCorrect()+" out of "+topic.getQuestions().size()+" questions correct!");
        return v;
    }
}