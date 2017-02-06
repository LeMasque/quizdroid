package edu.washington.swalden1.quizdroid;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {

    private boolean isInQuestion = false;
    public static MainActivity.Topic topic;
    public static int response;
    public static Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.TOPIC);
        topic = MainActivity.TOPICS.get(message);

        button = (Button) findViewById(R.id.quiz_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle all fragment UI details in fragment, but change button text here
                Log.i(MainActivity.TAG, "question submit clicked");
                if (isInQuestion && response >= 0) { // goto answer or last answer
                    isInQuestion = false;
                    Log.d(MainActivity.TAG, topic.getQuestion() + " : " + topic.getQuestions().length);
                    if (topic.getQuestion() + 1 == topic.getQuestions().length) {
                        Log.i(MainActivity.TAG, "setting final text");
                        button.setText("Finish");
                    } else {
                        Log.i(MainActivity.TAG, "Moving to next Question");
                        button.setText("Next");
                    }
                    Fragment af = AnswerFragment.newInstance(message);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_placeholder, af);
                    ft.commit();
                } else { // goto question or finish
                    isInQuestion = true;
                    if (topic.getQuestion() == topic.getQuestions().length) {
                        Log.i(MainActivity.TAG, "Finish clicked!");
                        finish();
                    } else { // set the text for the question page and the change to answer fragment
                        Log.i(MainActivity.TAG, "Question response submitted");
                        response = -1;
                        button.setEnabled(false); // gets re-enabled when user makes choice of response
                        button.setText("Submit");
                        Fragment qf = QuestionFragment.newInstance(message);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_placeholder, qf);
                        ft.commit();
                    }
                }
            }
        });
        topic.reset();
        Fragment of = OverviewFragment.newInstance(message);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, of);
        ft.commit();
    }
}
