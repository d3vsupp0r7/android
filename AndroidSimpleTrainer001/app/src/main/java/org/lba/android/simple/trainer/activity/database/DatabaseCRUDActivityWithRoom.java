package org.lba.android.simple.trainer.activity.database;

import androidx.appcompat.app.AppCompatActivity;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.DatabaseSampleIndexActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;
import org.lba.android.simple.trainer.db.dao.EmployeeDAO;
import org.lba.android.simple.trainer.db.dao.EmployeeModelWithRoomDAO;
import org.lba.android.simple.trainer.db.model.EmployeeModel;
import org.lba.android.simple.trainer.db.model.EmployeeModelWithRoom;
import org.lba.android.simple.trainer.db.room.config.RoomDatabaseClient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class DatabaseCRUDActivityWithRoom extends AppCompatActivity {

    //
    Context mContext;
    //
    Button toMainActivityButton;
    Button backToIndexSampleButton;
    //
    Button dbCreateButton;
    Button btnDeleteAll;
    //
    /**/
    TextView txtLblData1;
    TextView txtLblData2;
    /**/
    EditText nameInputTxt;
    EditText surnameInputTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_crud_with_room);
        /**/
        mContext = this;
        /**/
        nameInputTxt = findViewById(R.id.nameInputTxt);
        surnameInputTxt = findViewById(R.id.surnameInputTxt);
        txtLblData1 = findViewById(R.id.txtLblData1);
        txtLblData2 = findViewById(R.id.txtLblData2);
        /**/
        /*Load existing Employees*/
        //EmployeeModelWithRoomDAO employeeDAO = RoomDatabaseClient.getInstance(mContext).getAppDatabase().employeeDao();
        //List<EmployeeModelWithRoom> listEmployees = employeeDAO.getAll();
        List<EmployeeModelWithRoom> listEmployees = RoomDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().employeeDao().getAll();

        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"Read employees: " + listEmployees.size());
        if(listEmployees!=null && listEmployees.size()>0){
            txtLblData1.setText("There are saved employees: " + listEmployees.size());
        }else{
            txtLblData1.setText("No employees saved yet");
        }
        /**/
        toMainActivityButton = (Button) findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** DatabaseCRUDActivity goto MainActivity - started **");
                Intent intent = new Intent(DatabaseCRUDActivityWithRoom.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button) findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** DatabaseCRUDActivity goto DatabaseSampleIndexActivity - started **");
                Intent intent = new Intent(DatabaseCRUDActivityWithRoom.this, DatabaseSampleIndexActivity.class);
                startActivity(intent);
            }
        });

        /*UI CRUD Buttons*/
        dbCreateButton = (Button) findViewById(R.id.dbCreateButton);
        dbCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** DatabaseCRUDActivity goto DatabaseSampleIndexActivity - started **");
                /**/
                EmployeeModelWithRoomDAO employeeDao = RoomDatabaseClient.getInstance(mContext).getAppDatabase().employeeDao();
                /**/
                EmployeeModelWithRoom employeeFromTxtInput = new EmployeeModelWithRoom(nameInputTxt.getText().toString(),surnameInputTxt.getText().toString());
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"Employee for input fields: " + employeeFromTxtInput.toString() );
                Long createEmployeeId = employeeDao.insert(employeeFromTxtInput);
                /**/
                EmployeeModelWithRoom savedEmployee = employeeDao.findEmployeeById(createEmployeeId);
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"Employee found: " + savedEmployee.toString() );
                /**/
                nameInputTxt.setText("");
                surnameInputTxt.setText("");
                txtLblData1.setText("Saved employee with id: " + createEmployeeId);
                /**/
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"Employee saved: " + savedEmployee.toString() );
                txtLblData2.setText("Action: CREATE - Result: " + savedEmployee.toString());
            }
        });

        //DeleteAll
        btnDeleteAll  = (Button) findViewById(R.id.btnDeleteAll);
        btnDeleteAll .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeModelWithRoomDAO employeeDao = RoomDatabaseClient.getInstance(mContext).getAppDatabase().employeeDao();
                employeeDao.deleteAll();
                List<EmployeeModelWithRoom> listEmployees = RoomDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().employeeDao().getAll();
                if(listEmployees!=null && listEmployees.size() == 0){
                    txtLblData1.setText("All employee are deleted.");
                    txtLblData2.setText("Action: DELETE - OK");
                }else{
                    txtLblData1.setText("ERROR on DELETE");
                    txtLblData2.setText("Action: DELETE - KO");
                }
            }
        });
    }

}
