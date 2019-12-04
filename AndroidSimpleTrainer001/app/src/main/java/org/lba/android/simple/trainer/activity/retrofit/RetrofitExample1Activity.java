package org.lba.android.simple.trainer.activity.retrofit;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.RetrofitSampleIndexActivity;
import org.lba.android.simple.trainer.activity.util.PropertyReader;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitExample1Activity extends AppCompatActivity {


    private String TAG = RetrofitExample1Activity.class.getSimpleName();
    Button toMainActivityButton;
    Button backToIndexSampleButton;
    Button btnExecute;
    //Text
    EditText txtUrl;
    EditText txtParam1;
    EditText txtParam2;
    //
    Spinner urlSpinnerRef;
    String selectedDataFromSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_example1);

        Resources resources = this.getResources();
        AssetManager assetManager = resources.getAssets();

        urlSpinnerRef = (Spinner) findViewById(R.id.urlSpinner);
        /*Build spinner from properties*/
        final List<String> spinnerString = new ArrayList<>();
        spinnerString.add("None selected");

        /**/

        try {
            String sample = PropertyReader.getProperty("example1",getApplicationContext());
            Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** RetrofitExample1Activity - read property: " +  sample + " **");
            /*SpinnerPopulation*/
            String urlFromProperties = PropertyReader.getProperty("rest-api-endpoint",getApplicationContext());
            Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** RetrofitExample1Activity - read property: " +  sample + " **");
            spinnerString.add(urlFromProperties);
            /**/
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                 this,
                    android.R.layout.simple_spinner_item,
                    spinnerString
            );
            urlSpinnerRef.setAdapter(adapter);

            urlSpinnerRef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {

                    String items=urlSpinnerRef.getSelectedItem().toString();
                    selectedDataFromSpinner = urlSpinnerRef.getSelectedItem().toString();
                    Log.i("Selected item : ",items);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }

            });

        } catch (IOException e) {
            Log.e(ApplicationCostraintsEnum.APP_NAME.getValue(),"** RetrofitExample1Activity - read property exception: " +  e.getMessage() + " **");
        }
        /**/
        toMainActivityButton = (Button)findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** RetrofitExample1Activity goto MainActivity - started **");
                Intent intent= new Intent(RetrofitExample1Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button)findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** RetrofitExample1Activity goto RetrofitSampleIndexActivity - started **");
                Intent intent= new Intent(RetrofitExample1Activity.this, RetrofitSampleIndexActivity.class);
                startActivity(intent);
            }
        });
        /**/
        //Text init
        txtUrl =findViewById(R.id.txtUrl);
        txtParam1 = findViewById(R.id.txtParam1);
        txtParam2 = findViewById(R.id.txtParam2);

        btnExecute = (Button)findViewById(R.id.btnExecute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** RetrofitExample1Activity - btnExecute - started **");
                //1: Read data
                String url = txtUrl.getText().toString();
                String urlFromSpinner = selectedDataFromSpinner;
                String param1 = txtParam1.getText().toString();
                String param2 = txtParam2.getText().toString();
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"**URL: " + url +
                        "**URL From spinner: " + urlFromSpinner
                        + " param1: " + param1 + " - param2: " + param2);
                //Invocation
                /*Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();*/

            }
        });
    }
}
