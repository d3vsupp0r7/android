package org.sglba.trainman.service;

import org.sglba.trainman.model.Fermate;
import org.sglba.trainman.model.RailRoute;
import org.sglba.trainman.model.Station;
import org.sglba.trainman.model.TrainStatus;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface StationService {

    @GET("/viaggiatrenonew/resteasy/viaggiatreno/elencoStazioni/{region}")
    Call <List<Station>> getStationByRegion(@Path("region") String region);

    @GET("/viaggiatrenonew/resteasy/viaggiatreno/soluzioniViaggioNew/{departureStation}/{arrivalStation}/{departureDate}")
    Call <RailRoute> getTravelSolutionsFromStations(@Path("departureStation") String departureStation, @Path("arrivalStation") String arrivalStation, @Path("departureDate") String departureDate);

    @GET("/viaggiatrenonew/resteasy/viaggiatreno/cercaNumeroTrenoTrenoAutocomplete/{trainNumber}")
    Call <String> getTrainOriginForGetStatusAPI(@Path("trainNumber") String trainNumber);

    @GET("/viaggiatrenonew/resteasy/viaggiatreno/andamentoTreno/{departureStation}/{trainNumber}")
    Call <TrainStatus> getTrainStatus(@Path("departureStation") String departureStation, @Path("trainNumber") String trainNumber);

}
