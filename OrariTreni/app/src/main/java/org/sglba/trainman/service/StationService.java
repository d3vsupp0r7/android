package org.sglba.trainman.service;

import org.sglba.trainman.model.RailRoute;
import org.sglba.trainman.model.Station;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface StationService {

    @GET("/viaggiatrenonew/resteasy/viaggiatreno/elencoStazioni/{region}")
    Call <List<Station>> getStationByRegion(@Path("region") String region);

    @GET("/viaggiatrenonew/resteasy/viaggiatreno/soluzioniViaggioNew/{departureStation}/{arrivalStation}/{departureDate}T00:00:00")
    Call <RailRoute> getTravelSolutionsFromStations(@Path("departureStation") String departureStation, @Path("arrivalStation") String arrivalStation, @Path("departureDate") String departureDate);

}
