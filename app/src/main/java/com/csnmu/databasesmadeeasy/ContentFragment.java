package com.csnmu.databasesmadeeasy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {


    private ContentRecyclerAdapter contentRecyclerAdapter;

    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        //setting up recycler view


        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerContent = view.findViewById(R.id.rv_content);
        final LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerContent.setLayoutManager(recyclerLayoutManager);

        List<Concept> concepts = ResourceTools.getConcepts(getContext());

        contentRecyclerAdapter = new ContentRecyclerAdapter(this.getContext(), concepts);
        recyclerContent.setAdapter(contentRecyclerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        contentRecyclerAdapter.notifyDataSetChanged();
    }
}
