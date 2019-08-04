package org.sglba.trainman;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

    //Autocompletes
    AutoCompleteTextView autoCompleteDepartures;
    AutoCompleteTextView autoCompleteArrivals;
    //Adapters
    ArrayAdapter<String> adapterForDepartures;
    ArrayAdapter<String> adapterForArrivals;
    //Layouts
    TableLayout trainSolutionsTableLayout;
    //Data
    Map<String, String> stationMapFilteredForDepartures = new HashMap<>();
    Map<String, String> stationMapFilteredForArrivals = new HashMap<>();

    Boolean isCalendarButtonPressed=true;

    //To Add on Date Utils
    String   selectedDate;
    int yearToSet;
    int monthToSet;
    int dayToSet;

    //Popup
    PopupWindow mPopupWindow;
    ConstraintLayout mConstraintLayout;
    Context mContext;

    //Buttons
    ImageButton swapButton;
    //TextView
    TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.coordinatorLayout2);
        mContext = this;
        /* UI Components */
        //Buttons reference from UI
        ImageButton findButton = findViewById(R.id.findButton);
        ImageButton calendarButton= findViewById(R.id.calendarButton);
        swapButton =  findViewById(R.id.swapButton);
        //Autocomplete configurations
        autoCompleteArrivals = findViewById(R.id.autoCompleteArrivals);
        autoCompleteDepartures = findViewById(R.id.autoCompleteDepartures);
        autoCompleteDepartures.setThreshold(2);
        autoCompleteArrivals.setThreshold(2);
        //
        dateTextView = findViewById(R.id.dateTextView);

        //trainSolutions - TableLayout confifuration
        trainSolutionsTableLayout = findViewById(R.id.trainSolutionsTableLayout);
        trainSolutionsTableLayout.setStretchAllColumns(true);

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
        /*findButton  : OnClickListener*/
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCompleteDepartures.getText() != null & !autoCompleteDepartures.getText().toString().isEmpty()
                        && autoCompleteArrivals.getText() != null & !autoCompleteArrivals.getText().toString().isEmpty()) {
                    String departureStationCode = stationMapFilteredForDepartures.get(autoCompleteDepartures.getText().toString()).replace("S0", "");
                    String arrivalStationCode = stationMapFilteredForArrivals.get(autoCompleteArrivals.getText().toString()).replace("S0", "");
                    getTrainByStations(departureStationCode, arrivalStationCode,selectedDate!=null?selectedDate:"");
                }
            }
        });
        /*swapButton : OnClickListener*/
        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmpDeparture = autoCompleteDepartures.getText().toString();
                String tmpArrivals = autoCompleteArrivals.getText().toString();
                autoCompleteDepartures.setText(tmpArrivals);
                autoCompleteArrivals.setText(tmpDeparture);

            }
        });

        /*calendarButton : OnClickListener*/
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
                                                    = year + "-" + sMonth + "-" + sDay;
                                            Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "calendarButton.onClick - selectedDate: " + selectedDate);
                                            selectedDate = date;
                                            dateTextView.setText(DateUtils.formatDateToUE(selectedDate));
                                            trainSolutionsTableLayout.removeView(calendarView);
                                            isCalendarButtonPressed=true;
                                        }
                                    });
                    trainSolutionsTableLayout.addView(calendarView,0);


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
                    createSolutionsLayoutTable(solutionsList);
                    /* for (Soluzioni singleSolution : solutionsList) {
                        createSolutionsLayoutTable(singleSolution);
                        }
                    */
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

    private void createSolutionsLayoutTable(  List<Soluzioni> solutionsList) {

        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize =0, mediumTextSize = 0;

        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

        int rows = solutionsList.size();
        TextView textSpacer = null;
        trainSolutionsTableLayout.removeAllViews();

        //Row iteration
        for(int i = 0; i < rows; i ++) {

            Soluzioni currentSolution = solutionsList.get(i);
            List<Vehicle> vehicleForSolution = currentSolution.getVehicles();
            //Solution data
            String duration = currentSolution.getDurata();
            //Veichle management
            for (Vehicle currentVeichle: vehicleForSolution) {
                //Define layout for row
                final LinearLayout layAmounts = new LinearLayout(this);
                layAmounts.setOrientation(LinearLayout.VERTICAL);
                layAmounts.setGravity(Gravity.RIGHT);
                layAmounts.setPadding(0, 10, 0, 10);
                layAmounts.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                layAmounts.setBackgroundColor(Color.parseColor("#ffffff"));
                //
                final TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv.setPadding(5, 0, 1, 5);
                tv.setGravity(Gravity.RIGHT);
                tv.setBackgroundColor(Color.parseColor("#ffffff"));
                tv.setTextColor(Color.parseColor("#000000"));
                tv.setText(DateUtils.formatDate(currentVeichle.getOrarioPartenza()));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                layAmounts.addView(tv);
                //////
                final TextView tv2 = new TextView(this);
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setPadding(5, 0, 1, 5);
                tv2.setGravity(Gravity.RIGHT);
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setText(currentVeichle.getOrigine());
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                layAmounts.addView(tv2);
                //////
                //
                final TableRow tr = new TableRow(this);
                TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);

                trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                tr.setPadding(0,0,0,0);
                tr.setLayoutParams(trParams);
                //Listener on rowclick
                tr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        TableRow tr = (TableRow) v;
                        //do whatever action is needed
                        //Obtain layout for popup
                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                        View customView = inflater.inflate(R.layout.simple_popup_layout,null);
                        // Define background color for layout
                        Drawable d = new ColorDrawable(Color.BLUE);
                        //
                        LinearLayout l = (LinearLayout)tr.getChildAt(0);
                        TextView rowDetail = (TextView)l.getChildAt(0);

                        String result = (String)rowDetail.getText();
                        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "Value on rowclick: " + result);

                        mPopupWindow = new PopupWindow(
                                customView,
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );

                        // Get a reference for the custom view close button
                        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
                        closeButton.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                // Dismiss the popup window
                                mPopupWindow.dismiss();
                            }
                        });

                        mPopupWindow.setBackgroundDrawable(d);
                        //Center popup
                        mPopupWindow.showAtLocation(mConstraintLayout, Gravity.CENTER,0,0);

                    }
                });
                tr.addView(layAmounts);
                trainSolutionsTableLayout.addView(tr, trParams);
                /*Separator*/
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                tvSepLay.span = 4;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(1);
                trSep.addView(tvSep);
                trainSolutionsTableLayout.addView(trSep, trParamsSep);
            }

        }

    }

}
