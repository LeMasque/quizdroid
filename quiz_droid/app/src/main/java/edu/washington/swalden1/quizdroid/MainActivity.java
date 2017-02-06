package edu.washington.swalden1.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // TODO: consider getters for these
    public final static String TAG = "QUIZDROID";
    public final static String TOPIC = "edu.washington.swalden1.quizdroid.TOPIC";
    public static final Map<String, Topic> TOPICS;
    static {
        Map<String, Topic> aMap = new HashMap<String, Topic>();
        aMap.put("physics",
                new Topic("physics", "the branch of science concerned with the nature " +
                    "and properties of matter and energy. The subject matter of physics, distinguished " +
                    "from that of chemistry and biology, includes mechanics, heat, light and other " +
                    "radiation, sound, electricity, magnetism, and the structure of atoms.",
                    new String[] {  // questions
                            "what is the airspeed velocity of an unlaiden swallow?"
                    }, new String[][] { // responses
                            {"a", "b", "c", "d"}
                    }, new int[] { // answers
                            1
                    }
                )
        );
        aMap.put("math",
                new Topic("math", "the abstract science of number, quantity, and space. Mathematics " +
                    "may be studied in its own right ( pure mathematics ), or as it is applied " +
                    "to other disciplines such as physics and engineering ( applied mathematics ).",
                    new String[] {
                            "what is 2 + 2?",
                            "what is 4 - 7?"
                    }, new String[][] {
                        {"a", "b", "c", "d"},
                        {"a", "b", "c", "d"}
                    }, new int[] {
                        1,
                        0
                    }
                )
        );
        aMap.put("marvel super heros",
                new Topic("marvel super heros", "benevolent fictional characters with superhuman " +
                    "powers, such as Superman, but created by Marvel Comics.",
                    new String[] {
                       "Who is Thor's nemesis?"
                    }, new String[][] {
                       {"a", "b", "c", "d"}
                    }, new int[] {
                       1
                    }
                )
        );
        TOPICS = Collections.unmodifiableMap(aMap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: loop over all the buttons on this main view DONE
        RelativeLayout main = (RelativeLayout) findViewById(R.id.activity_main);
        for (int i = 0; i < main.getChildCount(); i++) {
            main.getChildAt(i).setOnClickListener(new ButtonClick());
        }
    }


    private class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            String topic = b.getText().toString().toLowerCase();
            Intent intent = new Intent(MainActivity.this, Quiz.class);
            intent.putExtra(TOPIC, topic);
            startActivity(intent);
        }
    }

    public static class Topic {

        private String DESCRIPTION;
        private String TOPIC;
        private String[] QUESTIONS;
        private String[][] RESPONSES;
        private int[] ANSWERS;
        private int question;
        private int correct;

        // takes topic, description, questions, responses, and correct responses
        public Topic(String t, String d, String[] q, String[][] r, int[] a) {
            this.TOPIC = t;
            this.DESCRIPTION = d;
            this.QUESTIONS = q;
            this.RESPONSES = r;
            this.ANSWERS = a;

            this.question = 0;
            this.correct = 0;
        }

        public String getDescription() { return this.DESCRIPTION; }
        public String getTopic() { return this.TOPIC; }
        public String[] getQuestions() { return this.QUESTIONS; }
        public String[][] getResponses() { return this.RESPONSES; }
        public int[] getAnswers() { return this.ANSWERS; }
        public int getQuestion() { return this.question; }
        public int getCorrect() { return this.correct; }

        // increments which question you're on, as well as incrementing the correct counter if correct
        public int nextQuestion(boolean wasCorrect) {
            if (wasCorrect) this.correct++;
            return ++this.question;
        }

        // sets the correct questions and the current question to 0
        public void reset() {
            this.correct = 0;
            this.question = 0;
        }
    }
}

