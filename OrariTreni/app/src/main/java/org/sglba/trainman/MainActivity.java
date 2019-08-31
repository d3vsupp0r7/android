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
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.util.Log;
import android.widget.TimePicker;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.sglba.trainman.costraints.ApplicationCostraintsEnum;
import org.sglba.trainman.costraints.DatePatternFormatterCostraintEnum;
import org.sglba.trainman.costraints.TrainCategoryCostraintsEnum;
import org.sglba.trainman.model.RailRoute;
import org.sglba.trainman.model.Soluzioni;
import org.sglba.trainman.model.Station;
import org.sglba.trainman.model.TrainSolution;
import org.sglba.trainman.model.Vehicle;
import org.sglba.trainman.retrofitclient.NetworkStationClient;
import org.sglba.trainman.service.StationService;
import org.sglba.trainman.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
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
        // Initialize button with current date for RFI-14
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmtButton= DateTimeFormat.forPattern(DatePatternFormatterCostraintEnum.EU_DATE_PATTERN.getValue());
        calendarButton.setText("Oggi: " + fmtButton.print(now));
        //

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

        /**/
        ScrollView scrollViewLayout = findViewById(R.id.trainSolutionsScrollView);
        LinearLayout scrollViewLinearLayout = findViewById(R.id.linearLayoutScrollView);
        scrollViewLinearLayout.setOrientation(LinearLayout.VERTICAL);
        /**/
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
        scrollViewLinearLayout.removeAllViews();

        //Row iteration
        for(int i = 0; i < rows; i ++) {

            Soluzioni currentSolution = solutionsList.get(i);
            List<Vehicle> vehicleForSolution = currentSolution.getVehicles();
            Vehicle firstVehicle=vehicleForSolution.get(0);
            Vehicle lastVehicle=vehicleForSolution.get(vehicleForSolution.size()-1);

            TrainSolution trainSolution = new TrainSolution(currentSolution,firstVehicle,lastVehicle,vehicleForSolution);
            //Solution data
            //Veichle management
            //Define layout for row
            //
            TableRow tr1 = new TableRow(this);
            buildRowTrainDetails(i, tr1,trainSolution);
            //
            TableRow tr2 = new TableRow(this);
            buildRowTrainDetailsArrival(i, tr2,trainSolution);
            //
            TableRow tr3 = new TableRow(this);
            buildRowTrainDetailsSolutions(i, tr3,trainSolution);
            //
            final TableRow trSep = new TableRow(this);
            buildRowTrainDetailsSeparator(scrollViewLinearLayout, leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin, trSep);
            //

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TableRow btn = (TableRow)view;
                    //Log.d("ClickTableRow: " , btn.getTag().toString());
                    Log.d("ClickTableRow: " , ""+btn.getChildCount());
                   /* switch (btn.getId()){

                        case R.id.dateTymeButtonSample1:
                            *//*Build Intent object with sourceActivity(this) and targetActivity*//*
                            Intent intent = new Intent(DateTimeSampleIndexActivity.this, DateAndTimePickerActivity.class);
                            *//*Call target (second) activity*//*
                            startActivity(intent);
                            break;
                    }*/
                }
            };

            /**/
            tr1.setOnClickListener(listener);
            tr2.setOnClickListener(listener);
            tr3.setOnClickListener(listener);
            /**/
            scrollViewLinearLayout.addView(tr1);
            scrollViewLinearLayout.addView(tr2);
            scrollViewLinearLayout.addView(tr3);

        }

    }

    private void buildRowTrainDetailsSeparator(LinearLayout scrollViewLinearLayout, int leftRowMargin, int topRowMargin, int rightRowMargin, int bottomRowMargin, TableRow trSep) {
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
        scrollViewLinearLayout.addView(trSep);
    }

    /*****************/
    private void buildRowTrainDetails(int i, TableRow tr1, TrainSolution trainSolution) {
        //
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DatePatternFormatterCostraintEnum.US_DATE_PATTERN_WITH_TIME.getValue());
        DateTime dt = formatter.parseDateTime(trainSolution.getFirstVehicle().getOrarioPartenza());
        String dateTxtFormattedButtonUI = dt.toString(DatePatternFormatterCostraintEnum.EU_DATE_PATTERN_WITH_TIME_NO_T.getValue());
        String[] date1 = dateTxtFormattedButtonUI.split(" ");

        TextView rowText1 = new TextView(this);
        rowText1.setText(date1[1]);
        rowText1.setPadding(5, 15, 15, 15);
        rowText1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        rowText1.setTextColor(Color.parseColor("#ffffff"));
        rowText1.setTypeface(null, Typeface.BOLD_ITALIC);

        TextView rowText2 = new TextView(this);
        rowText2.setText(trainSolution.getFirstVehicle().getOrigine());
        rowText2.setPadding(5, 15, 15, 15);
        rowText2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        rowText2.setTextColor(Color.parseColor("#ffffff"));
        rowText2.setTypeface(null, Typeface.BOLD_ITALIC);

        //
        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        LL.addView(rowText1);
        LL.addView(rowText2);

        // LL.setTag(View.generateViewId(),"td_"+i);
        //
//        tr1.setTag(View.generateViewId(),"tr_td_"+i);

        tr1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        tr1.addView(LL);

    }

    private void buildRowTrainDetailsArrival(int i, TableRow tr2,  TrainSolution trainSolution) {
        //
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DatePatternFormatterCostraintEnum.US_DATE_PATTERN_WITH_TIME.getValue());
        DateTime dt = formatter.parseDateTime(trainSolution.getLastVehicle().getOrarioArrivo());
        String dateTxtFormattedButtonUI = dt.toString(DatePatternFormatterCostraintEnum.EU_DATE_PATTERN_WITH_TIME_NO_T.getValue());
        String[] date1 = dateTxtFormattedButtonUI.split(" ");
        //
        TextView rowText1 = new TextView(this);
        rowText1.setText(date1[1]);
        rowText1.setPadding(5, 15, 15, 15);
        rowText1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        rowText1.setTextColor(Color.parseColor("#ffffff"));
        rowText1.setTypeface(null, Typeface.BOLD_ITALIC);

        TextView rowText2 = new TextView(this);
        rowText2.setText(trainSolution.getLastVehicle().getDestinazione());
        rowText2.setPadding(5, 15, 15, 15);
        rowText2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        rowText2.setTextColor(Color.parseColor("#ffffff"));
        rowText2.setTypeface(null, Typeface.BOLD_ITALIC);
        //
        TextView rowTextProv = new TextView(this);
        rowTextProv.setPadding(150, 15, 15, 15);
        rowTextProv.setText("                   ");
        //

        Soluzioni currentSolution = trainSolution.getSolution();
        TextView rowText3 = new TextView(this);
        rowText3.setText(DateUtils.calculateDurationTime(trainSolution.getFirstVehicle().getOrarioPartenza(),trainSolution.getLastVehicle().getOrarioArrivo()));
        rowText3.setPadding(5, 15, 15, 15);
        rowText3.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        rowText3.setTextColor(Color.parseColor("#ffffff"));
        rowText3.setTypeface(null, Typeface.BOLD_ITALIC);

        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        LL.addView(rowText1);
        LL.addView(rowText2);
        LL.addView(rowTextProv);
        LL.addView(rowText3);
        LL.setTag(View.generateViewId(),"tda_"+i);

        tr2.addView(LL);
        //      tr2.setTag(View.generateViewId(),"tr_tda_"+i);
        tr2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
    }

    private void buildRowTrainDetailsSolutions(int i, TableRow tr2, TrainSolution trainSolution) {

        //
        //
        LinearLayout LL = new LinearLayout(this);
        LL.setTag(View.generateViewId(),"tds_"+i);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        //
        for(int j = 0; j < trainSolution.getVehicleForSolution().size();j++){

            Vehicle currentSolution = trainSolution.getVehicleForSolution().get(j);

            TextView rowText1 = new TextView(this);
            rowText1.setText(TrainCategoryCostraintsEnum.getEnumFromCode(currentSolution.getCategoriaDescrizione()).getDescription()+" "+currentSolution.getNumeroTreno());
            rowText1.setTextColor(Color.parseColor("#ffffff"));
            rowText1.setPadding(5, 15, 15, 15);
            //
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(0xFF90A4AE); // Changes this drawbale to use a single color instead of a gradient
            gd.setCornerRadius(5);
            gd.setStroke(1, 0xFF90A4AE);
            rowText1.setBackground(gd);
            //
            TextView rowText2 = new TextView(this);
            rowText2.setPadding(5, 15, 15, 15);
            LL.addView(rowText1);
            LL.addView(rowText2);
        }

        tr2.addView(LL);
        tr2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));

    }

}
