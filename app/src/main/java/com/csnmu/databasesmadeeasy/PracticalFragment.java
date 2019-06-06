package com.csnmu.databasesmadeeasy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Pair;
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
 * {@link PracticalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PracticalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PracticalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ExpandableListView expListView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PracticalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PracticalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PracticalFragment newInstance(String param1, String param2) {
        PracticalFragment fragment = new PracticalFragment();
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

        ArrayList<String> listRelationShips = new ArrayList<>();
        listRelationShips.add("Summary");
        listRelationShips.add("Simulation");
        listRelationShips.add("Quiz");
        item.put("Database Definition", listRelationShips);

        ArrayList<String> listAttributes = new ArrayList<>();
        listAttributes.add("Summary");
        listAttributes.add("Simulation");
        listAttributes.add("Quiz");
        item.put("Attributes", listAttributes);

        ArrayList<String> listPKFK = new ArrayList<>();
        listPKFK.add("Summary");
        listPKFK.add("Simulation");
        listPKFK.add("Quiz");
        item.put("Primary Key and Foreign Key", listPKFK);

        ArrayList<String> listSQLCreate = new ArrayList<>();
        listSQLCreate.add("Summary");
        listSQLCreate.add("Simulation");
        listSQLCreate.add("Quiz");
        item.put("SQL: Create a DB", listSQLCreate);

        ArrayList<String> listSQLQuery = new ArrayList<>();
        listSQLQuery.add("Summary");
        listSQLQuery.add("Simulation");
        listSQLQuery.add("Quiz");
        item.put("SQL: Query a DB", listSQLQuery);

        ArrayList<String> listSQLAnomalies = new ArrayList<>();
        listSQLAnomalies.add("Summary");
        listSQLAnomalies.add("Simulation");
        listSQLAnomalies.add("Quiz");
        item.put("SQL: Anomalies", listSQLAnomalies);

        ArrayList<String> listDDL = new ArrayList<>();
        listDDL.add("Summary");
        listDDL.add("Simulation");
        listDDL.add("Quiz");
        item.put("SQL: Anomalies", listDDL);

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
