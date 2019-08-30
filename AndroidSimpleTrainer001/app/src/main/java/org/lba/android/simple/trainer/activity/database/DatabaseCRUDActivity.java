package org.lba.android.simple.trainer.activity.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.DatabaseSampleIndexActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;
import org.lba.android.simple.trainer.db.dao.EmployeeDAO;
import org.lba.android.simple.trainer.db.model.EmployeeModel;

import java.util.List;

public class DatabaseCRUDActivity extends AppCompatActivity {

    //
    Context mContext;
    //
    Button toMainActivityButton;
    Button backToIndexSampleButton;
    Button dbCreateButton;
    /**/
    EditText nameInputTxt;
    EditText surnameInputTxt;
    /**/
    TextView txtLblData1;
    TextView txtLblData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_crud);

        /**/
        mContext = this;
        /**/
        nameInputTxt = findViewById(R.id.nameInputTxt);
        surnameInputTxt = findViewById(R.id.surnameInputTxt);
        txtLblData1 = findViewById(R.id.txtLblData1);
        txtLblData2 = findViewById(R.id.txtLblData2);
        /*Load existing Employees*/
        EmployeeDAO employeeDAO = new EmployeeDAO(mContext);
        List<EmployeeModel>listEmployees = employeeDAO.readAllEmployee();
        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"Read employees: " + listEmployees.size());
        if(listEmployees!=null && listEmployees.size()>0){
            txtLblData1.setText("There are saved employees: " + listEmployees.size());
        }

        /**/
        toMainActivityButton = (Button)findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** DatabaseCRUDActivity goto MainActivity - started **");
                Intent intent= new Intent(DatabaseCRUDActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button)findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** DatabaseCRUDActivity goto DatabaseSampleIndexActivity - started **");
                Intent intent= new Intent(DatabaseCRUDActivity.this, DatabaseSampleIndexActivity.class);
                startActivity(intent);
            }
        });

        /*Create Button*/
        dbCreateButton  = (Button)findViewById(R.id.dbCreateButton);
        dbCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** DatabaseCRUDActivity goto DatabaseSampleIndexActivity - started **");

                /**/
                EmployeeModel model = new EmployeeModel(nameInputTxt.getText().toString(),surnameInputTxt.getText().toString());
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"Employee for input fields: " + model.toString() );

                /**/
                EmployeeDAO employeeDAO = new EmployeeDAO(mContext);
                long createEmployeeId = employeeDAO.createEmployeeRecord(model);
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"Created Employee with id: " + createEmployeeId );
                /**/
                nameInputTxt.setText("");
                surnameInputTxt.setText("");
                txtLblData1.setText("Saved employee with id: " + createEmployeeId);
                /**/
                EmployeeModel savedEmployee = employeeDAO.findEmployeeById(createEmployeeId);
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"Found Employee by id: " + savedEmployee.toString() );
                txtLblData2.setText("Action: CREATE - Result: " + savedEmployee.toString());


            }
        });
    }
}
