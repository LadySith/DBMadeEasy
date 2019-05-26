package com.csnmu.databasesmadeeasy;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ConceptActivity extends FragmentActivity {

    private static final String TAG = ConceptActivity.class.getSimpleName();

    private Fragment activeFragment = null;
    private FragmentManager supportFragmentManager;
    private FragmentManager.OnBackStackChangedListener backStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            if (supportFragmentManager.getBackStackEntryCount() == 0) {
                finishAffinity();
            }
        }
    };
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            if (intent == null) {
                Log.e(TAG, "onClick: Intent is null, cannot proceed with FAB next click");
                return;
            }
            if (activeFragment instanceof ConceptFragment) {
                Log.i(TAG, "onClick: Concept Next Clicked");
                activeFragment = createVideoFragment(intent.getIntExtra("CONCEPT_VIDEO", 0));
                showFragment(activeFragment);
            } else if (activeFragment instanceof ConceptVideoFragment) {
                Log.i(TAG, "onClick: Video Next Clicked");
                activeFragment = createQuizFragment(intent.getStringExtra("CONCEPT_QUIZ"));
                showFragment(activeFragment);
            } else if (activeFragment instanceof ConceptQuizFragment) {
                Log.i(TAG, "onClick: Quiz Next Clicked");
                // do nothing
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


            // Now we check the extras. If there is a CONCEPT_VIDEO or CONCEPT_QUIZ extra, we will skip to that fragment, else start from the beginning with a default CONCEPT_NAME
            // NOTE: The above is kept as a possible future implementation concept

            // Workaround for current implementation, may change
            if (intent.hasExtra("CONCEPT_NAME")) {
                activeFragment = createConceptFragment(intent.getStringExtra("CONCEPT_NAME"));
            } else if (intent.hasExtra("CONCEPT_VIDEO")) {
                activeFragment = createVideoFragment(intent.getIntExtra("CONCEPT_VIDEO", 0));
            } else if (intent.hasExtra("CONCEPT_QUIZ")) {
                activeFragment = createQuizFragment(intent.getStringExtra("CONCEPT_QUIZ"));
            }
            showFragment(activeFragment);
        }
    }

    private void showFragment(Fragment fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameConceptFragment, fragment)
                .commit();
    }

    private Fragment createConceptFragment(String conceptName) {
        Bundle bundle = new Bundle();
        bundle.putString("CONCEPT_NAME", conceptName);
        ConceptFragment conceptFragment = new ConceptFragment();
        conceptFragment.setArguments(bundle);
        return conceptFragment;
    }

    private Fragment createVideoFragment(int videoResId) {
        Bundle bundle = new Bundle();
        bundle.putInt("CONCEPT_VIDEO", videoResId);
        ConceptVideoFragment conceptVideoFragment = new ConceptVideoFragment();
        conceptVideoFragment.setArguments(bundle);
        conceptVideoFragment.setOnNextFabClickListener(onClickListener);
        return conceptVideoFragment;
    }

    private Fragment createQuizFragment(String conceptName) {
        Bundle bundle = new Bundle();
        bundle.putString("CONCEPT_QUIZ", conceptName);
        ConceptQuizFragment conceptQuizFragment = new ConceptQuizFragment();
        conceptQuizFragment.setArguments(bundle);
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
