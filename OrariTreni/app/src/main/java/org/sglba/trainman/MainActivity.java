package org.sglba.trainman;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.util.Log;
import android.widget.TimePicker;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.sglba.trainman.costraints.ApplicationCostraintsEnum;
import org.sglba.trainman.costraints.DatePatternFormatterCostraintEnum;
import org.sglba.trainman.costraints.TrainCategoryCostraintsEnum;
import org.sglba.trainman.model.RailRoute;
import org.sglba.trainman.model.Soluzioni;
import org.sglba.trainman.model.Station;
import org.sglba.trainman.model.Vehicle;
import org.sglba.trainman.retrofitclient.NetworkStationClient;
import org.sglba.trainman.service.StationService;
import org.sglba.trainman.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //Autocompletes
    AutoCompleteTextView autoCompleteDepartures;
    AutoCompleteTextView autoCompleteArrivals;
    //Adapters
    ArrayAdapter<String> adapterForDeparturesAndArrival;
    //Layouts
    TableLayout trainSolutionsTableLayout;
    //Data
    Map<String, String> stationMapFilteredForDepartures = new HashMap<>();
    Map<String, String> stationMapFilteredForArrivals = new HashMap<>();
    HashSet<Station> stationList=new HashSet<>();
    //Boolean conditions
    Boolean isCalendarButtonPressed=true;
    Boolean isAPIArrivalsAndDeparturesCallPerformed=false;
    Boolean isSwitchPressed=false;
    //To Add on Date Utils
    String   selectedDate;
    int yearToSet;
    int monthToSet;
    int dayToSet;

    //Popup
    ConstraintLayout mConstraintLayout;
    Context mContext;
    //DateTimePicker
    TimePicker timePicker;
    Button calendarButton;

    //Buttons
    ImageButton swapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.coordinatorLayout2);
        mContext = this;
        /* UI Components */
        //Buttons reference from UI
        ImageButton findButton = findViewById(R.id.findButton);
        calendarButton= findViewById(R.id.calendarButton);
        swapButton =  findViewById(R.id.swapButton);
        //Autocomplete configurations
        autoCompleteArrivals = findViewById(R.id.autoCompleteArrivals);
        autoCompleteDepartures = findViewById(R.id.autoCompleteDepartures);
        autoCompleteDepartures.setThreshold(2);
        autoCompleteArrivals.setThreshold(2);
        //

        //trainSolutions - TableLayout confifuration
        trainSolutionsTableLayout = findViewById(R.id.trainSolutionsTableLayout);
        trainSolutionsTableLayout.setStretchAllColumns(true);
        trainSolutionsTableLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        //Performs API call to retrieve all the stations for each region
        if(!isAPIArrivalsAndDeparturesCallPerformed&&stationList.isEmpty()) {
            getStationByRegionForDeparturesAndArrival();
        }

        autoCompleteDepartures.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "autoCompleteDepartures.addTextChangedListener - executed");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!isSwitchPressed) {
                    autocomplete(s.toString(), 0);
                }
                else
                {
                    isSwitchPressed=true;
                }
                Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "autoCompleteDepartures.onTextChanged - executed");
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
                if(!isSwitchPressed) {
                    autocomplete(s.toString(), 1);
                }
                else
                {
                    isSwitchPressed=true;
                }
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
                //To hide the KeyBoard
                hideKeyBoard();
                //To call the API service
                if (autoCompleteDepartures.getText() != null & !autoCompleteDepartures.getText().toString().isEmpty()
                        && autoCompleteArrivals.getText() != null & !autoCompleteArrivals.getText().toString().isEmpty()) {
                    String departureStationCode = stationMapFilteredForDepartures.get(autoCompleteDepartures.getText().toString()).replace("S0", "");
                    String arrivalStationCode = stationMapFilteredForArrivals.get(autoCompleteArrivals.getText().toString()).replace("S0", "");
                    getTrainByStations(departureStationCode, arrivalStationCode,selectedDate!=null?selectedDate:DateUtils.formatCurrentDateForAPIService());
                }
            }
        });
        /*swapButton : OnClickListener*/
        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                String tmpDeparture = autoCompleteDepartures.getText().toString();
                String tmpArrivals = autoCompleteArrivals.getText().toString();
                //
                String departureStationCode = stationMapFilteredForDepartures.get(tmpDeparture);
                String arrivalStationCode = stationMapFilteredForArrivals.get(tmpArrivals);
                //
                stationMapFilteredForDepartures.put(tmpArrivals,arrivalStationCode);
                stationMapFilteredForArrivals.put(tmpDeparture,departureStationCode);
                //
                isSwitchPressed=true;
                autoCompleteDepartures.setText(tmpArrivals);
                isSwitchPressed=true;
                autoCompleteArrivals.setText(tmpDeparture);
                //

            }
        });

        /*calendarButton : OnClickListener*/
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectDateTime(v);
            }

        });

    }

    private void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void selectDateTime(View v) {
        final View dialogView = View.inflate(this, R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        //To set 24H on timePicker
        timePicker = (TimePicker) dialogView.findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        dialogView.findViewById(R.id.datetimeset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datepicker);
                Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getHour(),
                        timePicker.getMinute());
                /*Date management with JodaTime*/
                TimeZone tz = calendar.getTimeZone();
                DateTimeZone jodaTz = DateTimeZone.forID(tz.getID());
                DateTime dateTime = new DateTime(calendar.getTimeInMillis(), jodaTz);
                String dateFormatFromPickers = dateTime.toString(DatePatternFormatterCostraintEnum.US_DATE_PATTERN_WITH_TIME.getValue());
                String dateFormatForAPICall = dateFormatFromPickers.replace(" ","T");
                /*UI Settings*/
                String dateTxtFormattedButtonUI = dateTime.toString(DatePatternFormatterCostraintEnum.EU_DATE_PATTERN_WITH_TIME_NO_T.getValue());
                /*Date management with UI/Service components*/
                calendarButton.setText(dateTxtFormattedButtonUI);
                selectedDate = dateFormatForAPICall;
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "calendarButton.onClick - selectedDate: " + selectedDate);
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }

    private void getStationByRegionForDeparturesAndArrival() {
        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = NetworkStationClient.getRetrofitClient();
        /*
        The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
        */
        StationService stationService = retrofit.create(StationService.class);
        /*
        Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
        */
        for (int z=0;z<23;z++) {
            Call call = stationService.getStationByRegion(String.valueOf(z));
        /*
        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        */
            call.enqueue(new Callback<List<Station>>() {
                @Override
                public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                    /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                     */
                    Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDeparturesAndArrival - started");
                    Map<String, String> stationMapFiltered = new HashMap<>();
                    List<String> stationNamesList = new ArrayList<>();

                    if (response.body() != null) {

                        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDeparturesAndArrival - response processing");

                        List<Station> station = response.body();
                        stationMapFiltered.clear();
                        stationNamesList.clear();
                        stationList.addAll(station);
                        stationMapFilteredForDepartures.putAll(stationMapFiltered);
                        stationMapFilteredForArrivals.putAll(stationMapFiltered);
                        isAPIArrivalsAndDeparturesCallPerformed = true;

                    } else {
                        //TODO: Manage application exception on comunication error
                        isAPIArrivalsAndDeparturesCallPerformed = false;
                        Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDeparturesAndArrival - failed");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    //TODO: Manage application exception on comunication error
                    isAPIArrivalsAndDeparturesCallPerformed = false;
                    Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getStationByRegionForDeparturesAndArrival  - onFailure");
                }
            });

        }
    }

    private void autocomplete(String charSequence,int checkDeparturesOrArrivals){
        Map<String, String> stationMapFiltered = new HashMap<>();
        List<String> stationNamesList = new ArrayList<>();
        for (Station singleStation:stationList){
            if (singleStation.getLocalita().getNomeLungo().toLowerCase().startsWith(charSequence.toLowerCase())){
                stationMapFiltered.put(singleStation.getLocalita().getNomeLungo(),singleStation.getCodStazione());
                stationNamesList.add(singleStation.getLocalita().getNomeLungo());
            }
        }
        Collections.sort(stationNamesList);
        adapterForDeparturesAndArrival = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, stationNamesList);
        if(checkDeparturesOrArrivals==0) {
            autoCompleteDepartures.setAdapter(adapterForDeparturesAndArrival);
            stationMapFilteredForDepartures.putAll(stationMapFiltered);
        }
        else if(checkDeparturesOrArrivals==1) {
            autoCompleteArrivals.setAdapter(adapterForDeparturesAndArrival);
            stationMapFilteredForArrivals.putAll(stationMapFiltered);
        }
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
        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "departureStation: " + departureStation
                        + "arrivalStation: " + arrivalStation +
                        "date: " + date);
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
        int textSize = 10, smallTextSize =0, mediumTextSize = 0;

        textSize = (int) getResources().getDimension(R.dimen.font_size_medium);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

        int rows = solutionsList.size();
        TextView textSpacer = null;
        trainSolutionsTableLayout.removeAllViews();

        //Row iteration
        for(int i = 0; i < rows; i ++) {

            Soluzioni currentSolution = solutionsList.get(i);
            List<Vehicle> vehicleForSolution = currentSolution.getVehicles();
            Vehicle firstVehicle=vehicleForSolution.get(0);
            Vehicle lastVehicle=vehicleForSolution.get(vehicleForSolution.size()-1);


            //Solution data
            String duration = currentSolution.getDurata();
            //Veichle management
            //Define layout for row

            final TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f));
            tv.setPadding(5, 0, 1, 5);
            tv.setGravity(Gravity.LEFT);
            tv.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setText(DateUtils.formatDate(firstVehicle.getOrarioPartenza())+"   "+firstVehicle.getOrigine());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            tv.setTypeface(null, Typeface.BOLD_ITALIC);
            TableRow tr = new TableRow(this);
            //////
            final TextView tv2 = new TextView(this);
            tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,0.7f));
            tv2.setPadding(5, 0, 1, 5);
            tv2.setGravity(Gravity.LEFT);
            tv2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
            tv2.setTextColor(Color.parseColor("#ffffff"));
            tv2.setText(DateUtils.formatDate(lastVehicle.getOrarioArrivo())+"   "+lastVehicle.getDestinazione());
            tv2.setTypeface(null, Typeface.BOLD_ITALIC);
            tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            //////
            final TextView tvDur = new TextView(this);
            tvDur.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,0.3f));
            tvDur.setPadding(5, 0, 20, 5);
            tvDur.setGravity(Gravity.RIGHT);
            tvDur.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
            tvDur.setTextColor(Color.parseColor("#ffffff"));
            tvDur.setText(currentSolution.getDurata()!=null?currentSolution.getDurata():DateUtils.calculateDurationTime(firstVehicle.getOrarioPartenza(),lastVehicle.getOrarioArrivo()));
            tvDur.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            tvDur.setTypeface(null, Typeface.BOLD_ITALIC);
            TableRow tr2 = new TableRow(this);
            tr2.setWeightSum(1f);
            //////
            TableRow tr3 = new TableRow(this);
            TableLayout.LayoutParams tr3Params =createTableParams(tr3,leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr3.setLayoutParams(tr3Params);

            for (Vehicle vehicle:vehicleForSolution) {
                final TextView tv3 = new TextView(this);
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv3.setPadding(0, 0, -30, 5);
                tv3.setGravity(Gravity.LEFT);
                tv3.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
                tv3.setTextColor(Color.parseColor("#ffffff"));
                tv3.setText(TrainCategoryCostraintsEnum.getEnumFromCode(vehicle.getCategoriaDescrizione()).getDescription()+" "+vehicle.getNumeroTreno());
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv3.setTypeface(null, Typeface.BOLD_ITALIC);
                //
                GradientDrawable gd = new GradientDrawable();
                gd.setColor(0xFF90A4AE); // Changes this drawbale to use a single color instead of a gradient
                gd.setCornerRadius(5);
                gd.setStroke(1, 0xFF90A4AE);
                //
                tv3.setBackground(gd);
                tr3.addView(tv3,vehicleForSolution.indexOf(vehicle));
            }
            //
            TableLayout.LayoutParams tr1Params =createTableParams(tr,leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setLayoutParams(tr1Params);
            tr.addView(tv);
            TableLayout.LayoutParams tr2Params =createTableParams(tr2,leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr2.setLayoutParams(tr2Params);
            tr2.addView(tv2);
            tr2.addView(tvDur);
            tr.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
            trainSolutionsTableLayout.addView(tr, tr1Params);
            trainSolutionsTableLayout.addView(tr2, tr2Params);
            trainSolutionsTableLayout.addView(tr3, tr3Params);
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

    private TableLayout.LayoutParams createTableParams(TableRow tr,int leftRowMargin,
                                                       int topRowMargin,
                                                       int rightRowMargin,
                                                       int bottomRowMargin){
        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT);
        trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
        tr.setLayoutParams(trParams);
        return trParams;
    }

}
