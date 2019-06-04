package com.csnmu.databasesmadeeasy;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
    private String question;
    private List<String> answers;
    private int solution;
    private int imgResId = 0;

    public Quiz() {
    }

    public Quiz(String question, List<String> answers, int solution, int imgResId) {
        this.question = question;
        this.answers = answers;
        this.solution = solution;
        this.imgResId = imgResId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void setSolution(int solution) {
        this.solution = solution;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getSolution() {
        return solution;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
