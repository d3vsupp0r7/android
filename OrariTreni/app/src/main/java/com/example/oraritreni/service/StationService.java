package com.example.oraritreni.service;

import com.example.oraritreni.model.RailRoute;
import com.example.oraritreni.model.Soluzioni;
import com.example.oraritreni.model.Station;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StationService {
    @GET("/your/rest/api/{region}")
    Call <List<Station>> getStationByRegion(@Path("region") String region);

    @GET("/your/rest/api/{departureStation}/{arrivalStation}/{date}T00:00:00")
    Call <RailRoute> getTrainsByTwoStations(@Path("departureStation") String departureStation, @Path("arrivalStation") String arrivalStation, @Path("date") String date);


}
