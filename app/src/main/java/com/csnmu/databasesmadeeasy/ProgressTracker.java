package com.csnmu.databasesmadeeasy;

import java.util.HashMap;

public class ProgressTracker {

    private String userID;

    private HashMap<Concept, Boolean> ConceptDone = new HashMap<Concept, Boolean>();
    private HashMap<Concept, Boolean> SummaryDone = new HashMap<Concept, Boolean>();
    private HashMap<Concept, Boolean> VideoDone = new HashMap<Concept, Boolean>();
    private HashMap<Concept, Boolean> QuizDone = new HashMap<Concept, Boolean>();

    public HashMap<Concept, Boolean> getConceptDone() {
        return ConceptDone;
    }

    public void setConceptDone(HashMap<Concept, Boolean> conceptDone) {
        ConceptDone = conceptDone;
    }

    public HashMap<Concept, Boolean> getSummaryDone() {
        return SummaryDone;
    }

    public void setSummaryDone(HashMap<Concept, Boolean> summaryDone) {
        SummaryDone = summaryDone;
    }

    public HashMap<Concept, Boolean> getVideoDone() {
        return VideoDone;
    }

    public void setVideoDone(HashMap<Concept, Boolean> videoDone) {
        VideoDone = videoDone;
    }

    public HashMap<Concept, Boolean> getQuizDone() {
        return QuizDone;
    }

    public void setQuizDone(HashMap<Concept, Boolean> quizDone) {
        QuizDone = quizDone;
    }
}
