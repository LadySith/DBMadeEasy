package com.csnmu.databasesmadeeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuizAttemptRecyclerAdapter extends RecyclerView.Adapter<QuizAttemptRecyclerAdapter.ViewHolder> {

    private final Context mContext;

    private final List<QuizAttempt> listQuizAttempts;
    private final LayoutInflater mlayoutInflater;

    public QuizAttemptRecyclerAdapter(Context mContext, List<QuizAttempt> listQuizAttempts) {
        this.mContext = mContext;
        mlayoutInflater = LayoutInflater.from(mContext);
        this.listQuizAttempts = listQuizAttempts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mlayoutInflater.inflate(R.layout.item_quiz_attempt, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizAttempt quizAttempt = listQuizAttempts.get(position);
        holder.quizTitle.setText(quizAttempt.getTopic());
        holder.quizScore.setText(quizAttempt.getScore() + " out of " + quizAttempt.getTotal());
        holder.icQuizDone.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
    }

    @Override
    public int getItemCount() {
        return listQuizAttempts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView quizTitle;
        private final TextView quizScore;
        private final ImageView icQuizDone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            quizTitle = (TextView) itemView.findViewById(R.id.tv_quizTitle);
            quizScore = (TextView) itemView.findViewById(R.id.tv_quizResult);

            icQuizDone = (ImageView) itemView.findViewById(R.id.ic_quizDone);


        }
    }
}
