package com.csnmu.databasesmadeeasy;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

public class ContentActivity extends Activity {

    private ArrayList<Concept> conceptsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
//
//        TextView tvTitle;
//        tvTitle = (TextView) findViewById(R.id.tv_title);
//        TextView tvContent;
//        tvContent = (TextView) findViewById(R.id.tv_text);
//
//        String conceptName = "string";
//
//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if (extras == null){
//                conceptName = null;
//            } else {
//                conceptName = extras.getString("CONCEPT_NAME");
//            }
//        }
//
//        tvTitle.setText(conceptName);
//
//        //setting paragraph text
//        String paragraph = "";
//        Concept thisConcept = returnConcept(conceptName);
//        Page[] thisPages = thisConcept.getConceptPages();
//
//        for (Page p : thisPages) {
//            paragraph += p.getPageTitle() + "\n\n";
//            paragraph += p.getPageText() + "\n\n";
//        }
//
//        tvContent.setText(paragraph);

    }
}
