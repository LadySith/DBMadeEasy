package com.csnmu.databasesmadeeasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {

    private ArrayList<Concept> conceptsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        TextView tvTitle;
        tvTitle = (TextView) findViewById(R.id.tv_title);
        TextView tvContent;
        tvContent = (TextView) findViewById(R.id.tv_text);

        populateConcepts();

        String conceptName = "string";

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                conceptName = null;
            } else {
                conceptName = extras.getString("CONCEPT_NAME");
            }
        }

        tvTitle.setText(conceptName);

        //setting paragraph text
        String paragraph = "";
        Concept thisConcept = returnConcept(conceptName);
        Page[] thisPages = thisConcept.getConceptPages();

        for (Page p : thisPages) {
            paragraph += p.getPageTitle() + "\n\n";
            paragraph += p.getPageText() + "\n\n";
        }

        tvContent.setText(paragraph);

    }

    public Concept returnConcept(String name){
        Concept getConcept = null;
        for (int i = 0; i < conceptsArray.size(); i++){
            if (((Concept)conceptsArray.get(i)).getConceptTitle() == name){
                getConcept = (Concept)conceptsArray.get(i);
                break;
            } else getConcept = (Concept)conceptsArray.get(1);
        }

        return getConcept;
    }


    private void populateConcepts(){
        ArrayList<Concept> concepts = new ArrayList<Concept>();

        //Introduction to Databases and DBMS
        Page c1p1 = new Page(" ","Imagine operating a business without having a record of your customers, products, employees, who owes you money and whom you owe money. In simple terms, a database is used to store, manage and exploit data sets. Database applications are used to track things and produce reports, while forms are used to enter, read, delete and query data. ");
        Page c1p2 = new Page("What is Database?","A database is an integrated collection of stored data that is centrally managed and controlled. A Database possesses features such as:  \n" +
                "\n" +
                "\u2022 Class attributes \n" +
                "\u2022 Associations \n" +
                "\u2022 Descriptive information about data, restrictions  \n" +
                "\u2022 Physical data store - Raw bit and bytes of the database  \n" +
                "\u2022 Schema - Access and data controls, associations among attributes, details of physical data store ");
        Page c1p3 = new Page("What is a Database Management System (DBMS)", "A DBMS is a system software component that manages and controls access to the database. Examples include Oracle, MS Access, SQL Server and DB2. It has interfaces to access schema and extract information for valid requests. A DBMS facilitates data access programs and subroutines. ");
        Page c1p4 = new Page(R.drawable.img11,"Data versus Information", "Data:  \n" +
                "\u2022 raw facts (raw indicates that the facts have not yet been processed to reveal their meaning)  \n" +
                "\u2022 building blocks of information  \n" +
                "\u2022 must be stored and accessed properly \n" +
                "\n" +
                "Information:  \n" +
                "\u2022 produced by processing data  \n" +
                "\u2022 reveals meaning data ");
        Page c1p5 = new Page("File-based System vs. Database System","File-based System\n" +
                "The traditional File-based system is an approach to storing and organizing data and information. Using methods such as indexing, data that is stored in files can be accessed and manipulated.\n" +
                "Database System\n" +
                "A database represents a change in the way end user data are stored, accessed, and managed. Logically related data are stored in a single data repository.\n");
        Page c1p6 = new Page(R.drawable.img11, "File-based System Critique","A File System may make data management difficult for any or all of the following reasons:\n" +
                "×\tThey require extensive programming in 3GL.\n" +
                "×\tAs file systems become more complex, managing files gets more difficult.\n" +
                "×\tSecurity features are difficult to implement and are lacking.\n" +
                "×\tChanging requirements mandate changes in existing file structures.\n" +
                "×\tPrograms accessing a file are subject to change when the file structure changes. Tis requires more maintenance.\n" +
                "Structural and Data Dependence\n" +
                "Structural Dependence: \n" +
                "•\tA change in any file’s structure requires the modification of all programs using that file.\n" +
                "Data Dependence:\n" +
                "•\tA change in any file’s data characteristics requires changes of all data access programs. Example: integer to decimal\n" +
                "Structural and data dependence make file systems very difficult to manage making file systems management high maintenance.\n" +
                "Data Redundancy\n" +
                "This occurs when the same information is stored in more than one place. Problems that rise due to uncontrolled data redundancy include:\n" +
                "•\tData Inconsistency - when different and conflicting versions of the same data appear in different places\n" +
                "•\tData anomalies:\n" +
                "o\tModification anomalies (Inconsistent data due to modifications).\n" +
                "o\tInsertion anomalies (Inconsistent data due to insertions) \n" +
                "o\tDeletion anomalies (Inconsistent data due to deletions)\n");
        Page c1p7 = new Page("Functions of a DBMS", "1.\tData Dictionary Management\n" +
                "Data dictionary stores definitions of the data elements and their relationships (metadata). It removes structural and data dependency from the system.\n" +
                "2.\tData Storage Management\n" +
                "DBMS creates data storage structure and relieves the programmer from the task of defining and programming physical data characteristics.\n" +
                "3.\tData Transformation and Presentation\n" +
                "DBMS transforms data from its logical format to its physical format and vice versa.\n" +
                "4.\tSecurity Management\n" +
                "DBMS provides user security and data privacy within the database. Data security is especially important in multi-user database.\n" +
                "5.\tMulti-User Access Control\n" +
                "DBMS ensures that multiple users can access the database concurrently and still guarantees the integrity of the database.\n" +
                "6.\tBackup and Recovery Management\n" +
                "DBMS provides backup and recovery procedures to ensure data safety and integrity.\n" +
                "7.\tData Integrity Management\n" +
                "DBMS promotes and enforces integrity rules to eliminate data integrity problems.\n" +
                "8.\tDatabase Access Languages & Application Programming Interfaces\n" +
                "The DBMS’s non procedural query language simplifies data access by specifying what must be done without specifying how it must be done. Data Definition Language (DDL) is used to define the database structure while Data Manipulation Language (DML) is used for accessing and manipulating data. DBMS also provides interfaces to application programs written in procedural languages such as COBOL, C etc.  \n" +
                "9.\tDatabase Communication Interfaces\n" +
                "DBMS provides communication interfaces so that the database can be accessed through a network (internet).\n");
        Page[] c1pages = new Page[]{c1p1, c1p2, c1p3, c1p4, c1p5, c1p6, c1p7};
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
        Page c2p2 = new Page(R.drawable.img21,"The Relational Model", "This is the current database implementation standard. The data is perceived as being stored in tables (two dimensional data structures consisting of columns and rows). Tables are related to each other by means of common values in common attributes.  \n" +
                "\n" +
                "The characteristics of a Relation (dataset) are as follows:  \n" +
                "\n" +
                "\u2022 Rows contain data about an entity  \n" +
                "\u2022 Columns contain data about attributes of the entity  \n" +
                "\u2022 Cells of the table hold a single value  \n" +
                "\u2022 All entries in a column are of the same kind  \n" +
                "\u2022 Each column has a unique name  \n" +
                "\u2022 The order of the columns is unimportant  \n" +
                "\u2022 The order of the rows is unimportant  \n" +
                "\u2022 No two rows may be identical \n" +
                "\n" +
                "How do we make sure that no two rows are identical?  \n" +
                "\n" +
                "Relational Keys  \n" +
                "\n" +
                "A key is one (or more) columns of a relation that is (are) used to identify a row. It must be unique. Types of relational keys include:  \n" +
                "\n" +
                "\u2022 Primary key - unique attribute for the record \n" +
                "\u2022 Composite key - a combination of two or more attributes representing the key  \n" +
                "\u2022 Foreign key - To preserve relationships (A foreign key is a primary key from one table placed into another table). ");
        Page c2p3 = new Page(R.drawable.img23,"Relational Databases", "A Relational database is perceived by the user as a collection of tables in which data are stored. Each table consists of series of row/column intersections. Tables (or relations) are related to each other by sharing a common entity characteristic. \n" +
                "\n" +
                "Keys are the basis for representing relationship among tables. Each table must have a unique key. Keys may be natural or invented.  \n" +
                "\n" +
                "A primary key uniquely identifies a row in a table.  \n" +
                "\n" +
                "A foreign key duplicates the primary key in another table. ");
        Page c2p4 = new Page(R.drawable.img24,"Representing Relationships: One-to-many (1:M) relationship","This is when a primary key field of “one” entity type is foreign key in table that represents “many” entity type. For example, one salesperson sells to zero or many customers; one customer is assisted by only one salesperson. The foreign key (FK) attributes are implemented. The child (M side) class gains an extra attribute.");
        Page c2p5 = new Page(R.drawable.img25,"Representing Relationships: Many-to-many (M:N) association","An associative entity table, which is a “extra intersection table”, is used (or created) to represent a relationship. The primary key field(s) of both entity types are used in the associative entity table which will contain at least two columns. Each row contains a pair of PKs, 1 from each object participating in association. For example, one salesperson sells many products; one product is sold by many salespersons.");
        Page c2p6 = new Page(R.drawable.img26, "Mapping inheritance to relational DB - Option 1", "\uF0D8\tMap each class to a table\n" +
                "\uF0D8\tSuperclass & subclasses separate tables\n" +
                "\uF0D8\tTo retrieve subclass both own table & superclass must be accessed\n" +
                "\uF0D8\tSubtype discriminator is required\n" +
                "\n" +
                "Branch(branchNumber<pk>,address,phone)\n" +
                "\n" +
                "Account(accountNumber<pk>,balance, dateOpened,   branchNum<fk1>, typeOfAcc)\n" +
                "\n" +
                "SavingsAccount(accountNumber<pk>,interestRate)\n" +
                "\n" +
                "CheckingAccount(accountNumber<pk>,checkStyle,minBal)\n");
        Page c2p7 = new Page(R.drawable.img26, "Mapping inheritance to relational DB - Option 2", "\uF0D8\tOnly implement the superclass as a table\n" +
                "\uF0D8\tAttributes of subclasses become attributes of the superclass table & null values when not used\n" +
                "\uF0D8\tmost appropriate where subclasses differ from their superclass more in behaviour than in attributes\n" +
                "\uF0D8\tA subtype discriminator is required to indicate which subclass each row represents\n" +
                "\n" +
                "Branch(branchNumber<pk>,address,phone)\n" +
                "\n" +
                "Account(accountNumber<pk>,balance, dateOpened,  typeOfAcc, interestRate, checkStyle, minBal, branchNum<fk1>)\n");
        Page c2p8 = new Page(R.drawable.img26, "Mapping inheritance to relational DB - Option 3", "\uF0D8\tOnly implement the subclasses as tables\n" +
                "\uF0D8\tThe attributes of the superclass are held in all the subclass tables \n" +
                "\uF0D8\tUsually only works if superclass is abstract (i.e. never instantiated)\n" +
                "Branch(branchNumber<pk>,address,phone)\n" +
                "\n" +
                "SavingsAccount(accountNumber<pk>, balance, dateOpened, interestRate, branchNum<fk1>)\n" +
                "\n" +
                "CheckingAccount(accountNumber<pk>, balance, dateOpened, checkStyle,minBal, branchNum<fk1>)\n");
        Page[] c2pages = new Page[]{c2p1, c2p2, c2p3, c2p4, c2p5, c2p6, c2p7, c2p8};
        Concept c2 = new Concept("The Relational Database Model", " ", c2pages);
        concepts.add(c2);

        //Database Optimization
        Page c3p1 = new Page(" ", "Databases can store tremendous amounts of information, petabytes of information. Specific bits of data are accessed by queries written in a particular interface language, such as SQL. Database optimization involves maximizing the speed and efficiency with which data is retrieved. Database designers, administrators and analysts work together to optimize system performance through diverse methods. Thoughtful design that carefully addresses functional needs is the foundation of performance enhancement. ");
        Page c3p2 = new Page("Steps in Designing a Relational Database", "There are 7 basic steps to designing a relational database. There are several other steps that could be included in the database implementation and the database life cycle like, research, rollout and maintenance. \n" +
                "\n" +
                "1. Create a table for each entity (class) \n" +
                "\n" +
                "2. Choose or invent primary key for each table \n" +
                "\n" +
                "3. Add foreign keys to represent one-to-many relationships \n" +
                "\n" +
                "4. Create new tables to represent many-to-many relationships \n" +
                "\n" +
                "5. Define referential integrity constraints \n" +
                "\n" +
                "6. Evaluate schema quality and make improvements \n" +
                "\n" +
                "7. Choose appropriate data types and value restrictions (if applicable) for each field (attribute) ");
        Page c3p3 = new Page("Evaluating Schema Quality", "Just like any software artefact, database schemas can (or should) be evaluated against quality criteria such as understandability, expressiveness, maintainability and evolvability. Most quality evaluation approaches rely on global metrics counting simple pattern instances in schemas. \n" +
                "\n" +
                "A High-quality data model has: \n" +
                "\n" +
                "\u2022 Uniqueness of table rows and primary keys \n" +
                "\u2022 Lack of redundant data ");
        Page[] c3pages = new Page[]{c3p1, c3p2, c3p3};
        Concept c3 = new Concept("Database Optimization", "Databases can store tremendous amounts of information, petabytes of information. Specific bits of data are accessed by queries written in a particular interface language, such as SQL. Database optimization involves maximizing the speed and efficiency with which data is retrieved. Database designers, administrators and analysts work together to optimize system performance through diverse methods. Thoughtful design that carefully addresses functional needs is the foundation of performance enhancement. ", c3pages);
        concepts.add(c3);

        //Database Normalisation
        Page c4p1 = new Page("What is normalisation?", "The Process of simplifying the design of a database so that it achieves the optimum structure by converting complex data structures into simple, stable data structures.");
        Page c4p2 = new Page("Why do we normalise?", "Remove data redundancies to prevent anomalies as well as to Limit null values ");
        Page[] c4pages = new Page[]{c4p1,c4p2};
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

        conceptsArray = concepts;
    }
}
