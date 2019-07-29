package com.csnmu.databasesmadeeasy;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "ExpandableListAdapter";

    private UserProgress userProgress;
    private HashMap<String, List<String>> mStringListHashMap;
    private List<String> mListHeaderGroup;

    ExpandableListAdapter(List<Pair<String, List<String>>> pairList) {

        Log.d(TAG, "Sithe: initializing Expandable List Adapter");

        userProgress = new UserProgress("me@me.com");

        mStringListHashMap = new HashMap<>();
        mListHeaderGroup = new ArrayList<>();
        for (int i = 0; i < pairList.size(); i++) {
            mListHeaderGroup.add(pairList.get(i).first);
            mStringListHashMap.put(pairList.get(i).first, pairList.get(i).second);
        }
    }

    @Override
    public int getGroupCount() {
        return mListHeaderGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        List<String> strings = mStringListHashMap.get(mListHeaderGroup.get(i));
        return (strings != null) ? strings.size() : 0;
    }

    @Override
    public String getGroup(int i) {
        return mListHeaderGroup.get(i);
    }

    @Override
    public String getChild(int i, int i1) {
        List<String> strings = mStringListHashMap.get(mListHeaderGroup.get(i));
        return (strings != null) ? strings.get(i1) : null;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i*i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ex_list_group, (ViewGroup) viewGroup, false);

        TextView textView = view.findViewById(R.id.textViewGroup);
        String groupName = String.valueOf(getGroup(i));
        textView.setText(groupName);

        ImageView doubleCheck = view.findViewById(R.id.checkGroup);

        if (!b){
            switch (i) {
                case 0: if (userProgress.isTopic1done()){
                    doubleCheck.setVisibility(View.VISIBLE);
                }
                case 1: if (userProgress.isTopic2done()){
                    doubleCheck.setVisibility(View.VISIBLE);
                }
                case 2: if (userProgress.isTopic3done()){
                    doubleCheck.setVisibility(View.VISIBLE);
                }
                case 3: if (userProgress.isTopic4done()){
                    doubleCheck.setVisibility(View.VISIBLE);
                }
                case 4: if (userProgress.isTopic5done()){
                    doubleCheck.setVisibility(View.VISIBLE);
                }
                default:
            }
        } else {
            doubleCheck.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ex_list_item, (ViewGroup) viewGroup, false);

        TextView textView = view.findViewById(R.id.textViewItem);
        String groupName = String.valueOf(getGroup(i));
        String childName = String.valueOf(getChild(i, i1));
        textView.setText(childName);

        ImageView check = view.findViewById(R.id.checkItem);

        Map<String, Boolean> curTopic;

        switch (groupName) {
            case "Introduction to Databases and DBMS": curTopic = userProgress.getTopic1();
            case "The Relational Database Model": curTopic = userProgress.getTopic2();
            case "Database Optimization": curTopic = userProgress.getTopic3();
            case "Database Normalisation": curTopic = userProgress.getTopic4();
            case "Simple and Advanced Queries": curTopic = userProgress.getTopic5();
            default: curTopic = userProgress.getTopic4();
        }

        switch (i1) {
            case 0: if (curTopic.get("summaryComplete")){
                check.setVisibility(View.VISIBLE);
            }
            case 1: if (curTopic.get("videoComplete")){
                check.setVisibility(View.VISIBLE);
            }
            case 2: if (curTopic.get("quizComplete")){
                check.setVisibility(View.VISIBLE);
            }
            default: check.setVisibility(View.INVISIBLE);
        }


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
