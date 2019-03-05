package com.example.admin.shareiscare.datamanger.intrestsdata;

import com.example.admin.shareiscare.data.Constants;

/**
 * Created by admin on 3/5/2019.
 */

public class IntrestDataFactory {
    public static IntrestDataProvider getUserIntrestData(Constants.YourIntrests userIntrestType){
        if(userIntrestType == Constants.YourIntrests.Movies){
            return new MoviesDataProvider();
        }else if(userIntrestType == Constants.YourIntrests.Books){
            return new BooksDataProvider();
        }
        return null;
    }
}
