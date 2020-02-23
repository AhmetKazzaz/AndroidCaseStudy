package com.example.casestudy.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class CampaignsDataFactory extends DataSource.Factory {

    private MutableLiveData<CampaignsDataSource> sourceMutableLiveData;

    public CampaignsDataFactory() {
        this.sourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        CampaignsDataSource campaignsDataSource = new CampaignsDataSource();
        sourceMutableLiveData.postValue(campaignsDataSource);
        return campaignsDataSource;
    }

    public LiveData<CampaignsDataSource> getSourceMutableLiveData() {
        return sourceMutableLiveData;
    }
}
