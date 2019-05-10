package com.csnmu.databasesmadeeasy;

public class Page {

    private String pageTitle;
    private String pageText;
    private int image;

    public Page(String pageTitle, String pageText){
        this.pageTitle = pageTitle;
        this.pageText = pageText;
    }

    public Page(int img, String pageTitle, String pageText){
        this.pageTitle = pageTitle;
        this.pageText = pageText;
        this.image = img;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageText() {
        return pageText;
    }

    public void setPageText(String pageText) {
        this.pageText = pageText;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
