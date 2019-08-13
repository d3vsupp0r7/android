package org.lba.android.simple.trainer.activity.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.DatabaseSampleIndexActivity;
import org.lba.android.simple.trainer.activity.DateTimeSampleIndexActivity;
import org.lba.android.simple.trainer.activity.datetime.picker.DateAndTimePickerActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

public class DatabaseCRUDActivity extends AppCompatActivity {

    Button toMainActivityButton;
    Button backToIndexSampleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_crud);

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
    }
}