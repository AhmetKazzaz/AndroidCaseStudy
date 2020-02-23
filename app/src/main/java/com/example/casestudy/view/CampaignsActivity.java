package com.example.casestudy.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.casestudy.R;
import com.example.casestudy.databinding.ActivityMainBinding;
import com.example.casestudy.ui.CampaignsAdapter;

public class CampaignsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PagedList<Object> campaigns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        CampaignsViewModel campaignsViewModel = ViewModelProviders.of(this).get(CampaignsViewModel.class);

        recyclerView = activityMainBinding.recyclerView;

        campaignsViewModel.getPagedListLiveData().observe(this, list -> {
            campaigns = list;
            initRv();
        });

    }

    private void initRv() {
        CampaignsAdapter campaignsAdapter = new CampaignsAdapter();
        campaignsAdapter.submitList(campaigns);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(campaignsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
}
