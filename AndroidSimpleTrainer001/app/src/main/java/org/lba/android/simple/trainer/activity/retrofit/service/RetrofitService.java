package org.lba.android.simple.trainer.activity.retrofit.service;

import org.lba.android.simple.trainer.activity.retrofit.model.station.Station;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("/viaggiatrenonew/resteasy/viaggiatreno/elencoStazioni/{region}")
    Call <List<Station>> getStationByRegion(@Path("region") String region);
}
