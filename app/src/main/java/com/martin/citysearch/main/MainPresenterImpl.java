package com.martin.citysearch.main;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainPresenterImpl implements Main.Presenter {


    private final WeakReference<Main.View> aboutView;
    private final CityModelImpl aboutModel;

    public MainPresenterImpl(Main.View view, @NonNull Context context) {
        this.aboutView = new WeakReference<>(view);
        this.aboutModel = new CityModelImpl(this, context);
    }

    @Override
    public void getCityInfo() {
        Main.View aboutViewImpl = aboutView.get();
        aboutViewImpl.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                aboutModel.getCityInfo();
            }
        }, 1000);

    }

    @Override
    public void onSuccess(List<City> aboutInfo) {
        Main.View aboutViewImpl = aboutView.get();
        if (aboutViewImpl != null) {
            aboutViewImpl.hideProgress();
            aboutViewImpl.setCitList(aboutInfo);
        }
    }

    @Override
    public void onFail() {
        Main.View aboutViewImpl = aboutView.get();
        if (aboutViewImpl != null) {
            aboutViewImpl.hideProgress();
            aboutViewImpl.showError();
        }
    }
}
