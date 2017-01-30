package edu.washington.swalden1.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.TOPIC);
        TextView ot = (TextView) findViewById(R.id.overview_topic);
        TextView od = (TextView) findViewById(R.id.overview_description);
        Button begin = (Button) findViewById(R.id.overview_button);

        ot.setText(message);
        od.setText(MainActivity.TOPICS.get(message).getDescription());

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: start a quiz with topic's questions DONE?
                // TODO: Zero out score for that topic, and correct things DONE?
                Log.i(MainActivity.TAG, "begin_quiz");
                MainActivity.TOPICS.get(message).reset();
                Intent intent = new Intent(getApplicationContext(), Question.class);
                intent.putExtra(MainActivity.TOPIC, message);
                startActivity(intent);
            }
        });

    }
}
