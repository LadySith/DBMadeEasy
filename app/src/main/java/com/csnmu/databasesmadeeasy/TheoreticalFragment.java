package com.csnmu.databasesmadeeasy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;

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
    private ArrayList<Concept> conceptsList;


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

        //populate concepts arraylist
        populateConcepts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_practical, container, false);

        expListView = rootView.findViewById(R.id.expandable_list_view);

        HashMap<String, List<String>> item = new HashMap<>();

        ArrayList<String>[] listLabels = new ArrayList[conceptsList.size()];

        for(int i = 0; i < conceptsList.size(); i++){
            listLabels[i] = new ArrayList<>();
            listLabels[i].add("\tSummary");
            listLabels[i].add("\tVideo");
            listLabels[i].add("\tQuiz");
            item.put((conceptsList.get(i)).getConceptTitle(), listLabels[i] );
        }

        final ExpandableListAdapter adapter = new ExpandableListAdapter(item);
        expListView.setAdapter(adapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                String selected = (String)adapter.getGroup(i);

                switch (i1) {
                    // ExpandableList Summary
                    case 0: {
                        Intent intent = new Intent(getContext(), ConceptActivity.class);
                        intent.putExtra("CONCEPT_NAME", selected);
                        startActivity(intent);
                        break;
                    }

                    // ExpandableList Video
                    case 1: {
                        Intent intent = new Intent(getContext(), ConceptActivity.class);
                        intent.putExtra("CONCEPT_VIDEO", R.raw.database_optimization);
                        startActivity(intent);
                        break;
                    }

                    // ExpandableList Quiz
                    case 2: {
                        Intent intent = new Intent(getContext(), ConceptActivity.class);
                        intent.putExtra("CONCEPT_QUIZ", selected);
                        startActivity(intent);
                        break;
                    }
                }

                return true;
            }
        });

        return rootView;
    }

    public void populateConcepts(){
        ArrayList<Concept> concepts = new ArrayList<Concept>();

        //Introduction to Databases and DBMS
        Page c1p1 = new Page(" ","Imagine operating a business without having a record of your customers, products, employees, who owes you money and whom you owe money. In simple terms, a database is used to store, manage and exploit data sets. Database applications are used to track things and produce reports, while forms are used to enter, read, delete and query data. ");
        Page c1p2 = new Page("What is Database?","A database is an integrated collection of stored data that is centrally managed and controlled. A Database possesses features such as:  \n" +
                "\n" +
                "Class attributes  \n" +
                "\n" +
                "Associations  \n" +
                "\n" +
                "Descriptive information about data, restrictions  \n" +
                "\n" +
                "Physical data store - Raw bit and bytes of the database  \n" +
                "\n" +
                "Schema - Access and data controls, associations among attributes, details of physical data store ");
        Page c1p3 = new Page("What is a Database Management System (DBMS)", "A DBMS is a system software component that manages and controls access to the database. Examples include Oracle, MS Access, SQL Server and DB2. It has interfaces to access schema and extract information for valid requests. A DBMS facilitates data access programs and subroutines. ");
        Page[] c1pages = new Page[]{c1p1, c1p2, c1p3};
        Concept c1 = new Concept("Introduction to Databases and DBMS", "Imagine operating a business without having a record of your customers, products, employees, who owes you money and whom you owe money. In simple terms, a database is used to store, manage and exploit data sets. Database applications are used to track things and produce reports, while forms are used to enter, read, delete and query data. ", c1pages);
        concepts.add(c1);

        //The Relational Database Model
        Page c2p1 = new Page("Database Models", "A Database Model determines the logical structure of a database and drives the way data will be stored, organized, viewed, represented, and manipulated. The model also determines how the data can be interrelated. \n" +
                "\n" +
                "There are two categories of Database Models:  \n" +
                "\n" +
                "Conceptual models - concerned with what is represented rather than how it is represented. (E-R model, OO)  \n" +
                "\n" +
                "Implementation models - place the emphasis on how the data are represented in the database or on how the data structures are implemented. (Hierarchical, Network, Relational) ");
        Page c2p2 = new Page("The Relational Model", "This is the current database implementation standard. The data is perceived as being stored in tables (two dimensional data structures consisting of columns and rows). Tables are related to each other by means of common values in common attributes.  \n" +
                "\n" +
                "The characteristics of a Relation (dataset) are as follows:  \n" +
                "\n" +
                "Rows contain data about an entity  \n" +
                "\n" +
                "Columns contain data about attributes of the entity  \n" +
                "\n" +
                "Cells of the table hold a single value  \n" +
                "\n" +
                "All entries in a column are of the same kind  \n" +
                "\n" +
                "Each column has a unique name  \n" +
                "\n" +
                "The order of the columns is unimportant  \n" +
                "\n" +
                "The order of the rows is unimportant  \n" +
                "\n" +
                "No two rows may be identical \n" +
                "\n" +
                "How do we make sure that no two rows are identical?  \n" +
                "\n" +
                "Relational Keys  \n" +
                "\n" +
                "A key is one (or more) columns of a relation that is (are) used to identify a row. It must be unique. Types of relational keys include:  \n" +
                "\n" +
                "Primary key - unique attribute for the record  \n" +
                "\n" +
                "Composite key - a combination of two or more attributes representing the key  \n" +
                "\n" +
                "Foreign key - To preserve relationships (A foreign key is a primary key from one table placed into another table). ");
        Page c2p3 = new Page("Relational Databases", "A Relational database is perceived by the user as a collection of tables in which data are stored. Each table consists of series of row/column intersections. Tables (or relations) are related to each other by sharing a common entity characteristic. \n" +
                "\n" +
                "Keys are the basis for representing relationship among tables. Each table must have a unique key. Keys may be natural or invented.  \n" +
                "\n" +
                "A primary key uniquely identifies a row in a table.  \n" +
                "\n" +
                "A foreign key duplicates the primary key in another table. ");
        Page[] c2pages = new Page[]{c2p1, c2p2, c2p3};
        Concept c2 = new Concept("The Relational Database Model", " ", c2pages);
        concepts.add(c2);

        //Database Optimization
        Page c3p1 = new Page(" ", "Databases can store tremendous amounts of information, petabytes of information. Specific bits of data are accessed by queries written in a particular interface language, such as SQL. Database optimization involves maximizing the speed and efficiency with which data is retrieved. Database designers, administrators and analysts work together to optimize system performance through diverse methods. Thoughtful design that carefully addresses functional needs is the foundation of performance enhancement. ");
        Page c3p2 = new Page("Steps in Designing a Relational Database", "There are 7 basic steps to designing a relational database. There are several other steps that could be included in the database implementation and the database life cycle like, research, rollout and maintenance. \n" +
                "\n" +
                "Create a table for each entity (class) \n" +
                "\n" +
                "Choose or invent primary key for each table \n" +
                "\n" +
                "Add foreign keys to represent one-to-many relationships \n" +
                "\n" +
                "Create new tables to represent many-to-many relationships \n" +
                "\n" +
                "Define referential integrity constraints \n" +
                "\n" +
                "Evaluate schema quality and make improvements \n" +
                "\n" +
                "Choose appropriate data types and value restrictions (if applicable) for each field (attribute) ");
        Page[] c3pages = new Page[]{c3p1, c3p2};
        Concept c3 = new Concept("Database Optimization", "Databases can store tremendous amounts of information, petabytes of information. Specific bits of data are accessed by queries written in a particular interface language, such as SQL. Database optimization involves maximizing the speed and efficiency with which data is retrieved. Database designers, administrators and analysts work together to optimize system performance through diverse methods. Thoughtful design that carefully addresses functional needs is the foundation of performance enhancement. ", c3pages);
        concepts.add(c3);

        //Database Normalisation
        Page c4p1 = new Page("What is normalisation?", "The Process of simplifying the design of a database so that it achieves the optimum structure by converting complex data structures into simple, stable data structures.");
        Page c4p2 = new Page("Why do we normalise?", "Remove data redundancies to prevent anomalies as well as to Limit null values ");
        Page[] c4pages = new Page[]{c4p1,c4p1};
        Concept c4 = new Concept("Database Normalisation", " ", c4pages);
        concepts.add(c4);

        //Simple and Advanced Queries
        Page c5p1 = new Page("Introduction", "SQL is a standard language for storing, manipulating, managing and retrieving data in databases. \n" +
                "\n" +
                "SQL code is divided into four main categories: \n" +
                "\n" +
                "Data Manipulation Language (DML): Deals with the manipulation of data present in database and this includes most of the SQL statements. \n" +
                "\n" +
                "Examples of DML: \n" +
                "\n" +
                "SELECT-is used to retrieve data from the database \n" +
                "\n" +
                "INSERT-is used to insert data into a database table \n" +
                "\n" +
                "UPDATE-is used to update existing data within a database table \n" +
                "\n" +
                "DELETE-is used to delete records from the database table \n" +
                "\n" +
                " \n" +
                "\n" +
                "Data Definition language (DDL)-Simply deals with descriptions of the database schema and is used to create and modify the structure of the database objects in database \n" +
                "\n" +
                "Examples of DDL commands: \n" +
                "\n" +
                "Create-creates the database or its objects e.g. table, index, function, view, etc. \n" +
                "\n" +
                "Drop-deletes database objects \n" +
                "\n" +
                "Alter-Alters the database structure \n" +
                "\n" +
                "Truncate-Removes all records from a database table \n" +
                "\n" +
                "Comment-is used to add comments to the data dictionary \n" +
                "\n" +
                "Rename-renames an existing object in the database \n" +
                "\n" +
                " \n" +
                "\n" +
                "Data Control language (DCL): Mainly deals with controlling access to the data stored in a database \n" +
                "\n" +
                "Examples of DCL commands \n" +
                "\n" +
                "Grant-gives the user access to the database \n" +
                "\n" +
                "Revokes-withdraws user’s access from the database \n" +
                "\n" +
                " \n" +
                "\n" +
                "Transaction Control Language (TCL)-Deals with the transaction within the database \n" +
                "\n" +
                "Examples of TCL commands \n" +
                "\n" +
                "Commit-commits a transaction \n" +
                "\n" +
                "Rollback-rollbacks a transaction in case of any error occurs \n" +
                "\n" +
                "Savepoint-sets a savepoint within a transaction \n" +
                "\n" +
                "Set Transaction-specify characteristics of the transaction ");
        Page[] c5pages = new Page[]{c5p1};
        Concept c5 = new Concept("Simple and Advanced Queries", " ", c5pages);
        concepts.add(c5);

        conceptsList = concepts;
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
