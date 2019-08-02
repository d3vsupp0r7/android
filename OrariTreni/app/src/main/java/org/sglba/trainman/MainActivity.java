package org.sglba.trainman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.util.Log;

import org.sglba.trainman.costraints.ApplicationCostraintsEnum;
import org.sglba.trainman.model.RailRoute;
import org.sglba.trainman.model.Soluzioni;
import org.sglba.trainman.model.Station;
import org.sglba.trainman.model.Vehicle;
import org.sglba.trainman.retrofitclient.NetworkStationClient;
import org.sglba.trainman.service.StationService;
import org.sglba.trainman.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    Boolean isCalendarButtonPressed=true;

    //To Add on Date Utils
    String   selectedDate;
    int yearToSet;
    int monthToSet;
    int dayToSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button findButton = findViewById(R.id.findButton);
        Button calendarButton= findViewById(R.id.calendarButton);
        autoCompleteArrivals = findViewById(R.id.autoCompleteArrivals);
        autoCompleteDepartures = findViewById(R.id.autoCompleteDepartures);
        tableLayoutPrincipal = findViewById(R.id.TableLayoutPrincipal);

        autoCompleteDepartures.setThreshold(2);
        autoCompleteArrivals.setThreshold(2);

        autoCompleteDepartures.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "autoCompleteDepartures.addTextChangedListener - executed");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "autoCompleteDepartures.onTextChanged - executed");
                getStationByRegionForDepartures(CAMPANIA_REGION, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "autoCompleteDepartures.afterTextChanged - executed");

            }
        });

        autoCompleteArrivals.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "autoCompleteArrivals.addTextChangedListener - executed");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getStationByRegionForArrivals(CAMPANIA_REGION, s.toString());
                Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "autoCompleteArrivals.onTextChanged - executed");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "autoCompleteArrivals.afterTextChanged - executed");

            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCompleteDepartures.getText() != null & !autoCompleteDepartures.getText().toString().isEmpty()
                        && autoCompleteArrivals.getText() != null & !autoCompleteArrivals.getText().toString().isEmpty()) {
                    String departureStationCode = stationMapFilteredForDepartures.get(autoCompleteDepartures.getText().toString()).replace("S0", "");
                    String arrivalStationCode = stationMapFilteredForArrivals.get(autoCompleteArrivals.getText().toString()).replace("S0", "");
                    tableLayoutPrincipal.removeAllViews();
                    getTrainByStations(departureStationCode, arrivalStationCode,DateUtils.formatDate(selectedDate!=null?selectedDate:""));
                }
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "calendarButton.onClick - executed");
                CalendarView calendarView = new CalendarView(MainActivity.this);
                if (isCalendarButtonPressed){
                    if (selectedDate != null) {
                        Calendar calendar = new GregorianCalendar();
                        calendar.set(yearToSet, monthToSet, dayToSet, 0, 0);
                        calendarView.setDate(calendar.getTimeInMillis());
                    }

                    // Add Listener in calendar
                    calendarView
                            .setOnDateChangeListener(
                                    new CalendarView
                                            .OnDateChangeListener() {
                                        @Override
                                        public void onSelectedDayChange(
                                                @NonNull CalendarView view,
                                                int year,
                                                int month,
                                                int dayOfMonth) {
                                            yearToSet = year;
                                            monthToSet = month;
                                            dayToSet = dayOfMonth;

                                            String sMonth = String.valueOf((monthToSet + 1));
                                            String sDay = String.valueOf(dayToSet);

                                            if (sDay.length() == 1)
                                                sDay = "0" + sDay;

                                            if (sMonth.length() == 1)
                                                sMonth = "0" + sMonth;

                                            String date
                                                    = sDay + "-"
                                                    + sMonth + "-" + year;
                                            Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "calendarButton.onClick - selectedDate: " + selectedDate);
                                            selectedDate = date;
                                            tableLayoutPrincipal.removeView(calendarView);
                                            isCalendarButtonPressed=true;
                                        }
                                    });
                    tableLayoutPrincipal.addView(calendarView);
                    isCalendarButtonPressed=false;
                }
            }

        });


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
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDepartures - started");

                Map<String, String> stationMapFiltered = new HashMap<>();
                List<String> stationNamesList = new ArrayList<>();

                if (response.body() != null) {
                    Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDepartures - response processing");

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
                    //TODO: Manage application exception on comunication error
                    Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDepartures - failed");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //TODO: Manage application exception on comunication error
                Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDepartures - onFailure");
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
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForArrivals - started");
                Map<String, String> stationMapFiltered = new HashMap<>();
                List<String> stationNamesList = new ArrayList<>();

                if (response.body() != null) {

                    Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDepartures - response processing");

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
                    //TODO: Manage application exception on comunication error
                    Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForArrivals - failed");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //TODO: Manage application exception on comunication error
                Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForArrivals  - onFailure");
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
        Call call = stationService.getTravelSolutionsFromStations(departureStation, arrivalStation, date);
        /*
        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        */
        call.enqueue(new Callback<RailRoute>() {
            @Override
            public void onResponse(Call<RailRoute> call, Response<RailRoute> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                if (response.body() != null) {
                    Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getTravelSolutionsFromStations - started");

                    RailRoute railRoute = response.body();
                    List<Soluzioni> solutionsList = railRoute.getSoluzioni();
                    Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** START generate dynamic tableLayout");
                    for (Soluzioni singleSolution : solutionsList) {
                        createSolutionsLayoutTable(singleSolution);
                    }
                    Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** END generate dynamic tableLayout");

                } else {
                    //TODO: Manage application exception on comunication error
                    Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getTravelSolutionsFromStations - failed");
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //TODO: Manage application exception on comunication error
                Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getTravelSolutionsFromStations  - onFailure");
            }
        });
    }

    private void createSolutionsLayoutTable(Soluzioni solution) {
        //TableRow/Layout creation
        TableRow tableRowSolution = new TableRow(this);
        tableRowSolution.setBackgroundResource(R.drawable.rowborder);

        TableLayout tableLayoutVehicle = new TableLayout(this);
        //row1
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
        Space spaceBetweenArrivalStationDurationTime= new Space(this);
        spaceBetweenArrivalStationDurationTime.setMinimumWidth(250);
        TextView textViewDurationTime= new TextView(this);

        //TextViewsSettings
        Vehicle firstVehicle = solution.getVehicles().get(0);
        Vehicle lastVehicle = solution.getVehicles().get(solution.getVehicles().size() - 1);
        textViewDepartureTime.setText(DateUtils.formatDate(firstVehicle.getOrarioPartenza()));
        textViewArrivalTime.setText(DateUtils.formatDate(lastVehicle.getOrarioArrivo()));
        textViewDepartureStation.setText(autoCompleteDepartures.getText().toString().replace("S0", ""));
        textViewArrivalStation.setText(autoCompleteArrivals.getText().toString().replace("S0", ""));
        textViewDurationTime.setText(solution.getDurata());

        //row1 render
        tableRowVehicleDeparture.addView(textViewDepartureTime);
        tableRowVehicleDeparture.addView(spaceBetweenTimeDepartureStation);
        tableRowVehicleDeparture.addView(textViewDepartureStation);
        //row2 render
        tableRowVehicleArrival.addView(textViewArrivalTime);
        tableRowVehicleArrival.addView(spaceBetweenTimeArrivalStation);
        tableRowVehicleArrival.addView(textViewArrivalStation);
        tableRowVehicleArrival.addView(spaceBetweenArrivalStationDurationTime);
        tableRowVehicleArrival.addView(textViewDurationTime);
        //row3 render
       TextView textViewVehicleNumber=new TextView(this);
        StringBuilder stringBuilder= new StringBuilder();
        for (Vehicle vehicle:solution.getVehicles()){
            stringBuilder.append(vehicle.getNumeroTreno()+"   ");
        }
        String result=stringBuilder.toString();
        textViewVehicleNumber.setText(result);
        tableRowVehicleStatus.addView(textViewVehicleNumber);

        //
        tableLayoutVehicle.addView(tableRowVehicleDeparture);
        tableLayoutVehicle.addView(tableRowVehicleArrival);
        //tableLayoutVehicle.addView(tableRowVehicleStatus);
        tableRowSolution.addView(tableLayoutVehicle);
        tableLayoutPrincipal.addView(tableRowSolution);
    }

}
