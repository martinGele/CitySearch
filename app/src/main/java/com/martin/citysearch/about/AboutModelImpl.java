package com.martin.citysearch.about;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 */

public class AboutModelImpl implements About.Model {

    private static final String TAG = AboutModelImpl.class.getSimpleName();
    private final About.Presenter presenter;
    private final WeakReference<Context> context;
    private static final String FILE_NAME = "aboutInfo.json";

    public AboutModelImpl(@NonNull About.Presenter presenter, @NonNull Context context){
        this.presenter = presenter;
        this.context = new WeakReference<>(context);
    }

    @Override
    public void getAboutInfo() {
        String aboutInfoJson = getAboutInfoFromAssets();

        if(aboutInfoJson != null && !aboutInfoJson.isEmpty()){
    		AboutInfo aboutInfo = parseAboutInfo(aboutInfoJson);
    		if (aboutInfo != null){
        		presenter.onSuccess(aboutInfo);
        		return;
   		 	}
		}

		presenter.onFail();
    }

    private AboutInfo parseAboutInfo(String aboutInfoJson) {
        AboutInfo aboutInfo = null;
        try {

            aboutInfo = new Gson().fromJson(aboutInfoJson, AboutInfo.class);
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return aboutInfo;
    }

    private String getAboutInfoFromAssets() {

        if(context.get() != null){
            try{
                AssetManager manager = context.get().getAssets();
                InputStream file = manager.open(FILE_NAME);
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                return new String(formArray);
            }catch (IOException ex){
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }

        return null;
    }
}
