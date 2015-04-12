package com.example.codetribe1.unischool.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codetribe1.unischool.R;
import com.example.codetribe1.unischool.dto.NewsDTO;

import java.util.List;

/**
 * Created by CodeTRibe1 on 2015-03-28.
 */
public class NewsAdaptor extends BaseAdapter {

    Context mCtx;
    List<String> mList;
    List<NewsDTO>newsList;

    public NewsAdaptor(Context mCtx, List<NewsDTO> newsList) {
        this.mCtx = mCtx;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder h;
        if(convertView==null){
            h= new holder();
            LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.news_item, parent, false);

            h.title = (TextView) convertView.findViewById(R.id.titleTextView);
            h.subtitle = (TextView) convertView.findViewById(R.id.subtitleTextView);
            h.description = (TextView) convertView.findViewById(R.id.descriptionTextView);
            h.imageView = (ImageView) convertView.findViewById(R.id.image);
            h.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(h);
        }else{
            h = (holder) convertView.getTag();
        }
        String title = newsList.get(position).getTitle();
        String subtitle = newsList.get(position).getSubTitle();
        String description = newsList.get(position).getDetails();
        String date = newsList.get(position).getDatePublished();

        h.title.setText(title);
        h.subtitle.setText(subtitle);
        h.description.setText(description);
        h.date.setText(date+"");
        if(position%2!=0){
            h.imageView.setVisibility(View.GONE);
        }


        animateView(convertView);

        return convertView;
    }
    class holder{
        ImageView imageView;
        TextView title;
        TextView subtitle;
        TextView description;
        TextView date;

    }

    public void animateView(final View view) {
        Animation a = AnimationUtils.loadAnimation(mCtx, R.anim.grow_fade_in_center);
        a.setDuration(500);
        if (view == null)
            return;
        view.startAnimation(a);
    }
}
