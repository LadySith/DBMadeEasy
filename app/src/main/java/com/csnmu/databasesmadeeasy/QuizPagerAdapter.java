package com.csnmu.databasesmadeeasy;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuizPagerAdapter extends FragmentPagerAdapter {

    private List<Quiz> quizList = new ArrayList<>();
    private List<QuizFragment> quizFragmentList = new ArrayList<>();

    public QuizPagerAdapter(final FragmentManager fm) {
        super(fm);

        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.i("QuizPagerAdapter", "onBackStackChanged: Changed, count = " + fm.getBackStackEntryCount());
            }
        });
    }

    /**
     * Add a list of quiz questions
     * @param fragmentList quiz object list
     */
    public void setQuizList(List<Quiz> fragmentList) {
        this.quizList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return new QuizFragment(quizList.get(position));
    }

    @NonNull
    @Override
    public Fragment instantiateItem(@NonNull ViewGroup container, int position) {
        QuizFragment fragment = (QuizFragment) super.instantiateItem(container, position);
        if (!quizFragmentList.contains(fragment))
            quizFragmentList.add(position, fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return quizList.size();
    }

    /**
     * Returns the score comparing the selected answer with the solution
     * @return score
     */
    public int getScore() {
        int score = 0;
        for (QuizFragment quizFragment : quizFragmentList) {
            score += quizFragment.isCorrect() ? 1 : 0;
        }
        return score;
    }

    /**
     * Checks each quiz's solution, ensuring non of them is the default -1 selected. If it is -1, then the quiz is incomplete.
     * @return true if all quiz questions have an answer.
     */
    public boolean checkComplete() {
        for (QuizFragment quizFragment : quizFragmentList) {
            if (quizFragment.getSelectedAnswer() == -1)
                return false;
        }
        return true;
    }
}

