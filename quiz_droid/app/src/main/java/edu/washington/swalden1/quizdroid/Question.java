package edu.washington.swalden1.quizdroid;

/**
 * Created by masq on 2/15/17.
 */

public class Question {

    private String questionText;
    private String[] answers;
    private int answer;

    public Question(String t, String[] a, int c) {
        this.questionText = t; // text
        this.answers = a; // answers
        this.answer = c; // correct
    }

    public String getQuestionText() { return questionText; }
    public String[] getAnswers() { return answers; }
    public int getAnswer() { return answer; }
}
