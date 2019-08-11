package org.lba.android.simple.trainer.activity.dynamictable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.DateTimeSampleIndexActivity;
import org.lba.android.simple.trainer.activity.DynamicTableSampleIndexActivity;
import org.lba.android.simple.trainer.activity.datetime.picker.DateAndTimePickerActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;
import org.lba.android.simple.trainer.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class DynamicTableExample1SingleRowActivity extends AppCompatActivity {

    //Obtain class as TAG for logging
    private String TAG = DynamicTableActivity.class.getSimpleName();
    Button toMainActivityButton;
    Button backToIndexSampleButton;
    //
    PodamFactory factory;
    List<Employee> myEmployeeList;
    //
    ConstraintLayout mConstraintLayout;
    Map<Integer, Employee> items = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_table_example1_single_row);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.costraintLayout);
        /**/
        toMainActivityButton = (Button)findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SecondActivity goto MainActivity - started **");
                Intent intent= new Intent(DynamicTableExample1SingleRowActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button)findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SecondActivity goto MainActivity - started **");
                Intent intent= new Intent(DynamicTableExample1SingleRowActivity.this, DynamicTableSampleIndexActivity.class);
                startActivity(intent);
            }
        });
        /**/
        loadData();

    }

    public void loadData() {

        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** DynamicTableExample1SingleRowActivity - loadData - started **");

        /*Demo Data Builder*/
        myEmployeeList = new ArrayList<>();
        factory = new PodamFactoryImpl();

        for(int i = 0; i < 10; i++){
            Employee employeeElement = factory.manufacturePojoWithFullData(Employee.class);
            myEmployeeList.add(employeeElement);
        }

        ScrollView scrollViewLayout = findViewById(R.id.scrollViewLayout);
        LinearLayout scrollViewLinearLayout = findViewById(R.id.linearLayoutScrollView);
        scrollViewLinearLayout.setOrientation(LinearLayout.VERTICAL);

        for(int i =0; i < 2; i++){

            Employee currentEmployee = myEmployeeList.get(i);
            items.put(i,currentEmployee);

            String color = "";
            if(i%2 ==0) {
                color = "#e5eb34";
            }else{
                color = "#acfcf7";
            }
            //
            TableRow tr1 = new TableRow(this);
            buildRowTrainDetails(i, tr1,currentEmployee, color);
            //
            TableRow tr2 = new TableRow(this);
            buildRowTrainDetailsArrival(i, tr2,currentEmployee, color);
            //
            TableRow tr3 = new TableRow(this);
            buildRowTrainDetailsSolutions(i, tr3,currentEmployee, color);
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

    private void buildRowTrainDetails(int i, TableRow tr1, Employee currentEmployee, String color) {
        //
        TextView rowText1 = new TextView(this);
        rowText1.setText("id: " + currentEmployee.getId());
        rowText1.setPadding(5, 15, 15, 15);

        TextView rowText2 = new TextView(this);
        rowText2.setText("Name: " + currentEmployee.getName().substring(4));
        rowText2.setPadding(5, 15, 15, 15);

        TextView rowText3 = new TextView(this);
        rowText3.setText("Surname: " + currentEmployee.getSurname().substring(4));
        rowText3.setPadding(5, 15, 15, 15);
        //
        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        LL.addView(rowText1);
        LL.addView(rowText2);
        LL.addView(rowText3);
//        LL.setTag(View.generateViewId(),"td_"+i);
        //
//        tr1.setTag(View.generateViewId(),"tr_td_"+i);

        tr1.setBackgroundColor(Color.parseColor(color));
        tr1.addView(LL);

    }

    private void buildRowTrainDetailsArrival(int i, TableRow tr2,  Employee currentEmployee, String color) {
        //
        TextView rowText1 = new TextView(this);
        rowText1.setText("Address Region:" + currentEmployee.getAddress().getRegion().substring(4));
        rowText1.setPadding(5, 15, 15, 15);

        TextView rowText2 = new TextView(this);
        rowText2.setText("Address City: " + currentEmployee.getAddress().getCity().substring(4));
        rowText2.setPadding(5, 15, 15, 15);

        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        LL.addView(rowText1);
        LL.addView(rowText2);
   //     LL.setTag(View.generateViewId(),"tda_"+i);

        tr2.addView(LL);
  //      tr2.setTag(View.generateViewId(),"tr_tda_"+i);
        tr2.setBackgroundColor(Color.parseColor(color));
    }

    private void buildRowTrainDetailsSolutions(int i, TableRow tr2, Employee currentEmployee, String color) {

        //
        TextView rowText1 = new TextView(this);
        rowText1.setText("Num Devices: " + currentEmployee.getDevices().size());
        rowText1.setPadding(5, 15, 15, 15);

        TextView rowText2 = new TextView(this);
        rowText2.setText("MMMM2: " + i);
        rowText2.setPadding(5, 15, 15, 15);

        TextView rowText3 = new TextView(this);
        rowText3.setText("Urb: " + i);
        rowText3.setPadding(5, 15, 15, 15);

        TextView rowText4 = new TextView(this);
        rowText4.setText("MMMM2: " + i);
        rowText4.setPadding(5, 15, 15, 15);

        TextView rowText5 = new TextView(this);
        rowText5.setText("MMMM2: " + i);
        rowText5.setPadding(5, 15, 15, 15);
        //
        LinearLayout LL = new LinearLayout(this);
   //     LL.setTag(View.generateViewId(),"tds_"+i);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        LL.addView(rowText1);
        LL.addView(rowText2);
        LL.addView(rowText3);
        LL.addView(rowText4);
        LL.addView(rowText5);
        //
    //    tr2.setTag(View.generateViewId(),"tr_tds_"+i);
        tr2.addView(LL);
        tr2.setBackgroundColor(Color.parseColor(color));

    }


}
