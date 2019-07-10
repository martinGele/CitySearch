package com.martin.citysearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.SupportMapFragment;
import com.martin.citysearch.R;
import com.martin.citysearch.holders.CityViewHolder;
import com.martin.citysearch.main.City;

import java.util.ArrayList;
import java.util.List;

public class CitySearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context context;
    private List<City> cityList;
    private List<City> cityListFiltered;
    private SupportMapFragment mapFragment;


    public CitySearchAdapter(Context context, List<City> contactList, SupportMapFragment mapFragment) {
        this.context = context;
        this.cityList = contactList;
        this.cityListFiltered = contactList;
        this.mapFragment = mapFragment;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_row, parent, false);
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CityViewHolder cityViewHolder = (CityViewHolder) holder;
        cityViewHolder.update(context, cityListFiltered.get(position), this, mapFragment);
    }


    @Override
    public int getItemCount() {
        return cityListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    cityListFiltered = cityList;
                } else {
                    List<City> filteredList = new ArrayList<>();
                    for (City row : cityList) {
                        /**
                         * name match condition and after that reorganize the list
                         */
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    cityListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cityListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cityListFiltered = (ArrayList<City>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
