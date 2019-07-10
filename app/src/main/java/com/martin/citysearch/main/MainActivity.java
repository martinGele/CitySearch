package com.martin.citysearch.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.SupportMapFragment;
import com.martin.citysearch.R;
import com.martin.citysearch.adapters.CitySearchAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Main.View {


    private SearchView searchView;
    private RecyclerView recyclerView;
    private CitySearchAdapter mAdapter;
    private Button about;
    private SupportMapFragment mapFragment;
    private ProgressBar progresBarMain;
    private android.view.View errorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progresBarMain = findViewById(R.id.progressBarMain);
        recyclerView = findViewById(R.id.recycler_view_city);
        errorView = findViewById(R.id.errorView);
        about = findViewById(R.id.buttonAbout);

        final MainPresenterImpl mainPresenter = new MainPresenterImpl(this, this);
        mainPresenter.getCityInfo();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);


        if (returnOrientation()) {
            about.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainPresenter.openAboutActivity(MainActivity.this);
                }
            });
        }

    }

    @Override
    public void setCitList(List<City> citList) {

        mAdapter = new CitySearchAdapter(this, citList, mapFragment);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showError() {
        errorView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showProgress() {
        progresBarMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarMain.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /***
         * Associate searchable configuration with the SearchView
         */
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);


        /**
         * listening to search query text change
         */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /**
                 * filter recycler view when query submitted, new view is represented on every change
                 */
                mAdapter.getFilter().filter(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                /**
                 *  filter recycler view when text is changed, new view is represented on every change
                 */
                mAdapter.getFilter().filter(query);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /**
         * noinspection SimplifiableIfStatement
         */

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private boolean returnOrientation() {

        return this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

    }

}
