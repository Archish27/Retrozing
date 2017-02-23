package com.markdevelopers.retrozing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.markdevelopers.retrozing.R;
import com.markdevelopers.retrozing.adapter.MainAdapter;
import com.markdevelopers.retrozing.common.BaseActivity;
import com.markdevelopers.retrozing.common.RetrozingApp;
import com.markdevelopers.retrozing.data.remote.models.DataModel;
import com.markdevelopers.retrozing.data.remote.models.DataWrapper;
import com.markdevelopers.retrozing.data.repository.DataRepository;

public class MainActivity extends BaseActivity implements MainContract.MainView, MainAdapter.ItemUpdateListener {
    MainPresenter mainPresenter;
    RecyclerView rvData;
    SwipeRefreshLayout swlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        DataRepository dataRepository = ((RetrozingApp) getApplication()).getComponent().dataRepository();
        mainPresenter = new MainPresenter(dataRepository, this);
        mainPresenter.fetchData();
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
        MainAdapter mainAdapter = new MainAdapter(dataWrapper.data, this);
        rvData.setAdapter(mainAdapter);
    }

    @Override
    public void onItemCardClicked(DataModel dataModel) {
        Intent i = new Intent(MainActivity.this, ImageViewActivity.class);
        i.putExtra("DataModel", dataModel);
        startActivity(i);
    }
}
