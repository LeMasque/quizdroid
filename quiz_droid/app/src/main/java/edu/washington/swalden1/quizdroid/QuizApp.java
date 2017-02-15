package edu.washington.swalden1.quizdroid;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by masq on 2/14/17.
 */

public class QuizApp extends Application {

    public static final String TAG = "QuizApp";

    public static QuizApp instance;
    private static TopicRepository tr;

    public QuizApp() {
        if (instance == null) {
            instance = this;
        } else {
            Log.e(TAG, "Instance already created");
        }
        tr = createHardCodedTopicRepository();
    }

    public Topic[] getTopics() {
        return tr.getTopics().toArray(new Topic[tr.getTopics().size()]);
    }

    public TopicRepository createHardCodedTopicRepository() {
        List<Topic> t = new ArrayList<Topic>(Arrays.asList(
                new Topic(
                        "physics",
                        "the branch of science concerned with the nature and properties of matter and energy.",
                        "the branch of science concerned with the nature " +
                                "and properties of matter and energy. The subject matter of physics, distinguished " +
                                "from that of chemistry and biology, includes mechanics, heat, light and other " +
                                "radiation, sound, electricity, magnetism, and the structure of atoms.",
                        Arrays.asList(
                                new Question(
                                        "what is the airspeed velocity of an unlaiden swallow?", // question text
                                        new String[] {"a", "b", "c", "d"}, // responses
                                        1 // index of responses of correct answer
                                )
                        )
                ),
                new Topic(
                        "math",
                        "the abstract science of number, quantity, and space.",
                        "the abstract science of number, quantity, and space. Mathematics " +
                        "may be studied in its own right ( pure mathematics ), or as it is applied " +
                        "to other disciplines such as physics and engineering ( applied mathematics ).",
                        Arrays.asList(
                                new Question(
                                        "What is 2 + 2?",
                                        new String[] { "a", "b", "c", "d" },
                                        3
                                ),
                                new Question(
                                        "What is 4 - 7?",
                                        new String[] { "a", "b", "c", "d" },
                                        0
                                )
                        )
                ),
                new Topic(
                        "marvel super heros",
                        "benevolent fictional characters with superhuman " +
                                "powers, such as Superman, but created by Marvel Comics.",
                        "benevolent fictional characters with superhuman " +
                                "powers, such as Superman, but created by Marvel Comics.",
                        Arrays.asList(
                                new Question(
                                        "Who is Thor's nemesis?",
                                        new String[] { "a", "b", "c", "d" },
                                        2
                                )
                        )
                )
        ));
        return new HardCodedRepo(t);
    }

    public TopicRepository getTopicRepository() {
        return this.tr;
    }

    public static QuizApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "[*] Called onCreate!");
    }
}
