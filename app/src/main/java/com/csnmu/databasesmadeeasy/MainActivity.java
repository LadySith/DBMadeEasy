package com.csnmu.databasesmadeeasy;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*Code reference for tabs: https://www.youtube.com/watch?v=7zaKUc2zfpI   */
public class MainActivity extends AppCompatActivity implements PracticalFragment.OnFragmentInteractionListener, TheoreticalFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Concept> conceptsList;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        // Adding Fragments
        adapter.AddFragment(new TheoreticalFragment(), "Theoretical");
        adapter.AddFragment(new PracticalFragment(), "Practical");

        // Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        switch (menuItem.getItemId()) {
                            case R.id.nav_logout:
                                FirebaseAuth.getInstance().signOut();
                                finish();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                break;
                        }

                        return true;
                    }
                });

        //populate concepts arraylist
        populateConcepts();
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
}
