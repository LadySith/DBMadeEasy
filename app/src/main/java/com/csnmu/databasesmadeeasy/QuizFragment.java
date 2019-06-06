package com.csnmu.databasesmadeeasy;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class QuizFragment extends Fragment {

    private Quiz quiz;
    private int selectedAnswer = -1;

    public QuizFragment(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_quiz, container, false);
        Context context = getActivity();
        if (quiz != null && context != null) {
            // Set image if valid
            ImageView imageView = inflate.findViewById(R.id.imgQuestion);
            if (quiz.getImgResId() != 0) {
                imageView.setImageDrawable(getResources().getDrawable(quiz.getImgResId(), context.getTheme()));
            }

            // Set question text
            TextView edtQuestion = inflate.findViewById(R.id.edtQuestion);
            edtQuestion.setText(quiz.getQuestion());

            // Add radio buttons
            int count=0;
            RadioGroup radioGroup = inflate.findViewById(R.id.radioGroupSolutions);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // Since the solution index conforms the ID's of each radiobutton, the 2 values should be identical.
                    selectedAnswer = checkedId;
                }
            });

            // Build unique options
            for (String answer : quiz.getAnswers()) {
                RadioButton radioButton = new RadioButton(context);
                RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(24, 16, 24, 16);
                radioButton.setLayoutParams(layoutParams);
                radioButton.setId(count);
                radioButton.setText(String.format(Locale.getDefault(),"%1$d. %2$s", count+1, answer));
                radioGroup.addView(radioButton, count);
                count++;
            }
        }
        return inflate;
    }

    /**
     * Get the answer the user entered
     * @return the 0-based integer answer, using the same index and the quiz answers
     */
    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    /**
     * Checks if the selected answer matches the solution
     * @return true if the answer is correct, false otherwise
     */
    public boolean isCorrect() {
        return selectedAnswer == quiz.getSolution();
    }
}
