package com.csnmu.databasesmadeeasy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class ConceptFragment extends Fragment {

    private static final String TAG = ConceptFragment.class.getSimpleName();

    private FloatingActionButton fabGoToVideo;
    private ViewPager screenPager;
    private ConceptViewPagerAdapter conceptViewPagerAdapter;
    private TabLayout tabIndicator;
    private Button btNext;
    private int position = 0;
    private View.OnClickListener onFabClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_concept, container, false);

        //initialize views
        tabIndicator = inflate.findViewById(R.id.tab_indicator);
        btNext = inflate.findViewById(R.id.bt_Next);
        fabGoToVideo = inflate.findViewById(R.id.fabGoToVideo);

        // Set on click listener
        fabGoToVideo.setOnClickListener(onFabClickListener);

        //get concept name from intent
        Concept concept = null;

        // Check intent for concept
        if (savedInstanceState == null) {
            Bundle extras = getArguments();
            if (extras != null && extras.containsKey("CONCEPT")){
                concept = (Concept) extras.getSerializable("CONCEPT");
            } else {
                Log.e(TAG, "onCreateView: No concept present");
            }
        }

        // Handle concept
        if (concept != null) {
            final List<Page> mList = concept.getConceptPages();

            //setup viewpager
            screenPager = inflate.findViewById(R.id.vp_conceptPager);
            conceptViewPagerAdapter = new ConceptViewPagerAdapter(getContext(), mList);
            screenPager.setAdapter(conceptViewPagerAdapter);

            //setup tablayout with viewpager
            tabIndicator.setupWithViewPager(screenPager);

            //Next button click
            btNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = screenPager.getCurrentItem();

                    if (position < mList.size()){
                        position++;
                        screenPager.setCurrentItem(position);
                    }

                    if (position == mList.size()){
                        position = 0;
                        screenPager.setCurrentItem(position);
                    }
                }
            });
        }

        // Return view created
        return inflate;
    }

    public void setOnNextFabClickListener(View.OnClickListener onFabClickListener) {
        this.onFabClickListener = onFabClickListener;
    }
}
