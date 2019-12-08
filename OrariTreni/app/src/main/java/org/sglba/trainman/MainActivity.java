package org.sglba.trainman;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.sglba.trainman.costraints.ApplicationCostraintsEnum;
import org.sglba.trainman.costraints.DatePatternFormatterCostraintEnum;
import org.sglba.trainman.costraints.TrainCategoryCostraintsEnum;
import org.sglba.trainman.db.model.StationEntityRoom;
import org.sglba.trainman.db.room.config.AppDatabase;
import org.sglba.trainman.db.room.config.RoomDatabaseClientConfig;
import org.sglba.trainman.model.RailRoute;
import org.sglba.trainman.model.Soluzioni;
import org.sglba.trainman.model.Station;
import org.sglba.trainman.model.TrainSolution;
import org.sglba.trainman.model.TrainStatus;
import org.sglba.trainman.model.Vehicle;
import org.sglba.trainman.retrofitclient.NetworkStationClient;
import org.sglba.trainman.service.StationService;
import org.sglba.trainman.util.DateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
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
    //Data
    Map<String, String> stationMapFilteredForDepartures = new HashMap<>();
    Map<String, String> stationMapFilteredForArrivals = new HashMap<>();
    HashSet<Station> stationList=new HashSet<>();
    //Boolean conditions
    Boolean isSwitchPressed=false;
    //To Add on Date Utils
    String   selectedDate;

    List<String> selectedTrainOrigin=new ArrayList<>();
    TrainStatus selectedTrainStatus;

    //Popup
    ConstraintLayout mConstraintLayout;
    Context mContext;
    //DateTimePicker
    TimePicker timePicker;
    Button calendarButton;
    ProgressBar progressBar;

    //Buttons
    ImageButton swapButton;

    /*DB*/
    AppDatabase appDatabase;
    HashSet<StationEntityRoom> stationListApp= new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.coordinatorLayout2);
        mContext = this;
        /*Get Data*/
        appDatabase = RoomDatabaseClientConfig.getInstance(getApplicationContext()).getAppDatabase();
        stationListApp.addAll(appDatabase.stationDao().getAll());
        Log.i(ApplicationCostraintsEnum.APP_NAME.getValue(), "List of stations: " + stationList.size());

        /* UI Components */
        //Buttons reference from UI
        progressBar=findViewById(R.id.progressBarSubmit);
        progressBar.setVisibility(View.INVISIBLE);
        ImageButton findButton = findViewById(R.id.findButton);
        Button circumTimeTablesButton=findViewById(R.id.circumTimeTablesButton);
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

        circumTimeTablesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=24){
                    try{
                        Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                        m.invoke(null);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},102);
                }else {
                    try {
                        openPDFFiles();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

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
                progressBar.setVisibility(View.VISIBLE);
                //To hide the KeyBoard
                hideKeyBoard();
                //To call the API service
                if (autoCompleteDepartures.getText() != null & !autoCompleteDepartures.getText().toString().isEmpty()
                        && autoCompleteArrivals.getText() != null & !autoCompleteArrivals.getText().toString().isEmpty()) {
                    String departureStationCode = stationMapFilteredForDepartures.containsKey(autoCompleteDepartures.getText().toString())?stationMapFilteredForDepartures.get(autoCompleteDepartures.getText().toString()).replace("S0", "").replace("S",""):"";
                    String arrivalStationCode = stationMapFilteredForArrivals.containsKey(autoCompleteArrivals.getText().toString())?stationMapFilteredForArrivals.get(autoCompleteArrivals.getText().toString()).replace("S0", "").replace("S",""):"";
                    if (!departureStationCode.isEmpty()&&!arrivalStationCode.isEmpty()){
                        getTrainByStations(departureStationCode, arrivalStationCode,selectedDate!=null?selectedDate:DateUtils.formatCurrentDateForAPIService());
                    }else{
                        Toast.makeText(MainActivity.this, "Nome Stazione non supportato!",
                                Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
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

    private void autocomplete(String charSequence,int checkDeparturesOrArrivals){
        Map<String, String> stationMapFiltered = new HashMap<>();
        List<String> stationNamesList = new ArrayList<>();
        for (StationEntityRoom singleStation:stationListApp){
            if (singleStation.getStationName().toLowerCase().startsWith(charSequence.toLowerCase())){
                stationMapFiltered.put(singleStation.getStationName(),singleStation.getStationId());
                stationNamesList.add(singleStation.getStationName());
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
                    progressBar.setVisibility(View.GONE);

                } else {
                    //TODO: Manage application exception on comunication error
                    Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getTravelSolutionsFromStations - failed");
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //TODO: Manage application exception on comunication error
                Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(), "SERVICE CALL: getTravelSolutionsFromStations  - onFailure");
            }
        });
    }

    private void getTrainOriginForGetStatusAPI(String trainNumber){
        Retrofit retrofit = NetworkStationClient.getRetrofitClient();

        StationService stationService = retrofit.create(StationService.class);

        Call<String> call = stationService.getTrainOriginForGetStatusAPI(trainNumber);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(),"TrainOrigin API invocation succesfully");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(),"TrainOrigin API invocation failed");
            }
        });

    }

    private void getTrainStatus(String departureStation,String trainNumber){
        Retrofit retrofit = NetworkStationClient.getRetrofitClient();

        StationService stationService = retrofit.create(StationService.class);

        Call call = stationService.getTrainStatus(departureStation,trainNumber);

        call.enqueue(new Callback<TrainStatus>() {
            @Override
            public void onResponse(Call<TrainStatus> call, Response<TrainStatus> response) {
                Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(),"TrainStatus API invoked succesfully");
                selectedTrainStatus=response.body();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(),"TrainStatus API invocation failed");
            }
        });

    }


    private void createSolutionsLayoutTable(  List<Soluzioni> solutionsList) {

        /**/
        LinearLayout scrollViewLinearLayout = findViewById(R.id.linearLayoutScrollView);
        scrollViewLinearLayout.setOrientation(LinearLayout.VERTICAL);
        /**/
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;

        int rows = solutionsList.size();
        scrollViewLinearLayout.removeAllViews();

        //Row iteration
        for(int i = 0; i < rows; i ++) {
            if (i==20){
                break;
            }
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
            buildRowTrainDetails(tr1,trainSolution);
            //
            TableRow tr2 = new TableRow(this);
            buildRowTrainDetailsArrival(tr2,trainSolution);
            //
            TableRow tr3 = new TableRow(this);
            buildRowTrainDuration(tr3,trainSolution);
            //
            TableRow tr4 = new TableRow(this);
            buildRowTrainDetailsSolutions(tr4,trainSolution);
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
            tr4.setOnClickListener(listener);
            /**/
            scrollViewLinearLayout.addView(tr1);
            scrollViewLinearLayout.addView(tr2);
            scrollViewLinearLayout.addView(tr3);
            scrollViewLinearLayout.addView(tr4);

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
    private void buildRowTrainDetails(TableRow tr1, TrainSolution trainSolution) {
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

        tr1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        tr1.addView(LL);

    }

    private void buildRowTrainDetailsArrival(TableRow tr2,  TrainSolution trainSolution) {
        //
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DatePatternFormatterCostraintEnum.US_DATE_PATTERN_WITH_TIME.getValue());
        DateTime dt = formatter.parseDateTime(trainSolution.getLastVehicle().getOrarioArrivo());
        String dateTxtFormattedButtonUI = dt.toString(DatePatternFormatterCostraintEnum.EU_DATE_PATTERN_WITH_TIME_NO_T.getValue());
        String[] date1 = dateTxtFormattedButtonUI.split(" ");
        //
        TextView rowText1 = new TextView(this);
        rowText1.setText(date1[1]);
        rowText1.setPadding(5, 5, 15, 5);
        rowText1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        rowText1.setTextColor(Color.parseColor("#ffffff"));
        rowText1.setTypeface(null, Typeface.BOLD_ITALIC);

        TextView rowText2 = new TextView(this);
        rowText2.setText(trainSolution.getLastVehicle().getDestinazione());
        rowText2.setPadding(5, 5, 15, 5);
        rowText2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        rowText2.setTextColor(Color.parseColor("#ffffff"));
        rowText2.setTypeface(null, Typeface.BOLD_ITALIC);
        //

        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        LL.addView(rowText1);
        LL.addView(rowText2);

        tr2.addView(LL);
        tr2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
    }

    private void buildRowTrainDuration(TableRow tr1, TrainSolution trainSolution) {
        //
        TextView rowText1 = new TextView(this);
        rowText1.setText("Durata: "+DateUtils.calculateDurationTime(trainSolution.getFirstVehicle().getOrarioPartenza(),trainSolution.getLastVehicle().getOrarioArrivo()));
        rowText1.setPadding(5, 5, 15, 5);
        rowText1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        rowText1.setTextColor(Color.parseColor("#ffffff"));
        rowText1.setTypeface(null, Typeface.BOLD_ITALIC);

        //
        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        LL.addView(rowText1);

        tr1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));
        tr1.addView(LL);

    }

    private void buildRowTrainDetailsSolutions(TableRow tr2, TrainSolution trainSolution) {

        //
        //
        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        //
        for(int j = 0; j < trainSolution.getVehicleForSolution().size();j++){

            Vehicle currentSolution = trainSolution.getVehicleForSolution().get(j);

            TextView rowText1 = new TextView(this);
            rowText1.setText(TrainCategoryCostraintsEnum.getEnumFromCode(currentSolution.getCategoriaDescrizione()).getDescription()+" "+currentSolution.getNumeroTreno());
            rowText1.setTextColor(Color.parseColor("#ffffff"));
            rowText1.setPadding(5, 15, 15, 15);
            //
            Integer delay=selectedTrainStatus!=null&&selectedTrainStatus.getRitardo()!=null?selectedTrainStatus.getRitardo():null;
            //
            GradientDrawable gd = new GradientDrawable();
            if (delay==null) {
                gd.setColor(0xFF90A4AE); // Changes this drawbale to use a single color instead of a gradient
                gd.setCornerRadius(5);
                gd.setStroke(1, 0xFF90A4AE);
            }else{
                gd.setColor(delay<=0?0xFF85E085:0xFFFF6666); // Changes this drawbale to use a single color instead of a gradient
                gd.setCornerRadius(5);
                gd.setStroke(1, 0xFF90A4AE);
            }
            rowText1.setBackground(gd);
            //
            TextView rowText2 = new TextView(this);
            rowText2.setPadding(5, 15, 15, 15);
            LL.addView(rowText1);
            LL.addView(rowText2);
            selectedTrainStatus=null;
            selectedTrainOrigin=null;
        }

        tr2.addView(LL);
        tr2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryLight));

    }

    private void openPDFFiles() {
        File fPath=new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/pdf");
        if (!fPath.exists()){
            fPath.mkdirs();
        }
        File f = new File(fPath,"NapoliSorrento.pdf");

        if (!f.exists()) {
            AssetManager assets=getAssets();
            try {
                copy(assets.open("NapoliSorrento.pdf"), f);
            }
            catch (IOException e) {
                Log.e("FileProvider", "Exception copying from assets", e);
            }
        }

        Intent i=
                new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(f),"application/pdf");

        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(i);
    }

    static private void copy(InputStream in, File dst) throws IOException {
        FileOutputStream out=new FileOutputStream(dst);
        byte[] buf=new byte[1024];
        int len;

        while ((len=in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }

}
