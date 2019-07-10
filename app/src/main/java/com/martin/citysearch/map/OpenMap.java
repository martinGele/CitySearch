package com.martin.citysearch.map;

import android.content.Context;

public interface OpenMap {

    interface View {
        void showError();
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void openAboutActivity(Context context);


    }

}
