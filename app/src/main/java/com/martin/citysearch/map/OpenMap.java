package com.martin.citysearch.map;

import com.martin.citysearch.about.AboutInfo;
import com.martin.citysearch.main.City;

import java.util.List;

public interface OpenMap {

    interface Model {
        void getCityInfo();
    }


    interface Presenter {
        void getCityInfo();
        void onSuccess(List<City> aboutInfo);
        void onFail();
    }


    interface View {
        void setCitName(String companyName);
        void setCountryName(String companyAddress);
        void setLongitude(String postalCode);
        void setLatitude(String companyCity);
        void showError();
        void showProgress();
        void hideProgress();
    }
}
