package com.example.admin.shareiscare.data;

import com.example.admin.shareiscare.data.Data;

import java.util.ArrayList;

/**
 * Created by ruchi on 3/11/16.
 */
public class MovieData extends Data {

    public ArrayList<ResultData> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultData> results) {
        this.results = results;
    }

    private ArrayList<ResultData> results;
}
