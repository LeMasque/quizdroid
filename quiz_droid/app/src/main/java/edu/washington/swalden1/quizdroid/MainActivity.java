package edu.washington.swalden1.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // TODO: consider getters for these
    public final static String TAG = "QUIZDROID";
    public final static String TOPIC = "edu.washington.swalden1.quizdroid.TOPIC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView)findViewById(R.id.main_listview);
        ArrayAdapter<Topic> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, QuizApp.getInstance().getTopics());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Quiz.class);
                intent.putExtra(TOPIC, position);
                startActivity(intent);
            }
        });
    }
}

