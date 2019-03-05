package com.example.admin.shareiscare.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.admin.shareiscare.R;
import com.example.admin.shareiscare.data.Constants;
import com.example.admin.shareiscare.data.Data;
import com.example.admin.shareiscare.data.MovieData;
import com.example.admin.shareiscare.data.ResultData;
import com.example.admin.shareiscare.helper.Utils;
import com.example.admin.shareiscare.listeners.DataUpdateListener;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity implements DataUpdateListener{
private ListView mListView;
private ImageView shareImageView;
private ArrayList<ResultData> resultDataList = new ArrayList<>();
private MovieViewAdapter mMoviesViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView)findViewById(R.id.list_view_movies);
        mMoviesViewAdapter= new MovieViewAdapter(this, resultDataList );
        mListView.setAdapter(mMoviesViewAdapter);

        DownloadData();
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void DownloadData() {
       Utils.downloadData(this, String.format(Constants.POPULAR_MOVIES_URL, "popular" , Constants.MOVIE_DB_API_KEY), this, Constants.MOVIE_TYPE, getString(R.string.prefs_sort_default_value));
    }

    @Override
    public void onDataUpdate(Data data) {
   if(data instanceof MovieData){
     resultDataList = ((MovieData)data).getResults();
     mMoviesViewAdapter.UpdateData(resultDataList);
   }
    }


}
