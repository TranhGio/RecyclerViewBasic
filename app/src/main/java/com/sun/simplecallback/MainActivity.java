package com.sun.simplecallback;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerViewCountry;
    ArrayList<Country> countries;
    MenuItem mActionToggleLayout;
    boolean isLinear = true;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerViewCountry = findViewById(R.id.recyclerView);
        countries = createData();
        final CountryAdapter countryAdapter = new CountryAdapter(countries,this);
        mRecyclerViewCountry.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mRecyclerViewCountry.setLayoutManager(linearLayoutManager);
        mRecyclerViewCountry.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerViewCountry.setAdapter(countryAdapter);
        ItemTouchHelper.Callback callback = new SwipeHelper(countryAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerViewCountry);
    }

    private ArrayList<Country> createData() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country(R.drawable.viet_nam_flag,"Viet Nam"));
        countries.add(new Country(R.drawable.england_flag,"England"));
        countries.add(new Country(R.drawable.belgium_flag,"Belgium"));
        countries.add(new Country(R.drawable.new_zealand_flag,"New zeadland"));
        countries.add(new Country(R.drawable.united_kingdom_flag,"United Kingdom"));
        countries.add(new Country(R.drawable.china_flag,"China"));
        countries.add(new Country(R.drawable.viet_nam_flag,"Viet Nam"));
        countries.add(new Country(R.drawable.england_flag,"England"));
        countries.add(new Country(R.drawable.belgium_flag,"Belgium"));
        countries.add(new Country(R.drawable.new_zealand_flag,"New zeadland"));
        countries.add(new Country(R.drawable.united_kingdom_flag,"United Kingdom"));
        countries.add(new Country(R.drawable.china_flag,"China"));
        countries.add(new Country(R.drawable.viet_nam_flag,"Viet Nam"));
        countries.add(new Country(R.drawable.england_flag,"England"));
        countries.add(new Country(R.drawable.belgium_flag,"Belgium"));
        countries.add(new Country(R.drawable.new_zealand_flag,"New zeadland"));
        countries.add(new Country(R.drawable.united_kingdom_flag,"United Kingdom"));
        countries.add(new Country(R.drawable.china_flag,"China"));
        countries.add(new Country(R.drawable.viet_nam_flag,"Viet Nam"));
        countries.add(new Country(R.drawable.england_flag,"England"));
        countries.add(new Country(R.drawable.belgium_flag,"Belgium"));
        countries.add(new Country(R.drawable.new_zealand_flag,"New zeadland"));
        countries.add(new Country(R.drawable.united_kingdom_flag,"United Kingdom"));
        countries.add(new Country(R.drawable.china_flag,"China"));
        countries.add(new Country(R.drawable.viet_nam_flag,"Viet Nam"));
        countries.add(new Country(R.drawable.england_flag,"England"));
        countries.add(new Country(R.drawable.belgium_flag,"Belgium"));
        countries.add(new Country(R.drawable.new_zealand_flag,"New zeadland"));
        countries.add(new Country(R.drawable.united_kingdom_flag,"United Kingdom"));
        countries.add(new Country(R.drawable.china_flag,"China"));
        countries.add(new Country(R.drawable.viet_nam_flag,"Viet Nam"));
        countries.add(new Country(R.drawable.england_flag,"England"));
        countries.add(new Country(R.drawable.belgium_flag,"Belgium"));
        countries.add(new Country(R.drawable.new_zealand_flag,"New zeadland"));
        countries.add(new Country(R.drawable.united_kingdom_flag,"United Kingdom"));
        countries.add(new Country(R.drawable.china_flag,"China"));
        return countries;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        mActionToggleLayout = menu.findItem(R.id.actionToggleLayout);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.actionToggleLayout){
            if(isLinear){
                mRecyclerViewCountry.setLayoutManager(new GridLayoutManager(this,2));
                mActionToggleLayout.setIcon(R.drawable.ic_grid_on_white_24dp);
                isLinear = false;
            } else{
                mRecyclerViewCountry.setLayoutManager(linearLayoutManager);
                mActionToggleLayout.setIcon(R.drawable.ic_list_white_24dp);
                isLinear = true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
