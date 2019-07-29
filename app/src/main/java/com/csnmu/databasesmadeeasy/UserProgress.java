package com.csnmu.databasesmadeeasy;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProgress implements Parcelable {

    // Topic 1 - Introduction to Databases and DBMS
    // Topic 2 - The Relational Database Model
    // Topic 3 - Database Optimization
    // Topic 4 - Database Normalisation
    // Topic 5 - Simple and Advanced Queries

    private String userName;

    private Map<String, Boolean> Topic1 = new HashMap<String,Boolean>();
    private Map<String, Boolean> Topic2 = new HashMap<String,Boolean>();
    private Map<String, Boolean> Topic3 = new HashMap<String,Boolean>();
    private Map<String, Boolean> Topic4 = new HashMap<String,Boolean>();
    private Map<String, Boolean> Topic5 = new HashMap<String,Boolean>();

    public UserProgress(){}

    public UserProgress(String name){
        this.userName = name;

        this.Topic1.put("allComplete", false);
        this.Topic1.put("summaryComplete", false);
        this.Topic1.put("videoComplete", false);
        this.Topic1.put("quizComplete", false);

        this.Topic2.put("allComplete", true);
        this.Topic2.put("summaryComplete", true);
        this.Topic2.put("videoComplete", true);
        this.Topic2.put("quizComplete", true);

        this.Topic3.put("allComplete", false);
        this.Topic3.put("summaryComplete", false);
        this.Topic3.put("videoComplete", false);
        this.Topic3.put("quizComplete", false);

        this.Topic4.put("allComplete", false);
        this.Topic4.put("summaryComplete", true);
        this.Topic4.put("videoComplete", false);
        this.Topic4.put("quizComplete", true);

        this.Topic5.put("allComplete", false);
        this.Topic5.put("summaryComplete", false);
        this.Topic5.put("videoComplete", false);
        this.Topic5.put("quizComplete", false);
    }

    protected UserProgress(Parcel in) {
        userName = in.readString();
    }

    public static final Creator<UserProgress> CREATOR = new Creator<UserProgress>() {
        @Override
        public UserProgress createFromParcel(Parcel in) {
            return new UserProgress(in);
        }

        @Override
        public UserProgress[] newArray(int size) {
            return new UserProgress[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void seenTopic1summary(){
        Topic1.put("summaryComplete", true);
    }

    public void seenTopic2summary(){
        Topic2.put("summaryComplete", true);
    }

    public void seenTopic3summary(){
        Topic3.put("summaryComplete", true);
    }

    public void seenTopic4summary(){
        Topic4.put("summaryComplete", true);
    }

    public void seenTopic5summary(){
        Topic5.put("summaryComplete", true);
    }


    public void seenTopic1video(){
        Topic1.put("videoComplete", true);
    }

    public void seenTopic2video(){
        Topic2.put("videoComplete", true);
    }

    public void seenTopic3video(){
        Topic3.put("videoComplete", true);
    }

    public void seenTopic4video(){
        Topic4.put("videoComplete", true);
    }

    public void seenTopic5video(){
        Topic5.put("videoComplete", true);
    }


    public void seenTopic1quiz(){
        Topic1.put("quizComplete", true);
    }

    public void seenTopic2quiz(){
        Topic2.put("quizComplete", true);
    }

    public void seenTopic3quiz(){
        Topic3.put("quizComplete", true);
    }

    public void seenTopic4quiz(){
        Topic4.put("quizComplete", true);
    }

    public void seenTopic5quiz(){
        Topic5.put("quizComplete", true);
    }

    public boolean isTopic1done() {

        if (Topic1.get("summaryComplete") && Topic1.get("videoComplete") && Topic1.get("quizComplete")) {
            Topic1.put("allComplete", true);
            return true;
        } else {
            return false;
        }
    }

    public boolean isTopic2done() {

        if (Topic2.get("summaryComplete") && Topic2.get("videoComplete") && Topic2.get("quizComplete")) {
            Topic2.put("allComplete", true);
            return true;
        } else {
            return false;
        }
    }

    public boolean isTopic3done() {

        if (Topic3.get("summaryComplete") && Topic3.get("videoComplete") && Topic3.get("quizComplete")) {
            Topic3.put("allComplete", true);
            return true;
        } else {
            return false;
        }
    }

    public boolean isTopic4done() {

        if (Topic4.get("summaryComplete") && Topic4.get("videoComplete") && Topic4.get("quizComplete")) {
            Topic4.put("allComplete", true);
            return true;
        } else {
            return false;
        }
    }

    public boolean isTopic5done() {

        if (Topic5.get("summaryComplete") && Topic5.get("videoComplete") && Topic5.get("quizComplete")) {
            Topic5.put("allComplete", true);
            return true;
        } else {
            return false;
        }
    }

    // default getters and setters
    public Map<String, Boolean> getTopic1() {
        return Topic1;
    }

    public void setTopic1(Map<String, Boolean> topic1) {
        Topic1 = topic1;
    }

    public Map<String, Boolean> getTopic2() {
        return Topic2;
    }

    public void setTopic2(Map<String, Boolean> topic2) {
        Topic2 = topic2;
    }

    public Map<String, Boolean> getTopic3() {
        return Topic3;
    }

    public void setTopic3(Map<String, Boolean> topic3) {
        Topic3 = topic3;
    }

    public Map<String, Boolean> getTopic4() {
        return Topic4;
    }

    public void setTopic4(Map<String, Boolean> topic4) {
        Topic4 = topic4;
    }

    public Map<String, Boolean> getTopic5() {
        return Topic5;
    }

    public void setTopic5(Map<String, Boolean> topic5) {
        Topic5 = topic5;
    }

    //getting individual elements

    public boolean getQuizCompleteTopic(int i){

        if (i == 1) {
            return Topic1.get("quizComplete");
        }

        if (i == 2) {
            return Topic2.get("quizComplete");
        }

        if (i == 3) {
            return Topic3.get("quizComplete");
        }

        if (i == 4) {
            return Topic4.get("quizComplete");
        }

        if (i == 5) {
            return Topic5.get("quizComplete");
        }

        else return false;

    }

    public boolean getSummaryCompleteTopic(int i) {

        if (i == 1) {
            return Topic1.get("summaryComplete");
        }

        if (i == 2) {
            return Topic2.get("summaryComplete");
        }

        if (i == 3) {
            return Topic3.get("summaryComplete");
        }

        if (i == 4) {
            return Topic4.get("summaryComplete");
        }

        if (i == 5) {
            return Topic5.get("summaryComplete");
        }

        else return true;

    }

    public boolean getVideoCompleteTopic(int i) {

        if (i == 1) {
            return Topic1.get("videoComplete");
        }

        if (i == 2) {
            return Topic2.get("videoComplete");
        }

        if (i == 3) {
            return Topic3.get("videoComplete");
        }

        if (i == 4) {
            return Topic4.get("videoComplete");
        }

        if (i == 5) {
            return Topic5.get("videoComplete");
        }

        else return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeMap(Topic1);
        dest.writeMap(Topic2);
        dest.writeMap(Topic3);
        dest.writeMap(Topic4);
        dest.writeMap(Topic5);
    }
}
