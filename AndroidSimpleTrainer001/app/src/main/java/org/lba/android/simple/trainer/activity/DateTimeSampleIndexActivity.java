package org.lba.android.simple.trainer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.datetime.picker.DateAndTimePickerActivity;
import org.lba.android.simple.trainer.activity.dynamictable.DynamicTableActivity;

public class DateTimeSampleIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_sample_index);

        /**/
        Button example1 = (Button)findViewById(R.id.dateTymeButtonSample1);

        /**/
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button btn = (Button)view;
                switch (btn.getId()){

                    case R.id.dateTymeButtonSample1:
                        /*Build Intent object with sourceActivity(this) and targetActivity*/
                        Intent intent = new Intent(DateTimeSampleIndexActivity.this, DateAndTimePickerActivity.class);
                        /*Call target (second) activity*/
                        startActivity(intent);
                        break;
                }
            }
        };

        //Add example
        example1.setOnClickListener(listener);
    }
}
