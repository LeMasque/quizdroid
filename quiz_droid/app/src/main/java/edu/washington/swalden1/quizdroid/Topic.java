package edu.washington.swalden1.quizdroid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by masq on 2/15/17.
 */

public class Topic {

    private List<Question> QUESTIONS;
    private String SHORT_DESCRIPTION;
    private String LONG_DESCRIPTION;
    private String TITLE;
    private int question;
    private int correct;

    // takes topic, description, questions, responses, and correct responses
    public Topic(String title, String desc, String long_desc, Collection<Question> q) {
        this.TITLE = title;
        this.SHORT_DESCRIPTION = desc;
        this.LONG_DESCRIPTION = long_desc;
        this.QUESTIONS = new ArrayList<>(q);
        this.question = 0;
        this.correct = 0;
    }

    public String getDescription() { return this.SHORT_DESCRIPTION; }
    public String getLongDescription() { return this.LONG_DESCRIPTION; }
    public String getTitle() { return this.TITLE; }
    public List<Question> getQuestions() { return this.QUESTIONS; }
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

    @Override
    public String toString() {
        return this.getTitle() + "\r\n" + this.getDescription();
    }
}
