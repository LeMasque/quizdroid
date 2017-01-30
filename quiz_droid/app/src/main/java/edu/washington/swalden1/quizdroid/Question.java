package edu.washington.swalden1.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Question extends AppCompatActivity {

    Button submit = null;
    public static final String RESPONSE = "edu.washinton.swalden1.quizdroid.RESPONSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.TOPIC);
        MainActivity.Topic topic = MainActivity.TOPICS.get(message);

        int question_number = topic.getQuestion();
        String question = topic.getQuestions()[question_number];
        String[] responses = topic.getResponses()[question_number];

        TextView question_header = (TextView) findViewById(R.id.question_number);
        question_header.setText("Question "+(question_number+1));
        TextView question_question = (TextView) findViewById(R.id.question_question);
        question_question.setText(question);
        RadioGroup rg = (RadioGroup) findViewById(R.id.question_responses);
        for (int i = 0; i < responses.length; i++) {
            String response = responses[i];
            final int index = i;
            RadioButton rb = new RadioButton(this);
            rb.setText(response);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submit = new Button(getApplicationContext());
                    submit.setText("Submit");
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO: launch into answer activity
                            Intent intent = new Intent(getApplicationContext(), Answer.class);
                            intent.putExtra(MainActivity.TOPIC, message);
                            intent.putExtra(RESPONSE, index);
                            startActivity(intent);
                            Log.i(MainActivity.TAG, "question submit clicked");
                        }
                    });
                    // style for button
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    params.rightMargin = 30;
                    params.bottomMargin = 30;

                    // add to layout
                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_question);
                    rl.addView(submit, params);
                    Log.i(MainActivity.TAG, "question response clicked");
                }
            });
            rg.addView(rb);
        }
    }
}
