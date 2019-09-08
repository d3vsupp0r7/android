package org.sglba.trainman;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import org.apache.commons.collections4.CollectionUtils;
import org.sglba.trainman.costraints.ApplicationCostraintsEnum;
import org.sglba.trainman.db.model.StationEntityRoom;
import org.sglba.trainman.db.room.config.AppDatabase;
import org.sglba.trainman.db.room.config.RoomDatabaseClientConfig;
import org.sglba.trainman.model.Station;
import org.sglba.trainman.retrofitclient.NetworkStationClient;
import org.sglba.trainman.service.StationService;

import java.io.Serializable;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    AppDatabase appDatabase;
    List<StationEntityRoom> stationListApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*Get data*/
        appDatabase = RoomDatabaseClientConfig.getInstance(getApplicationContext()).getAppDatabase();
        stationListApp = appDatabase.stationDao().getAll();
        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"Existing sations: " + stationListApp.size());
        /**/
        if(CollectionUtils.isEmpty(stationListApp)){
            Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"DB is empty - start retrofit calls");
            new GetAllStationsForServerAsyncTask().execute();
        }else{
            Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"There are saved stations: " + stationListApp.size());
        }


        /* Handler timer */
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                //IMPO: Size of data to pass too large: intent.putExtra("stationList",(Serializable)stationListApp);

                startActivity(intent);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private class GetAllStationsForServerAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            Retrofit retrofit = NetworkStationClient.getRetrofitClient();

            StationService stationService = retrofit.create(StationService.class);
            for (int z=0;z<23;z++) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** id: " + z);
                Call call = stationService.getStationByRegion(String.valueOf(z));
                /**/
                call.enqueue(new Callback<List<Station>>() {
                    @Override
                    public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {

                        /**/
                        if (response.body() != null) {
                            Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "GetAllStationsForServerAsyncTask - SERVICE CALL: getStationByRegion - response processing");
                            List<Station> stationList = response.body();
                            Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "GetAllStationsForServerAsyncTask - StationList Processing - size: " + stationList.size() );
                            /**/
                            for (Station currentStation: stationList) {
                                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "CurrentStation: " + currentStation.toString() );
                                //TODO: Save station details on db
                                String codiceStazione = currentStation.getCodiceStazione();

                                StationEntityRoom stationToSave = new StationEntityRoom(
                                        codiceStazione,
                                        codiceStazione.replace("S0",""),//TODO: Strategy for station processing
                                        currentStation.getLocalita().getNomeLungo(),
                                        currentStation.getLat(),
                                        currentStation.getLon()
                                );

                                Long saveResult = appDatabase.stationDao().insert(stationToSave);
                                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "Saved station on DB with id: " + saveResult);

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        //TODO: Manage application exception on comunication error
                        Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "GetAllStationsForServerAsyncTask - SERVICE CALL: getStationByRegion  - onFailure");
                    }
                });
            }
            /**/
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "GetAllStationsForServerAsyncTask - onPostExecute - STARTED" );

            /*1. Load stations to pass on apps */
            stationListApp = appDatabase.stationDao().getAll();

        }
    }
}
