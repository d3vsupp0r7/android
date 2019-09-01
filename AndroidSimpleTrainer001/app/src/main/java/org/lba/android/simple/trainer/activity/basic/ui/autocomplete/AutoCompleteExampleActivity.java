package org.lba.android.simple.trainer.activity.basic.ui.autocomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.BasicUIIndexActivity;
import org.lba.android.simple.trainer.activity.basic.ui.autocomplete.adapter.EmployeeAdapter;
import org.lba.android.simple.trainer.activity.basic.ui.model.EmployeeUISampleModel;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

import java.util.ArrayList;

public class AutoCompleteExampleActivity extends AppCompatActivity {

    //
    Context mContext;
    //
    Button toMainActivityButton;
    Button backToIndexSampleButton;
    //
    AutoCompleteTextView autoCpmTxtViewStringArray;
    //List object AutoComplete
    EmployeeAdapter employeeAdapter;
    AutoCompleteTextView autoCpmTxtViewObjectList;
    ArrayList<EmployeeUISampleModel> listEmployees;
    AutoCompleteTextView autoCompleteObjectList;

    /***/
    private String[] employeeNamesStringArray = {"Anthony", "Artur", "Bob", "Chris", "Donald", "Gregory", "Kim", "Martin", "Paul"};
    /***/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_example);

        /**/
        autoCpmTxtViewStringArray = findViewById(R.id.autoCpmTxtViewStringArray);
        autoCompleteObjectList = findViewById(R.id.autoCompleteObjectList);
        /**/
        toMainActivityButton = (Button) findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** AutoCompleteExampleActivity goto MainActivity - started **");
                Intent intent = new Intent(AutoCompleteExampleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button) findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** AutoCompleteExampleActivity goto BasicUIIndexActivity - started **");
                Intent intent = new Intent(AutoCompleteExampleActivity.this, BasicUIIndexActivity.class);
                startActivity(intent);
            }
        });
        /** String[] array automplete management**/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, employeeNamesStringArray);
        autoCpmTxtViewStringArray.setThreshold(1); //will start working from first character
        autoCpmTxtViewStringArray.setAdapter(adapter);
        /***/

        /** Autocomplete with object list **/
        listEmployees = new ArrayList<>();
        //"Anthony", "Artur", "Bob", "Chris", "Donald", "Gregory", "Kim", "Martin", "Paul"
        EmployeeUISampleModel t1 = new EmployeeUISampleModel(1,"Anthony_EMPList","Surname_1");
        EmployeeUISampleModel t2 = new EmployeeUISampleModel(2,"Artur_EMPList","Surname_2");
        EmployeeUISampleModel t3 = new EmployeeUISampleModel(3,"Bob_EMPList","Surname_3");
        EmployeeUISampleModel t4 = new EmployeeUISampleModel(4,"Chris_EMPList","Surname_4");
        EmployeeUISampleModel t5 = new EmployeeUISampleModel(5,"Donald_EMPList","Surname_5");
        EmployeeUISampleModel t6 = new EmployeeUISampleModel(6,"Gregory_EMPList","Surname_6");
        EmployeeUISampleModel t7 = new EmployeeUISampleModel(7,"Kim_EMPList","Surname_7");
        EmployeeUISampleModel t8 = new EmployeeUISampleModel(8,"Martin_EMPList","Surname_8");
        EmployeeUISampleModel t9 = new EmployeeUISampleModel(9,"Paul_EMPList","Surname_9");
        listEmployees.add(t1);
        listEmployees.add(t2);
        listEmployees.add(t3);
        listEmployees.add(t4);
        listEmployees.add(t5);
        listEmployees.add(t6);
        listEmployees.add(t7);
        listEmployees.add(t8);
        listEmployees.add(t9);
        /**/
        employeeAdapter = new EmployeeAdapter(this, R.layout.autocomplete_employee_list_layout, listEmployees);

        autoCompleteObjectList.setThreshold(1);
        autoCompleteObjectList.setAdapter(employeeAdapter);

        // handle click event and set desc on textview
        autoCompleteObjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EmployeeUISampleModel employeeSelected = (EmployeeUISampleModel) adapterView.getItemAtPosition(i);
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** Employee selected: " + employeeSelected.toString() +" **");
                //fruitDesc.setText(fruit.getDesc());
            }
        });
        /***/
    }
}
