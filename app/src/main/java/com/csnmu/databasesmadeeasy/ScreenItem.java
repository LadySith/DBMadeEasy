package com.csnmu.databasesmadeeasy;

public class ScreenItem {

    String screenTitle;
    String screenDescription;
    int screenImg;

    public ScreenItem(String title, String description, int img){
        screenTitle = title;
        screenDescription = description;
        screenImg = img;
    }

    public String getScreenTitle() {
        return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public String getScreenDescription() {
        return screenDescription;
    }

    public void setScreenDescription(String screenDescription) {
        this.screenDescription = screenDescription;
    }

    public int getScreenImg() {
        return screenImg;
    }

    public void setScreenImg(int screenImg) {
        this.screenImg = screenImg;
    }
}
