package com.example.casestudy.api;

import com.example.casestudy.model.CampaignsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CampaignsService {

    @GET("campaigns/{page}")
    Observable<CampaignsResponse> getCampaigns(@Path(value = "page") int page);

}
