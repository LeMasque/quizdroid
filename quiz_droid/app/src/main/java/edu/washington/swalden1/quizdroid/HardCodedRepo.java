package edu.washington.swalden1.quizdroid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by masq on 2/15/17.
 */

public class HardCodedRepo implements TopicRepository {

    private List<Topic> topics;

    public HardCodedRepo(Collection<Topic> seedTopics) {
        this.topics = new ArrayList<Topic>(seedTopics);
    }

    public HardCodedRepo() {

    }

    public Collection<Topic> getTopics() {
        return topics;
    }

    // Probably don't even need this
    public Topic createTopic(String title, String desc, String long_desc, Collection<Question> q) {
        Topic t = new Topic(title, desc, long_desc, q);
        this.topics.add(t);
        return t;
    }


    public Topic retrieveTopic(String title) {
        for (Topic t : this.topics) {
            if (t.getTitle() == title) {
                return t;
            }
        }
        return null;
    }

    // Stub method, hard coded doesn't need to implement this really
    public Topic updateTopic(String title) {
        return null;
    }

    // Stub method, hard coded doesn't need to implement this really
    public Topic deleteTopic(String title) {
        return null;
    }
}
