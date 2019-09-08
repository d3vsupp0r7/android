package org.lba.android.simple.trainer.activity.basic.livedata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.ArchitectureSampleIndexActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

public class SimpleLiveDataExampleActivity extends AppCompatActivity  {

    Button buttonGenerateData;
    Button toMainActivityButton;
    Button backToIndexSampleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_live_data);

        //add observer to LiveData
        //observer gets called every time data changes in LiveData
       /* LiveDataObject.getData().observe(this, time -> {
            ((TextView)findViewById(R.id.observedUIComponent)).setText(""+time);
        });*/
       LiveDataObject.getData().observe(this, new Observer<Long>() {
           @Override
           public void onChanged(Long aLong) {
               ((TextView)findViewById(R.id.observedUIComponent)).setText(""+aLong);
           }
       });
       /**/
        buttonGenerateData = (Button)findViewById(R.id.buttonGenerateData);
        buttonGenerateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SimpleLiveDataExampleActivity generate observable data **");
                getTime(v);
            }
        });
        /**/
        /**/
        toMainActivityButton = (Button)findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SimpleLiveDataExampleActivity goto MainActivity - started **");
                Intent intent= new Intent(SimpleLiveDataExampleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button)findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SimpleLiveDataExampleActivity goto ArchitectureSampleIndexActivity - started **");
                Intent intent= new Intent(SimpleLiveDataExampleActivity.this, ArchitectureSampleIndexActivity.class);
                startActivity(intent);
            }
        });
    }
    //on click of button, set latest time to LiveData
    public void getTime(View view){
        LiveDataObject.getData();
    }
}
