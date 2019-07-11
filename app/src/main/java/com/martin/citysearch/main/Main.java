package com.martin.citysearch.main;

import android.content.Context;

import java.util.List;

public interface Main {

    interface Model {
       void getCityInfo();
    }


    interface Presenter {
        void getCityInfo();
        void onSuccess(List<City> aboutInfo);
        void onFail();
        void openAboutActivity(Context context);

    }


    interface View {
        void setCitList(List<City> citList);
        void showError();
        void showProgress();
        void hideProgress();
    }

}
