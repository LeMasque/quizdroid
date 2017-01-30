package edu.washington.swalden1.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Answer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.TOPIC);
        final int response_number = intent.getIntExtra(Question.RESPONSE, -1);
        MainActivity.Topic topic = MainActivity.TOPICS.get(message);

        final int question_number = topic.getQuestion();
        String question = topic.getQuestions()[question_number];
        String answer = topic.getResponses()[question_number][topic.getAnswers()[question_number]];
        String response = topic.getResponses()[question_number][response_number];

        TextView aa = (TextView) findViewById(R.id.answer_answer);
        TextView an = (TextView) findViewById(R.id.answer_number);
        TextView aq = (TextView) findViewById(R.id.answer_question);
        TextView av = (TextView) findViewById(R.id.answer_verdict);
        TextView as = (TextView) findViewById(R.id.answer_status);
        TextView ar = (TextView) findViewById(R.id.answer_response);
        Button next = (Button) findViewById(R.id.answer_continue);


        aa.setText("Correct Answer: " + answer);
        an.setText("Question"+(question_number+1));
        aq.setText(question);
        ar.setText("You answered: " + response);
        if (response_number == topic.getAnswers()[question_number]) {
            int rightColor = 0xFF33AA33;
            topic.nextQuestion(true);
            av.setText("CORRECT!");
            ar.setTextColor(rightColor);
            av.setTextColor(rightColor); // 0x33AA33
            // TODO: correct
        } else {
            int wrongColor = 0xFFAA3333;
            topic.nextQuestion(false);
            av.setText("WRONG!");
            ar.setTextColor(wrongColor);
            av.setTextColor(wrongColor); // 0xAA3333
            // TODO: WRONG
        }
        as.setText("You've answered "+topic.getCorrect()+" out of "+topic.getQuestions().length+" questions correct!");
        if (question_number + 1 == topic.getQuestions().length) {
            next.setText("Finish");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: FIXME; this is bad, as i need to go "back" to the original main, rather than make a new main
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }
            });
            // FINISH to main screen?
        } else {
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(MainActivity.TAG, "next question in quiz");
                    Intent intent = new Intent(getApplicationContext(), Question.class);
                    intent.putExtra(MainActivity.TOPIC, message);
                    startActivity(intent);
                }
            });
        }
    }
}
