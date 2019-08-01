package com.example.oraritreni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.oraritreni.model.RailRoute;
import com.example.oraritreni.model.Soluzioni;
import com.example.oraritreni.model.Station;
import com.example.oraritreni.model.Vehicle;
import com.example.oraritreni.retrofitclient.NetworkStationClient;
import com.example.oraritreni.service.StationService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String CAMPANIA_REGION = "18";

    public static final String ERROR = "Error";

    AutoCompleteTextView autoCompleteDepartures;
    AutoCompleteTextView autoCompleteArrivals;
    ArrayAdapter<String> adapterForDepartures;
    ArrayAdapter<String> adapterForArrivals;
    TableLayout tableLayoutPrincipal;

    Map<String, String> stationMapFilteredForDepartures = new HashMap<>();
    Map<String, String> stationMapFilteredForArrivals = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button findButton = findViewById(R.id.findButton);
        autoCompleteArrivals = findViewById(R.id.autoCompleteArrivals);
        autoCompleteDepartures = findViewById(R.id.autoCompleteDepartures);
        tableLayoutPrincipal = findViewById(R.id.TableLayoutPrincipal);

        autoCompleteDepartures.setThreshold(2);
        autoCompleteArrivals.setThreshold(2);

        autoCompleteDepartures.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getStationByRegionForDepartures(CAMPANIA_REGION, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        autoCompleteArrivals.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getStationByRegionForArrivals(CAMPANIA_REGION, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCompleteDepartures.getText() != null & !autoCompleteDepartures.getText().toString().isEmpty()
                        && autoCompleteArrivals.getText() != null & !autoCompleteArrivals.getText().toString().isEmpty()) {
                    String departureStationCode = stationMapFilteredForDepartures.get(autoCompleteDepartures.getText().toString()).replace("S0", "");
                    String arrivalStationCode = stationMapFilteredForArrivals.get(autoCompleteArrivals.getText().toString()).replace("S0", "");
                    getTrainByStations(departureStationCode, arrivalStationCode, "2019-08-01");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getStationByRegionForDepartures(String region, String charSequence) {
        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = NetworkStationClient.getRetrofitClient();
        /*
        The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
        */
        StationService stationService = retrofit.create(StationService.class);
        /*
        Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
        */
        Call call = stationService.getStationByRegion(region);
        /*
        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        */
        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                Map<String, String> stationMapFiltered = new HashMap<>();
                List<String> stationNamesList = new ArrayList<>();
                if (response.body() != null) {
                    List<Station> station = response.body();
                    stationMapFiltered.clear();
                    stationNamesList.clear();
                    for (Station singleStation : station) {
                        if (singleStation.getLocalita().getNomeLungo().toLowerCase().startsWith(charSequence)) {
                            stationMapFiltered.put(singleStation.getLocalita().getNomeLungo(), singleStation.getCodStazione());
                            stationNamesList.add(singleStation.getLocalita().getNomeLungo());
                        }
                    }
                    stationMapFilteredForDepartures.putAll(stationMapFiltered);
                    adapterForDepartures = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, stationNamesList);
                    autoCompleteDepartures.setAdapter(adapterForDepartures);

                } else {
                    //aggiungere exception error on response
                    System.out.println("Error");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Error");
            }
        });
    }

    private void getStationByRegionForArrivals(String region, String charSequence) {
        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = NetworkStationClient.getRetrofitClient();
        /*
        The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
        */
        StationService stationService = retrofit.create(StationService.class);
        /*
        Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
        */
        Call call = stationService.getStationByRegion(region);
        /*
        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        */
        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                Map<String, String> stationMapFiltered = new HashMap<>();
                List<String> stationNamesList = new ArrayList<>();
                if (response.body() != null) {
                    List<Station> station = response.body();
                    stationMapFiltered.clear();
                    stationNamesList.clear();
                    for (Station singleStation : station) {
                        if (singleStation.getLocalita().getNomeLungo().toLowerCase().startsWith(charSequence)) {
                            stationMapFiltered.put(singleStation.getLocalita().getNomeLungo(), singleStation.getCodStazione());
                            stationNamesList.add(singleStation.getLocalita().getNomeLungo());
                        }
                    }

                    stationMapFilteredForArrivals.putAll(stationMapFiltered);
                    adapterForArrivals = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, stationNamesList);
                    autoCompleteArrivals.setAdapter(adapterForArrivals);

                } else {
                    //aggiungere exception error on response
                    System.out.println("Error");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Error");
            }
        });
    }


    private void getTrainByStations(String departureStation, String arrivalStation, String date) {
        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = NetworkStationClient.getRetrofitClient();
        /*
        The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
        */
        StationService stationService = retrofit.create(StationService.class);
        /*
        Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
        */
        Call call = stationService.getTrainsByTwoStations(departureStation, arrivalStation, date);
        /*
        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        */
        call.enqueue(new Callback<RailRoute>() {
            @Override
            public void onResponse(Call<RailRoute> call, Response<RailRoute> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                if (response.body() != null) {
                    RailRoute railRoute = response.body();
                    List<Soluzioni> solutionsList = railRoute.getSoluzioni();
                    for (Soluzioni singleSolution : solutionsList) {
                        createSolutionsLayoutTable(singleSolution);
                    }

                    System.out.println("TuttoOk");

                } else {
                    //aggiungere exception error on response
                    System.out.println(ERROR);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //aggiungere exception error on request
                System.out.println(ERROR);
            }
        });
    }

    private void createSolutionsLayoutTable(Soluzioni solution) {
        //TableRow/Layout creation
        TableRow tableRowSolution = new TableRow(this);
        TableLayout tableLayoutVehicle = new TableLayout(this);
        TableRow tableRowVehicleDeparture = new TableRow(this);
        TableRow tableRowVehicleArrival = new TableRow(this);
        TableRow tableRowVehicleStatus = new TableRow(this);
        //TextViewsSection
        TextView textViewDepartureTime = new TextView(this);
        TextView textViewArrivalTime = new TextView(this);
        Space spaceBetweenTimeDepartureStation = new Space(this);
        spaceBetweenTimeDepartureStation.setMinimumWidth(20);
        Space spaceBetweenTimeArrivalStation = new Space(this);
        spaceBetweenTimeArrivalStation.setMinimumWidth(20);
        TextView textViewDepartureStation = new TextView(this);
        TextView textViewArrivalStation = new TextView(this);
        //TextViewsSettings
        Vehicle firstVehicle = solution.getVehicles().get(0);
        Vehicle lastVehicle = solution.getVehicles().get(solution.getVehicles().size() - 1);
        textViewDepartureTime.setText(DateUtils.formatDate(firstVehicle.getOrarioPartenza()));
        textViewArrivalTime.setText(DateUtils.formatDate(lastVehicle.getOrarioArrivo()));
        textViewDepartureStation.setText(autoCompleteDepartures.getText().toString().replace("S0", ""));
        textViewArrivalStation.setText(autoCompleteArrivals.getText().toString().replace("S0", ""));

        tableRowVehicleDeparture.addView(textViewDepartureTime);
        tableRowVehicleDeparture.addView(spaceBetweenTimeDepartureStation);
        tableRowVehicleDeparture.addView(textViewDepartureStation);
        tableRowVehicleArrival.addView(textViewArrivalTime);
        tableRowVehicleArrival.addView(spaceBetweenTimeArrivalStation);
        tableRowVehicleArrival.addView(textViewArrivalStation);
        tableLayoutVehicle.addView(tableRowVehicleDeparture);
        tableLayoutVehicle.addView(tableRowVehicleArrival);
        tableLayoutVehicle.addView(tableRowVehicleStatus);
        tableRowSolution.addView(tableLayoutVehicle);
        tableLayoutPrincipal.addView(tableRowSolution);
    }


}
