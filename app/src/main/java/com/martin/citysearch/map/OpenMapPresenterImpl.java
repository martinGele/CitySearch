package com.martin.citysearch.map;

import android.content.Context;
import android.content.Intent;

import com.martin.citysearch.about.AboutActivity;

import java.lang.ref.WeakReference;

public class OpenMapPresenterImpl implements OpenMap.Presenter {


    private final WeakReference<OpenMap.View> aboutView;


    public OpenMapPresenterImpl(OpenMap.View view) {
        this.aboutView = new WeakReference<>(view);

    }

    @Override
    public void openAboutActivity(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);

    }
}
