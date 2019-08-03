package org.lba.android.simple.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;
import org.lba.android.simple.trainer.model.Employee;

import java.util.ArrayList;
import java.util.List;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    PodamFactory factory;
    List<Employee> myEmployeeList;
    Button nextActivityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** MainActivity - started **");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        /*Demo Data Builder*/
        myEmployeeList = new ArrayList<>();
        factory = new PodamFactoryImpl();

        for(int i = 0; i < 10; i++){
            Employee employeeElement = factory.manufacturePojoWithFullData(Employee.class);
            myEmployeeList.add(employeeElement);
        }
        /*Dynamic UI Building*/
        for (Employee currentEmployee: myEmployeeList) {

        }




    }
}
