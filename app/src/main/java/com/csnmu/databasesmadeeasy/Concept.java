package com.csnmu.databasesmadeeasy;

public class Concept {

    private String conceptTitle;
    private String conceptSummary;
    private Page[] conceptPages;

    public Concept(String title, String summary, Page[] pages){
        this.conceptTitle = title;
        this.conceptSummary = summary;
        this.conceptPages = pages;
    }

    public String getConceptTitle() {
        return conceptTitle;
    }

    public void setConceptTitle(String conceptTitle) {
        this.conceptTitle = conceptTitle;
    }

    public String getConceptSummary() {
        return conceptSummary;
    }

    public void setConceptSummary(String conceptSummary) {
        this.conceptSummary = conceptSummary;
    }

    public Page[] getConceptPages() {
        return conceptPages;
    }

    public void setConceptPages(Page[] conceptPages) {
        this.conceptPages = conceptPages;
    }
}