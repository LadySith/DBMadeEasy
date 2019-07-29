package com.csnmu.databasesmadeeasy;


import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConceptVideoFragment extends Fragment {

    private MediaController mediaController;
    private Context applicationContext;
    private VideoView videoView;
    private int lastPositionMS = 0;


    public ConceptVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concept_video, container, false);
        videoView = view.findViewById(R.id.vidConcept);

        // Load MediaController
        loadControls();

        // Get arguments and video URL
        final Bundle arguments = getArguments();
        if (arguments != null) {
            if (!arguments.containsKey("CONCEPT")) {
                Toast.makeText(getActivity(), "No video to play", Toast.LENGTH_SHORT).show();
            } else {
                Concept concept = (Concept) arguments.getSerializable("CONCEPT");
                if (concept != null) {
                    int resIdVideo = concept.getVideoRawResId();
                    Context context = getContext();
                    if (context != null) {
                        applicationContext = context.getApplicationContext();
                        loadVideo(videoView, resIdVideo);
                        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                videoView.stopPlayback();
                            }
                        });
                    }
                }
            }
        }

        // Set restore duration
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("VIDEO_TIME")) {
                lastPositionMS = savedInstanceState.getInt("VIDEO_TIME");
            }
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("VIDEO_TIME", lastPositionMS);
    }

    /**
     * Load MediaController controls and hook onto VideoView
     */
    private void loadControls() {
        mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
    }

    /**
     * Loads a videoRawResId into VideoView from Video Raw Resource ID
     *
     * @param view          VideoView
     * @param videoRawResId raw resource of videoRawResId
     */
    private void loadVideo(VideoView view, int videoRawResId) {
        String path = "android.resource://" + applicationContext.getPackageName() + "/" + videoRawResId;
        view.setVideoURI(Uri.parse(path));
    }

    @Override
    public void onPause() {
        super.onPause();
        // Save current position. This is due to the internal logic of the VideoView where the getting the current position will always return 0 even though we are not at the 0 position.
        lastPositionMS = videoView.getCurrentPosition();
        videoView.suspend();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        // A problem with VideoView is it can only seek in sets of 10s. If the current duration is 9s and the device is tilted, the seek seeks to 9000ms but the VideoView will start at 0:00 again.
        videoView.start();

        // Restore last position if needed
        if (lastPositionMS != 0) {
            videoView.seekTo(lastPositionMS);
        }

        // Show tilt message if in portrait mode
        if (applicationContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(applicationContext, "Tilt device for fullscreen video.", Toast.LENGTH_SHORT).show();
        }
    }



}
