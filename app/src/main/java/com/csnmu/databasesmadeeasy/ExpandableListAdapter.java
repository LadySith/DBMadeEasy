package com.csnmu.databasesmadeeasy;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private HashMap<String, List<String>> mStringListHashMap;
    private List<String> mListHeaderGroup;

    public ExpandableListAdapter(List<Pair<String, List<String>>> pairList) {
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

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(String.valueOf(getGroup(i)));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ex_list_item, (ViewGroup) viewGroup, false);

        TextView textView = view.findViewById(R.id.textView2);
        textView.setText(String.valueOf(getChild(i, i1)));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
