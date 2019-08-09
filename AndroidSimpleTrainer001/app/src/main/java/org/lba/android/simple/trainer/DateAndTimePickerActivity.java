package org.lba.android.simple.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import android.app.AlertDialog;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateAndTimePickerActivity extends AppCompatActivity {

    TextView firstSelectedDate;
    TextView secondSelectedDate;
    Button pickDateTimeButton;
    Button pickDateTimeButton2;
    Button toMainActivityButton;
    //
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time_picker);

        firstSelectedDate = (TextView) findViewById(R.id.firstSelectedDate);
        secondSelectedDate = (TextView) findViewById(R.id.secondSelectedDate);

        /**/
        pickDateTimeButton = (Button) findViewById(R.id.pickDateTimeButton);
        pickDateTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDateTime(v,0);
            }
        });

        pickDateTimeButton2 = (Button) findViewById(R.id.pickDateTimeButton2);
        pickDateTimeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDateTime(v,1);
            }
        });
        /**/
        toMainActivityButton = (Button)findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SecondActivity goto MainActivity - started **");
                Intent intent= new Intent(DateAndTimePickerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SelectDateTime(View v, final int flag) {
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
                /**/
                TimeZone tz = calendar.getTimeZone();
                DateTimeZone jodaTz = DateTimeZone.forID(tz.getID());
                DateTime dateTime = new DateTime(calendar.getTimeInMillis(), jodaTz);
                String pattern = "dd/MM/yyyy HH:mm";
                String result = dateTime.toString(pattern);
                String toTxtView = result.replace(" ","\n");
                /**/
                switch(flag){
                    case 0:
                        //Do this and this
                        firstSelectedDate.setText(toTxtView);
                        break;
                    case 1:
                        //Do this and this
                        secondSelectedDate.setText(toTxtView);
                        break;

                    default:
                        break;
                }

                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }
}
