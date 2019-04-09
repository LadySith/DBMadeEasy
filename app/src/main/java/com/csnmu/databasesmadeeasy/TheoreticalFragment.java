package com.csnmu.databasesmadeeasy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TheoreticalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TheoreticalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TheoreticalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ExpandableListView expListView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TheoreticalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TheoreticalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TheoreticalFragment newInstance(String param1, String param2) {
        TheoreticalFragment fragment = new TheoreticalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_practical, container, false);

        expListView = rootView.findViewById(R.id.expandable_list_view);

        HashMap<String, List<String>> item = new HashMap<>();

        ArrayList<String> listDefinition = new ArrayList<>();
        listDefinition.add("Summary");
        listDefinition.add("Tutorial");
        listDefinition.add("Quiz");
        item.put("Database Definition", listDefinition);

        ArrayList<String> listUsing = new ArrayList<>();
        listUsing.add("Summary");
        listUsing.add("Tutorial");
        listUsing.add("Quiz");
        item.put("Making use of Databases", listUsing);

        ArrayList<String> listDesign = new ArrayList<>();
        listDesign.add("Summary");
        listDesign.add("Tutorial");
        listDesign.add("Quiz");
        item.put("Design Criteria", listDesign);

        ArrayList<String> listRelationships = new ArrayList<>();
        listRelationships.add("Summary");
        listRelationships.add("Tutorial");
        listRelationships.add("Quiz");
        item.put("Relationships", listRelationships);

        ArrayList<String> listAttributes = new ArrayList<>();
        listAttributes.add("Summary");
        listAttributes.add("Tutorial");
        listAttributes.add("Quiz");
        item.put("Attributes", listAttributes);

        ArrayList<String> listPKFK = new ArrayList<>();
        listPKFK.add("Summary");
        listPKFK.add("Tutorial");
        listPKFK.add("Quiz");
        item.put("Primary Key and Foreign Key", listPKFK);

        ArrayList<String> listSQLDDL = new ArrayList<>();
        listSQLDDL.add("Summary");
        listSQLDDL.add("Tutorial");
        listSQLDDL.add("Quiz");
        item.put("SQL: DDL", listSQLDDL);

        ArrayList<String> listSQLDQL = new ArrayList<>();
        listSQLDQL.add("Summary");
        listSQLDQL.add("Tutorial");
        listSQLDQL.add("Quiz");
        item.put("SQL: DQL", listSQLDQL);

        ArrayList<String> listAnomalies = new ArrayList<>();
        listAnomalies.add("Summary");
        listAnomalies.add("Tutorial");
        listAnomalies.add("Quiz");
        item.put("SQL: Anomalies and more", listAnomalies);

        ExpandableListAdapter adapter = new ExpandableListAdapter(item);
        expListView.setAdapter(adapter);

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
