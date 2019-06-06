package com.csnmu.databasesmadeeasy;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ConceptActivity extends FragmentActivity {

    private static final String TAG = ConceptActivity.class.getSimpleName();

    private Concept concept;
    private Bundle bundle;
    private Fragment activeFragment = null;
    private FragmentManager supportFragmentManager;
    private FragmentManager.OnBackStackChangedListener backStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            if (supportFragmentManager.getBackStackEntryCount() == 0) {
                finish();
            }
        }
    };

    // Handles the 'Concept' or 'Class' content flow
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            if (intent == null) {
                Log.e(TAG, "onClick: Intent is null, cannot proceed with FAB next click");
                return;
            }

            // This can definitely be rewritten more efficiently

            // Check if we are on the Concept Content page
            if (activeFragment instanceof ConceptFragment) {
                Log.i(TAG, "onClick: Concept Next Clicked");

                // Check if there is a video to show, else check if there is a quiz, else end
                if (concept.getVideoRawResId() != 0 && concept.getVideoRawResId() != -1) {
                    // Show video
                    activeFragment = createVideoFragment(bundle);
                    showFragment(activeFragment);
                } else if (concept.getQuizList().size() != 0) {
                    // Show quiz
                    activeFragment = createQuizFragment(bundle);
                    showFragment(activeFragment);
                } else {
                    // End
                    Toast.makeText(ConceptActivity.this, "No further content available", Toast.LENGTH_SHORT).show();
                    finish();
                }
                // Check if we are on the Video Page
            } else if (activeFragment instanceof ConceptVideoFragment) {
                Log.i(TAG, "onClick: Video Next Clicked");

                // Check if there is a quiz present, else end
                if (concept.getQuizList().size() != 0) {
                    // Show Quiz
                    activeFragment = createQuizFragment(bundle);
                    showFragment(activeFragment);
                } else {
                    //End
                    Toast.makeText(ConceptActivity.this, "No further content available", Toast.LENGTH_SHORT).show();
                    finish();
                }
                // Check if we are on the quiz page, (or which defaults to this) and end
            } else if (activeFragment instanceof ConceptQuizFragment) {
                Log.i(TAG, "onClick: Quiz Next Clicked");
                finish();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept);

        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.addOnBackStackChangedListener(backStackChangedListener);


        Intent intent = getIntent();
        if (intent != null && savedInstanceState == null) {

            if (!intent.hasExtra("CONCEPT")) {
                Log.e(TAG, "onCreate: No concept present");
                finishAffinity();
            }

            // Get serialized concept
            bundle = new Bundle();
            concept = (Concept) intent.getSerializableExtra("CONCEPT");
            bundle.putSerializable("CONCEPT", concept);

            // Get action
            String action = intent.getAction();
            if (action != null) {

                // Handle start fragment
                switch (action) {
                    case "CONCEPT_CONTENT":{
                        activeFragment = createConceptFragment(bundle);
                        break;
                    }
                    case "CONCEPT_VIDEO":{
                        activeFragment = createVideoFragment(bundle);
                        break;
                    }
                    case "CONCEPT_QUIZ":{
                        activeFragment = createQuizFragment(bundle);
                        break;
                    }
                    default:{
                        Log.e(TAG, "onCreate: Unknown Action + " + action);
                    }
                }

                // Show the fragment
                showFragment(activeFragment);
            }
        }
    }

    /**
     * Show a fragment on the fragment frame. This will add the replaced fragment to the backstack.
     * @param fragment concept fragment
     */
    private void showFragment(Fragment fragment) {
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameConceptFragment, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    /**
     * Creates a fragment that covers the information handled by a concept
     * @param bundle serialized concept
     * @return created fragment
     */
    private Fragment createConceptFragment(Bundle bundle) {
        ConceptFragment conceptFragment = new ConceptFragment();
        conceptFragment.setArguments(bundle);
        return conceptFragment;
    }

    /**
     * Creates a fragment that displays a video covering the concept
     * @param bundle serialized concept
     * @return created fragment
     */
    private Fragment createVideoFragment(Bundle bundle) {
        ConceptVideoFragment conceptVideoFragment = new ConceptVideoFragment();
        conceptVideoFragment.setArguments(bundle);
        return conceptVideoFragment;
    }

    /**
     * Creates a fragment that quizzes the user about content covered
     * @param bundle serialized concept
     * @return created fragment
     */
    private Fragment createQuizFragment(Bundle bundle) {
        ConceptQuizFragment conceptQuizFragment = new ConceptQuizFragment(getSupportFragmentManager());
        conceptQuizFragment.setArguments(bundle);
        conceptQuizFragment.setOnNextStepClicked(onClickListener);
        return conceptQuizFragment;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        supportFragmentManager.removeOnBackStackChangedListener(backStackChangedListener);
    }
}