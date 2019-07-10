package com.martin.citysearch.main;

import java.util.List;

public interface Main {

    interface Model {
        void getCityInfo();
    }


    interface Presenter {
        void getCityInfo();
        void onSuccess(List<City> aboutInfo);
        void onFail();
    }


    interface View {
        void setCitList(List<City> citList);
        void showError();
        void showProgress();
        void hideProgress();
    }

}
