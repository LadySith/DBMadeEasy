package com.csnmu.databasesmadeeasy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentRecyclerAdapter extends RecyclerView.Adapter<ContentRecyclerAdapter.ViewHolder>{

    private static final String TAG = "ContentRecyclerAdapter";

    private final Context mContext;
    private final UserProgress userProgress;
    private final List<Concept> conceptsList;
    private final LayoutInflater mlayoutInflater;

    public ContentRecyclerAdapter(Context mContext, List<Concept> conceptsList) {
        this.mContext = mContext;
        mlayoutInflater = LayoutInflater.from(mContext);
        this.conceptsList = conceptsList;
        this.userProgress = new UserProgress("me@me.com");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mlayoutInflater.inflate(R.layout.card_concept_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Concept concept = conceptsList.get(position);
        holder.mtextContentTitle.setText(concept.getConceptTitle());
        holder.mCurrentPosition = position;

        holder.bisSummaryDone = userProgress.getSummaryCompleteTopic(position + 1);;
        holder.bisVideoDone = userProgress.getVideoCompleteTopic(position + 1);
        holder.bisQuizDone = userProgress.getQuizCompleteTopic(position + 1);

        if (holder.bisSummaryDone){
            holder.icSummary.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            holder.icSummary.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_indicator_gray));
        }

        if (holder.bisVideoDone){
            holder.icVideo.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            holder.icVideo.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_indicator_gray));
        }

        if (holder.bisQuizDone){
            holder.icQuiz.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            holder.icQuiz.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_indicator_gray));
        }
    }

    @Override
    public int getItemCount() {
        return conceptsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mtextContentTitle;
        private final TextView mtextContentSummary;
        private final TextView mtextContentVideo;
        private final TextView mtextContentQuiz;
        private final ImageView icSummary;
        private final ImageView icVideo;
        private final ImageView icQuiz;

        public int mCurrentPosition;
        private boolean bisSummaryDone;
        private boolean bisVideoDone;
        private boolean bisQuizDone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mtextContentTitle = (TextView) itemView.findViewById(R.id.tv_contentTitle);
            mtextContentSummary = (TextView) itemView.findViewById(R.id.tv_contentSummary);
            mtextContentVideo = (TextView) itemView.findViewById(R.id.tv_contentVideo);
            mtextContentQuiz = (TextView) itemView.findViewById(R.id.tv_contentQuiz);
            icSummary = (ImageView) itemView.findViewById(R.id.ic_summary);
            icVideo = (ImageView) itemView.findViewById(R.id.ic_video);
            icQuiz = (ImageView) itemView.findViewById(R.id.ic_quiz);

            mtextContentSummary.setOnClickListener(summaryClickListener);
            mtextContentVideo.setOnClickListener(videoClickListener);
            mtextContentQuiz.setOnClickListener(quizClickListener);

            icSummary.setImageResource(R.drawable.ic_library_books_black_24dp);
            icVideo.setImageResource(R.drawable.ic_video_library_black_24dp);
            icQuiz.setImageResource(R.drawable.ic_assignment_turned_in_black_24dp);

            icSummary.setOnClickListener(summaryClickListener);
            icVideo.setOnClickListener(videoClickListener);
            icQuiz.setOnClickListener(quizClickListener);
        }

        private View.OnClickListener summaryClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Concept concept = conceptsList.get(mCurrentPosition);

                Intent intent = new Intent(mContext , ConceptActivity.class);
                intent.putExtra("CONCEPT", concept);

                if (concept.getConceptPages().size() == 0) {
                    Toast.makeText(mContext, "No content available", Toast.LENGTH_SHORT).show();
                }
                intent.setAction("CONCEPT_CONTENT");
                mContext.startActivity(intent);
            }
        };

        private View.OnClickListener videoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Concept concept = conceptsList.get(mCurrentPosition);

                Intent intent = new Intent(mContext , ConceptActivity.class);
                intent.putExtra("CONCEPT", concept);

                if (concept.getVideoRawResId() == 0 || concept.getVideoRawResId() == -1) {
                    Toast.makeText(mContext, "No video available", Toast.LENGTH_SHORT).show();
                }
                intent.setAction("CONCEPT_VIDEO");
                mContext.startActivity(intent);
            }
        };

        private View.OnClickListener quizClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Concept concept = conceptsList.get(mCurrentPosition);

                Intent intent = new Intent(mContext , ConceptActivity.class);
                intent.putExtra("CONCEPT", concept);

                if (concept.getQuizList().size() == 0) {
                    Toast.makeText(mContext, "No quiz available", Toast.LENGTH_SHORT).show();
                }
                intent.setAction("CONCEPT_QUIZ");
                mContext.startActivity(intent);
            }
        };
    }

}
