package edu.washington.swalden1.quizdroid;

import java.util.Collection;

/**
 * Created by masq on 2/14/17.
 */

public interface TopicRepository {

    public Topic createTopic(String title, String desc, String long_desc, Collection<Question> q);
    public Topic retrieveTopic(String topicName);
    public Topic updateTopic(String topicName); // TODO
    public Topic deleteTopic(String topicName);

    public Collection<Topic> getTopics();
}
