package com.markdevelopers.retrozing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.markdevelopers.retrozing.R;
import com.markdevelopers.retrozing.adapter.MainAdapter;
import com.markdevelopers.retrozing.common.BaseActivity;
import com.markdevelopers.retrozing.common.RetrozingApp;
import com.markdevelopers.retrozing.data.remote.models.DataModel;
import com.markdevelopers.retrozing.data.remote.models.DataWrapper;
import com.markdevelopers.retrozing.data.repository.DataRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.MainView,SearchView.OnQueryTextListener, MainAdapter.ItemUpdateListener {
    MainPresenter mainPresenter;
    RecyclerView rvData;
    SwipeRefreshLayout swlData;
    MainAdapter mainAdapter;
    ArrayList<DataModel> allData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        DataRepository dataRepository = ((RetrozingApp) getApplication()).getComponent().dataRepository();
        mainPresenter = new MainPresenter(dataRepository, this);
        mainPresenter.fetchData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);

        final MenuItem item = menu.findItem(R.id.action_search);//Menu Resource, Menu
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    private void initViews() {
        rvData = (RecyclerView) findViewById(R.id.rvData);
        rvData.setHasFixedSize(true);
        rvData.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        swlData = (SwipeRefreshLayout) findViewById(R.id.swlData);
        swlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.fetchData();
            }
        });
    }

    @Override
    public void onData(DataWrapper dataWrapper) {
        populateView(dataWrapper);
        if (swlData.isRefreshing())
            swlData.setRefreshing(false);
    }

    private void populateView(DataWrapper dataWrapper) {
        mainAdapter = new MainAdapter(dataWrapper.data, this);
        allData = dataWrapper.data;
        rvData.setAdapter(mainAdapter);
    }

    @Override
    public void onItemCardClicked(DataModel dataModel) {
        Intent i = new Intent(MainActivity.this, ImageViewActivity.class);
        i.putExtra("DataModel", dataModel);
        startActivity(i);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return true;
    }


    private List<DataModel> filter(List<DataModel> models, String query) {
        query = query.toLowerCase();

        final List<DataModel> filteredModelList = new ArrayList<>();
        for (DataModel model : models) {
            String s = model.getName();
            final String text = s.toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        final List<DataModel> filteredModelList = filter(allData, newText);
        mainAdapter.animateTo(filteredModelList);
        rvData.scrollToPosition(0);
        return false;
    }


}
