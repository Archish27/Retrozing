package com.markdevelopers.retrozing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.markdevelopers.retrozing.R;
import com.markdevelopers.retrozing.common.Config;
import com.markdevelopers.retrozing.data.remote.models.DataModel;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Archish on 2/24/2017.
 */

public class ImageViewActivity extends AppCompatActivity {
    PhotoView ivImage;
    DataModel dataModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        Intent i = getIntent();
        dataModel = i.getParcelableExtra("DataModel");
        initViews();
    }

    private void initViews() {
        ivImage = (PhotoView) findViewById(R.id.ivImage);
        Picasso.with(getApplicationContext()).load(Config.MEDIA + dataModel.getImage()).into(ivImage);
    }
}
