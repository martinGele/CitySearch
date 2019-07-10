package com.martin.citysearch.map;

import com.martin.citysearch.about.AboutInfo;
import com.martin.citysearch.main.City;

import java.util.List;

public interface OpenMap {

    interface View {
        void showError();
        void showProgress();
        void hideProgress();
    }

}
