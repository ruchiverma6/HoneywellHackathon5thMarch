package com.example.admin.shareiscare.helper;

import android.support.v4.app.Fragment;

import com.example.admin.shareiscare.data.Constants;
import com.example.admin.shareiscare.ui.GroupsFragment;
import com.example.admin.shareiscare.ui.YourIntrestsFragment;

/**
 * Created by admin on 3/5/2019.
 */

public class FragmentProvider {
    public static Fragment newInstance(int sectionNumber) {
        Fragment fragment;
        if(sectionNumber == Constants.FragmentsSection.Intrests.Value){
            fragment = new YourIntrestsFragment();
        }else{
            fragment = new GroupsFragment();
        }

        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);
        return fragment;
    }
}
