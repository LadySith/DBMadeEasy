package com.csnmu.databasesmadeeasy;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConceptQuizFragment extends Fragment {

    private static final String TAG = ConceptQuizFragment.class.getSimpleName();

    private FloatingActionButton fabQuizCompleted;
    private TextView txtQuestionCount;
    private Button btnNext, btnPrev;
    private ViewPager pagerQuizFragments;
    private QuizPagerAdapter quizPagerAdapter;
    private List<Quiz> quizList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private View.OnClickListener onFabClickListener;

    public ConceptQuizFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            Concept concept = (Concept) arguments.getSerializable("CONCEPT");

            // Set quiz list
            if (concept != null) {
                quizList = concept.getQuizList();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_concept_quiz, container, false);

        fabQuizCompleted = inflate.findViewById(R.id.fabQuizCompleted);
        txtQuestionCount = inflate.findViewById(R.id.edtQuestionCount);
        btnPrev = inflate.findViewById(R.id.btnPrev);
        btnNext = inflate.findViewById(R.id.btnNext);
        pagerQuizFragments = inflate.findViewById(R.id.frameQuizFragments);

        // Handle FAB button clicks
        fabQuizCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final int score = quizPagerAdapter.getScore();
                final FragmentActivity activity = getActivity();

                if (activity != null) {
                    // Check if all questions are answered
                    if (!quizPagerAdapter.checkComplete()) {

                        // Unanswered questions present, ask if the user wants to continue showing the score and ending the quiz
                        new AlertDialog.Builder(activity)
                                .setTitle("Incomplete")
                                .setMessage("You have not completed the quiz, are you finished?")

                                // No, they want to continue with quiz
                                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })

                                // Yes, they want to quit
                                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        showScore(activity, score, v);
                                    }
                                })
                                .create().show();
                    } else {
                        // All questions answered
                        showScore(activity, score, v);
                    }

                } else {
                    // If activity is null, fore safety
                    Toast.makeText(getContext(), "An error occurred while displaying score", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onClick: Cannot display activity, it is null");
                    onFabClickListener.onClick(v);
                }
            }

            /**
             * Shows alert dialog with score, followed by triggering the onNextClickListener to complete the concept
             * @param activity activity
             * @param score score calculated
             * @param view button view
             */
            void showScore(final Activity activity, int score, final View view) {
                new AlertDialog.Builder(activity)
                        .setTitle("Quiz Completed")
                        .setMessage("You scored " + score + " out of " + quizList.size())
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                onFabClickListener.onClick(view);
                            }
                        })
                        .create().show();
            }
        });

        // Check if only one question, then we show finish button
        if (quizList.size() > 1) {
            fabQuizCompleted.hide();
        }

        // Create adapter
        quizPagerAdapter = new QuizPagerAdapter(fragmentManager);
        quizPagerAdapter.setQuizList(quizList);

        // Set adapter
        pagerQuizFragments.setAdapter(quizPagerAdapter);
        pagerQuizFragments.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                txtQuestionCount.setText(String.format(Locale.getDefault(), getString(R.string.x_of_y), pagerQuizFragments.getCurrentItem() + 1, quizList.size()));
                if (position == quizList.size() - 1) {
                    btnNext.setVisibility(View.GONE);
                    fabQuizCompleted.show();
                } else if (position == 0) {
                    btnPrev.setVisibility(View.GONE);
                } else {
                    btnPrev.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                    fabQuizCompleted.hide();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousQuiz();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuiz();
            }
        });

        return inflate;
    }

    /**
     * Show next quiz
     */
    private void nextQuiz() {
        pagerQuizFragments.setCurrentItem((pagerQuizFragments.getCurrentItem() == (quizList.size() - 1)) ? 0 : pagerQuizFragments.getCurrentItem() + 1);
    }

    /**
     * Show previous quiz
     */
    private void previousQuiz() {
        pagerQuizFragments.setCurrentItem((pagerQuizFragments.getCurrentItem() == 0) ? quizList.size() : pagerQuizFragments.getCurrentItem() - 1);
    }

    /**
     * Set the next step in the concept flow
     * @param onFabClickListener next step listener
     */
    public void setOnNextStepClicked(View.OnClickListener onFabClickListener) {
        this.onFabClickListener = onFabClickListener;
    }
}