package com.bw.movie.wdyy;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LunboActivity extends AppCompatActivity {
    List<View> list = new ArrayList<>();
    @BindView(R.id.viewpager_id)
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunbo);
        ButterKnife.bind(this);
    }
}
