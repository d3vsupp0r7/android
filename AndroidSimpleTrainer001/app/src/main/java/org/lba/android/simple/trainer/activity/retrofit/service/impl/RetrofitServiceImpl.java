package org.lba.android.simple.trainer.activity.retrofit.service.impl;

import android.util.Log;

import org.lba.android.simple.trainer.activity.retrofit.config.RetrofitSingleton;
import org.lba.android.simple.trainer.activity.retrofit.model.station.Station;
import org.lba.android.simple.trainer.activity.retrofit.service.RetrofitService;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitServiceImpl
        //implements RetrofitService
{

    /*@Override
    public List<Station> getStationByRegion(String region) {

        final List<Station> listToReturn = new ArrayList<>();

        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = RetrofitSingleton.getRetrofitClient();
        *//*
        The main purpose of Retrofit is to create HTTP calls from the Java interface
         based on the annotation associated with each method.
         This is achieved by just passing the interface class as parameter to the create method
        *//*
        RetrofitService stationService = retrofit.create(RetrofitService.class);
        Call call = stationService.getStationByRegion(String.valueOf("18"));

        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {

                if (response.body() != null) {

                    Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDeparturesAndArrival - response processing");

                    List<Station> station = response.body();
                    listToReturn.addAll(station);
                }

            }

        @Override
        public void onFailure(Call call, Throwable t) {
            //TODO: Manage application exception on comunication error
            Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDeparturesAndArrival  - onFailure");
        }
    });

        return listToReturn;
    }*/
}
