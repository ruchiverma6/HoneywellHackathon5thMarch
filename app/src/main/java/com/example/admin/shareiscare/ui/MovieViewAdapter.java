package com.example.admin.shareiscare.ui;

import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.shareiscare.R;
import com.example.admin.shareiscare.data.Constants;
import com.example.admin.shareiscare.data.ResultData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by admin on 3/5/2019.
 */

public class MovieViewAdapter extends BaseAdapter implements View.OnClickListener{

    private ArrayList<ResultData> items;
    private Context context;

    //public constructor
    public MovieViewAdapter(Context context, ArrayList<ResultData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public void UpdateData(ArrayList<ResultData> resultDataList) {
        this.items = resultDataList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.layout_list_view_row_items, parent, false);
        }

        // get current item to be displayed
        ResultData currentItem = (ResultData) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.movie_poster_title);
        ImageView posterImageView = (ImageView)convertView.findViewById(R.id.movie_poster_image_view);
        ImageView shareImageView = (ImageView)convertView.findViewById(R.id.share_icon_image_view);
        shareImageView.setOnClickListener(this);
        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getTitle());
      String moviePosterUrl =  String.format(Constants.MOVIE_POSTER_BASE_URL,Constants.MOVIE_POSTER_IMAGE_WIDTH).concat(currentItem.getPoster_path());
        Picasso.with(context).load(moviePosterUrl).into(posterImageView);

        // returns the view for the current row
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_icon_image_view:
                break;
        }
    }
}
