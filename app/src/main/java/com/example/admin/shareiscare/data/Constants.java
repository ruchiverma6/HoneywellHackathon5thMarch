package com.example.admin.shareiscare.data;

/**
 * Created by admin on 3/5/2019.
 */

public class Constants {

   public static String[] userIntrests={
            "Movies", "Books"
    };

    public static final String RESPONSE_DATA = "responsedata";
    public static final String RESPONSE_CODE = "responsecode";
    public static int NO_INTERNET_CONNECTION=123;
    public static final int NO_INTERNET_CONNECTION_STATUS = 123;
    //Reference variable to hold Movie DB Api key.
    public static String MOVIE_DB_API_KEY="f6ffadde87fb81871dfed27ac7dd61af";
    public static String POPULAR_MOVIES_URL="http://api.themoviedb.org/3/movie/%s?api_key=%s";
    public static final int MOVIE_TYPE =0 ;

    //Movie poster base url;
    public static String MOVIE_POSTER_BASE_URL="http://image.tmdb.org/t/p/%s/";

    //Movie poster image width
    public static String MOVIE_POSTER_IMAGE_WIDTH="w185";

    public enum FragmentsSection
    {
        Intrests(1),
        Groups(2);


        /**
         * Value for this difficulty
         */
        public final int Value;

        private FragmentsSection(int value)
        {
            Value = value;
        }
    }

    public enum YourIntrests
    {
        Movies(1),
        Books(2);


        /**
         * Value for this difficulty
         */
        public final int Value;

        private YourIntrests(int value)
        {
            Value = value;
        }
    }
}
