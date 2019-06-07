package com.csnmu.databasesmadeeasy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopicsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TopicsFragment extends Fragment {
    private static final String TAG = TopicsFragment.class.getSimpleName();

    private ExpandableListView expListView;
    private OnFragmentInteractionListener mListener;
    private List<Concept> conceptList;

    public TopicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_progress, container, false);
        expListView = rootView.findViewById(R.id.expandable_list_view);

        // Get concept list
        conceptList = ResourceTools.getConcepts(getContext());

        // Create mapping ConceptTitle -> ['Summary', 'Video', 'Quiz']
        final List<Pair<String, List<String>>> conceptMapping = new ArrayList<>();
        for (int i = 0; i < conceptList.size(); i++) {
            Concept concept = conceptList.get(i);
            conceptMapping.add(new Pair<>(concept.getConceptTitle(), Arrays.asList("Summary", "Video", "Quiz")));
        }

        // Create expandable list adapter
        final ExpandableListAdapter adapter = new ExpandableListAdapter(conceptMapping);
        expListView.setAdapter(adapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String conceptTitle = adapter.getGroup(i);
                Concept concept = null;
                for (Concept _concept : conceptList) {
                    if (_concept.getConceptTitle().equals(conceptTitle)){
                        concept = _concept;
                    }
                }

                // Safety check
                if (concept == null) {
                    Log.e(TAG, "onChildClick: Concept is null, we cannot proceed");
                    return true;
                }

                Intent intent = new Intent(getContext(), ConceptActivity.class);
                intent.putExtra("CONCEPT", concept);
                switch (i1) {
                    // ExpandableList Summary
                    case 0: {
                        if (concept.getConceptPages().size() == 0) {
                            Toast.makeText(getActivity(), "No content available", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        intent.setAction("CONCEPT_CONTENT");
                        break;
                    }

                    // ExpandableList Video
                    case 1: {
                        if (concept.getVideoRawResId() == 0 || concept.getVideoRawResId() == -1) {
                            Toast.makeText(getActivity(), "No video available", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        intent.setAction("CONCEPT_VIDEO");
                        break;
                    }

                    // ExpandableList Quiz
                    case 2: {
                        if (concept.getQuizList().size() == 0) {
                            Toast.makeText(getActivity(), "No quiz available", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        intent.setAction("CONCEPT_QUIZ");
                        break;
                    }
                }

                startActivity(intent);
                return true;
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}