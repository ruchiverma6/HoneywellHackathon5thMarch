package com.example.admin.shareiscare.helper;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;

import com.example.admin.shareiscare.R;
import com.example.admin.shareiscare.data.Constants;
import com.example.admin.shareiscare.data.Data;
import com.example.admin.shareiscare.data.MovieData;
import com.example.admin.shareiscare.listeners.DataUpdateListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;


import com.example.admin.shareiscare.data.ErrorInfo;


/**
 * Created by ruchi on 31/10/16.
 */
public class DataDownloaderAsyncTask extends AsyncTask<String, Void, Data> {
    //Reference variable to hold context object
    private Context context;
    //Reference variable to hold HttpHelper object
    private HttpHelper httpHelper;
    //Reference variable to hold url value.
    private String url;
    //DataUpdateListener object to notify sources after complete download of data.
    private DataUpdateListener dataUpdateListener;
    private int classType;
    Data movieData = null;
    private String sortBy;

    public DataDownloaderAsyncTask(Context context) {
        httpHelper = new HttpHelper(context);
      this.context=context;

    }

    @Override
    protected Data doInBackground(String... params) {

        Message msg = httpHelper.doHttpGet(params[0]);
        Bundle bundle = msg.getData();
        int responseCode = bundle.getInt(Constants.RESPONSE_CODE);
        String responseStr = null;

        if (responseCode == HttpURLConnection.HTTP_OK) {
            responseStr = bundle.getString(Constants.RESPONSE_DATA);
            movieData = parseResponse(responseStr);

            //saveDataInDb(movieData,sortBy);
        } else {


            movieData = handleErrorScenario(responseCode);
        }

        return movieData;
    }




    private Data handleErrorScenario(int responseCode) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setStatusCode(responseCode);
        getErrorMessage(responseCode);
        errorInfo.setErrorMsg(getErrorMessage(responseCode));
        movieData = errorInfo;
        return movieData;
    }

    private String getErrorMessage(int responseCode) {
        String errorMsg=null;
        switch (responseCode) {
            case Constants.NO_INTERNET_CONNECTION_STATUS:

                errorMsg = context.getResources().getString(R.string.no_internet_connectivity_msg);

                break;
            default:
                errorMsg = context.getResources().getString(R.string.data_no_retrival_message);
                break;
        }
            return errorMsg;
        }


    @Override
    protected void onPostExecute(Data movieData) {
        super.onPostExecute(movieData);

            dataUpdateListener.onDataUpdate(movieData);

    }

    private Data parseResponse(String responseString) {
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

    public void setDataUpdateListener(DataUpdateListener dataUpdateListener) {
        this.dataUpdateListener = dataUpdateListener;
    }

    public void setClassType(int type) {
        this.classType = type;
    }

    public void setSortByType(String sortBy) {
        this.sortBy=sortBy;

    }
}
