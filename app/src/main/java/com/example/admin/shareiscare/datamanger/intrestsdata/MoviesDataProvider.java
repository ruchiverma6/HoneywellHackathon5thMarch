package com.example.admin.shareiscare.datamanger.intrestsdata;

import android.content.Context;

import com.example.admin.shareiscare.R;
import com.example.admin.shareiscare.data.Constants;
import com.example.admin.shareiscare.helper.DataDownloaderAsyncTask;
import com.example.admin.shareiscare.helper.Utils;
import com.example.admin.shareiscare.listeners.DataUpdateListener;

/**
 * Created by admin on 3/5/2019.
 */

public class MoviesDataProvider extends IntrestDataProvider {
    public  MoviesDataProvider(){

    }

    public void DownloadData(Context context, DataUpdateListener dataUpdateListener) {
        Utils.downloadData(context, String.format(Constants.POPULAR_MOVIES_URL, "popular" , Constants.MOVIE_DB_API_KEY), dataUpdateListener, Constants.MOVIE_TYPE, context.getString(R.string.prefs_sort_default_value));
    }
    public  void DownloadData(Context context, String url, DataUpdateListener dataUpdateListener, int classType, String sortBy) {
        DataDownloaderAsyncTask downloaderAsyncTask = new DataDownloaderAsyncTask(context);
        downloaderAsyncTask.setClassType(classType);
        downloaderAsyncTask.setSortByType(sortBy);
        downloaderAsyncTask.setDataUpdateListener(dataUpdateListener);
        downloaderAsyncTask.execute(url);
    }
}
