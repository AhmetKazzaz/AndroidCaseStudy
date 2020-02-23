package com.example.casestudy.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PageKeyedDataSource;

import com.example.casestudy.api.RetrofitClient;
import com.example.casestudy.model.Banner;
import com.example.casestudy.model.HotDeal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CampaignsDataSource extends PageKeyedDataSource<Integer, Object> {

    private static final String TAG = "CampaignsDataSource";
    private Disposable disposable;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Object> callback) {
        fetchCampaigns(1, callback, null);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Object> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Object> callback) {
        fetchCampaigns(params.key, null, callback);
    }

    /**
     * this function combines the two types of arrays into a generic array of type Object
     *
     * @param hotDeals: HotDeal type array
     * @param banners:  Banner type Array
     * @return returns the combined array in order of one by one eg; HotDeal, Banner, Hotdeal, Banner..
     * @see: a sample test case is written in the test class
     */
    public List<Object> combineLists(ArrayList<HotDeal> hotDeals, ArrayList<Banner> banners) {
        ArrayList<Object> campaigns = new ArrayList<>();
        int maxSize = banners.size() > hotDeals.size() ? banners.size() : hotDeals.size();
        for (int i = 0; i < maxSize; i++) {
            if (i < hotDeals.size())
                campaigns.add(hotDeals.get(i));

            if (i < banners.size())
                campaigns.add(banners.get(i));

        }
        return campaigns;
    }

    /**
     * For this case study, I chose to fetch the data in the dataSource class instead of creating a repository class
     * because we only have one service. In case of a large scalable application we would need a repository class responsible to act
     * as the data layer.
     *
     * @param page:            page to call (pagination number)
     * @param initialCallback: the first fetch
     * @param nextCallback:    the next fetches
     */
    private void fetchCampaigns(int page, @Nullable LoadInitialCallback<Integer, Object> initialCallback,
                                @Nullable LoadCallback<Integer, Object> nextCallback) {
        disposable = RetrofitClient.getInstance().campaignsService().getCampaigns(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        campaignsResponse -> {
                            if (initialCallback != null) {
                                initialCallback.onResult(combineLists(campaignsResponse.hotDeals, campaignsResponse.banners), null, page + 1);
                            } else {
                                nextCallback.onResult(combineLists(campaignsResponse.hotDeals, campaignsResponse.banners), page + 1);
                            }
                        },
                        error -> Log.e(TAG, error.getMessage())
                );
    }

    /**
     * This takes care of disposing the subscription of the observable.
     */
    public void cancelLoadingOps() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
