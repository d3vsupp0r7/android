package org.lba.android.simple.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import org.lba.android.simple.trainer.activity.ArchitectureSampleIndexActivity;
import org.lba.android.simple.trainer.activity.AsyncTaskSampleIndexActivity;
import org.lba.android.simple.trainer.activity.BasicUIIndexActivity;
import org.lba.android.simple.trainer.activity.DatabaseSampleIndexActivity;
import org.lba.android.simple.trainer.activity.DateTimeSampleIndexActivity;
import org.lba.android.simple.trainer.activity.DynamicTableSampleIndexActivity;
import org.lba.android.simple.trainer.activity.RetrofitSampleIndexActivity;
import org.lba.android.simple.trainer.activity.fragment.ActivityForFragmentHelloWorld;
import org.lba.android.simple.trainer.activity.masterdetail.items.ItemListActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;
import org.lba.android.simple.trainer.db.dao.SettingsDAO;
import org.lba.android.simple.trainer.db.model.SettingsModel;
import org.lba.android.simple.trainer.model.Employee;

import java.util.ArrayList;
import java.util.List;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    PodamFactory factory;
    List<Employee> myEmployeeList;
    //
    Button nextActivityButton;
    Button dynamicTableActivityButton;
    Button toMDActivity;
    Button toDatePickerButton;
    Button toDBActivity;
    Button toFragmentExample;
    Button toAsyncActivity;
    Button toRetrofitActivity;
    Button toBasicUi;
    Button toArchitectureActivity;
    Button toAboutActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** MainActivity - started **");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** DBWriting example **");
        /* Load Existing Settings*/
        SettingsDAO settingsDAO = new SettingsDAO(this);
        List<SettingsModel> modelOnDb = settingsDAO.readAllSettings();
        if(modelOnDb!= null && modelOnDb.size() > 0) {
            Log.d(ApplicationCostraintsEnum.DB.getValue(), "There are existing settings: " + modelOnDb.size());
            for (SettingsModel currentModel : modelOnDb) {
                Log.d(ApplicationCostraintsEnum.DB.getValue(), "Setting: " + currentModel.toString());
            }
        }else{
            Log.d(ApplicationCostraintsEnum.DB.getValue(), "Writing an example setting on db");
            //Create a sample record for settings
            SettingsModel model = new SettingsModel();
            model.setColumn1("Saving");
            model.setColumn2("Model");
            model.setColumn3(3l);
            Long savedSettingId = settingsDAO.createSettingsRecord(model);
            Log.d(ApplicationCostraintsEnum.DB.getValue(), "Setting inserted sucessfully with id: " + savedSettingId);
        }

        /*NB: Example of PODAM Library to build Employee demo data*/
        myEmployeeList = new ArrayList<>();
        factory = new PodamFactoryImpl();

        for(int i = 0; i < 10; i++){
            Employee employeeElement = factory.manufacturePojoWithFullData(Employee.class);
            myEmployeeList.add(employeeElement);
        }

        /** START - Navigation section **/
        /*Activity navigation*/
        nextActivityButton =(Button)findViewById(R.id.nextActivity);
        nextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** MainActivity - goto second activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                /* Sample data to pass to second activity*/
                Employee employeeDataBetweenActivity = factory.manufacturePojoWithFullData(Employee.class);
                intent.putExtra("employeeShared",employeeDataBetweenActivity);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });

        /*Dynamic table*/
        dynamicTableActivityButton  =(Button)findViewById(R.id.dynamicTableActivityButton);
        dynamicTableActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto DynamicTableSampleIndexActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, DynamicTableSampleIndexActivity.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
            });

        /**/
        toMDActivity  =(Button)findViewById(R.id.toMDActivity);
        toMDActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto toMDActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });
        /**/
        toDatePickerButton =(Button)findViewById(R.id.toDatePickerButton);
        toDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto DateTimeSampleIndexActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, DateTimeSampleIndexActivity.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });
        /**/
        toDBActivity = (Button)findViewById(R.id.toDBActivity);
        toDBActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto DatabaseSampleIndexActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, DatabaseSampleIndexActivity.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });
        /**/
        toFragmentExample = (Button)findViewById(R.id.toFragmentExample);
        toFragmentExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto DatabaseSampleIndexActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, ActivityForFragmentHelloWorld.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });
        //toAsyncActivity
        toAsyncActivity = (Button)findViewById(R.id.toAsyncActivity);
        toAsyncActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto AsyncTaskSampleIndexActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, AsyncTaskSampleIndexActivity.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });
        //toRetrofitActivity
        toRetrofitActivity = (Button)findViewById(R.id.toRetrofitActivity);
        toRetrofitActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto RetrofitSampleIndexActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, RetrofitSampleIndexActivity.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });
        /**/
        toBasicUi = (Button)findViewById(R.id.toBasicUiActivity);
        toBasicUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto toBasicUiActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, BasicUIIndexActivity.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });
        /**/
        toArchitectureActivity = (Button)findViewById(R.id.toArchitectureActivity);
        toArchitectureActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto toArchitectureActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, ArchitectureSampleIndexActivity.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });
        /**/
        toAboutActivity = (Button)findViewById(R.id.toAboutActivity);
        toAboutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** MainActivity - goto toAboutActivity activity **");
                /*Build Intent object with sourceActivity(this) and targetActivity*/
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                /*Call target (second) activity*/
                startActivity(intent);
            }
        });

        /** END - Navigation section **/

    }
}
