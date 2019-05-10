package com.csnmu.databasesmadeeasy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ConceptViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<Page> mListScreen;

    public ConceptViewPagerAdapter(Context mContext, List<Page> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.iv_screenimg);
        TextView title = layoutScreen.findViewById(R.id.tv_screentitle);
        TextView description = layoutScreen.findViewById(R.id.tv_screentext);

        title.setText(mListScreen.get(position).getPageTitle());
        description.setText(mListScreen.get(position).getPageText());
        imgSlide.setImageResource(mListScreen.get(position).getImage());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}