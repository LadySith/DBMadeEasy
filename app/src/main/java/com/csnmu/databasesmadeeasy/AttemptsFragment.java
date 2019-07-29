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

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttemptsFragment extends Fragment {


    public AttemptsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attempts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerAttempts = view.findViewById(R.id.rv_quizzes);
        final LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerAttempts.setLayoutManager(recyclerLayoutManager);

        QuizAttempt qa1 = new QuizAttempt("Intro to DBMS", 4, 9);
        QuizAttempt qa2 = new QuizAttempt("Intro to DBMS", 7, 9);

        List<QuizAttempt> Attempts = Arrays.asList(qa1, qa2);

        final QuizAttemptRecyclerAdapter quizAttemptRecyclerAdapter = new QuizAttemptRecyclerAdapter(this.getContext(), Attempts);
        recyclerAttempts.setAdapter(quizAttemptRecyclerAdapter);
    }
}
