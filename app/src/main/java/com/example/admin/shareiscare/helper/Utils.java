package com.example.admin.shareiscare.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;

import com.example.admin.shareiscare.R;
import com.example.admin.shareiscare.data.Constants;
import com.example.admin.shareiscare.data.Data;
import com.example.admin.shareiscare.data.MovieData;
import com.example.admin.shareiscare.datamanger.intrestsdata.MoviesDataProvider;
import com.example.admin.shareiscare.listeners.DataUpdateListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;


/**
 * Created by ruchi on 4/11/16.
 */
public class Utils {


    /***
     * Method to convert string to date.
     *
     * @param strDate
     * @param format
     * @return
     */
    public static String parseDate(String strDate, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;
        return dateFormat.format(date);
    }

   


    public static void downloadData(Context context, String url, DataUpdateListener dataUpdateListener, int classType, String sortBy) {

        MoviesDataProvider moviesDataProvider = new MoviesDataProvider();
        moviesDataProvider.DownloadData(context, url,dataUpdateListener, classType, sortBy);

    }


    /***
     * Method to check whether network is connected or not.
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Data getData(HttpHelper httpHelper, Context context, String url, int classType, String sortBy){
        Message msg = httpHelper.doHttpGet(url);
        Bundle bundle = msg.getData();
        int responseCode = bundle.getInt(Constants.RESPONSE_CODE);
        String responseStr = null;

        Data movieData;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            responseStr = bundle.getString(Constants.RESPONSE_DATA);
            movieData = parseResponse(responseStr,classType);
        } else {

            movieData=null;
            // movieData = handleErrorScenario(responseCode);
        }

        return movieData;
    }




    public static Data parseResponse(String responseString,int classType) {
        Gson gson = new GsonBuilder().create();
        Data movieData = null;
        switch (classType) {
            case 0:
                movieData = gson.fromJson(responseString, MovieData.class);
                break;

            default:
                break;
        }


        return movieData;
    }

}
