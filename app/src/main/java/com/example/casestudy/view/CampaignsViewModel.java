package com.example.casestudy.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.casestudy.datasource.CampaignsDataFactory;
import com.example.casestudy.datasource.CampaignsDataSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CampaignsViewModel extends AndroidViewModel {

    private LiveData<CampaignsDataSource> dataSourceLiveData;
    private LiveData<PagedList<Object>> pagedListLiveData;

    public CampaignsViewModel(@NonNull Application application) {
        super(application);

        CampaignsDataFactory dataFactory = new CampaignsDataFactory();
        dataSourceLiveData = dataFactory.getSourceMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .setPrefetchDistance(4)
                .build();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        pagedListLiveData = (new LivePagedListBuilder<Long, Object>(dataFactory, config))
                .setFetchExecutor(executor)
                .build();

    }

    LiveData<PagedList<Object>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    @Override
    protected void onCleared() {
        if (dataSourceLiveData.getValue() != null)
            dataSourceLiveData.getValue().cancelLoadingOps();

        super.onCleared();
    }
}
