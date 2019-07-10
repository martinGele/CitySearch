package com.martin.citysearch.main;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CityModelImpl implements Main.Model {

    private static final String TAG = CityModelImpl.class.getSimpleName();
    private final WeakReference<Context> context;
    private final Main.Presenter presenter;
    private static final String FILE_NAME = "cities.json";
    private List<City> cities;


    public CityModelImpl(@NonNull Main.Presenter presenter, @NonNull Context context) {
        this.presenter = presenter;
        this.context = new WeakReference<>(context);
    }

    @Override
    public void getCityInfo() {
        String aboutInfoJson = getCityInfoFromAssets();

        if (aboutInfoJson != null && !aboutInfoJson.isEmpty()) {
            List<City> cityList = parseCityInfo(aboutInfoJson);
            if (cityList != null) {
                presenter.onSuccess(cityList);
                return;
            }
        }

        presenter.onFail();

    }

    private List<City> parseCityInfo(String aboutInfoJson) {
        Gson gson = new Gson();
        /**
         * with Gson I am taking the whole JSON file and  converting
         * Java Objects into their JSON representation
         */
        Type collectionType = new TypeToken<List<City>>() {
        }.getType();
        cities = gson.fromJson(aboutInfoJson, collectionType);
        /**
         * this will make the sort by the alphabet it uses TimSort
         */
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return city1.getName().compareTo(city2.getName());
            }
        });
        return cities;
    }


    private String getCityInfoFromAssets() {

        if (context.get() != null) {
            try {
                AssetManager manager = context.get().getAssets();
                InputStream file = manager.open(FILE_NAME);
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                return new String(formArray);
            } catch (IOException ex) {
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }

        return null;
    }

}
