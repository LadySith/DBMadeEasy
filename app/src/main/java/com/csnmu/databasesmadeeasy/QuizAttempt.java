package com.csnmu.databasesmadeeasy;

public class QuizAttempt {

    private String topic;
    private int score;
    private int total;

    public QuizAttempt(){}

    public QuizAttempt(String topic, int score, int total) {
        this.topic = topic;
        this.score = score;
        this.total = total;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
