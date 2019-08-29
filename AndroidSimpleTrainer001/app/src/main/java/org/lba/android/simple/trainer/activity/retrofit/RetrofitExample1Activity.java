package org.lba.android.simple.trainer.activity.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.DynamicTableSampleIndexActivity;
import org.lba.android.simple.trainer.activity.RetrofitSampleIndexActivity;
import org.lba.android.simple.trainer.activity.dynamictable.DynamicTableActivity;
import org.lba.android.simple.trainer.activity.dynamictable.DynamicTableExample1SingleRowActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

public class RetrofitExample1Activity extends AppCompatActivity {


    private String TAG = RetrofitExample1Activity.class.getSimpleName();
    Button toMainActivityButton;
    Button backToIndexSampleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_example1);

        /**/
        toMainActivityButton = (Button)findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** RetrofitExample1Activity goto MainActivity - started **");
                Intent intent= new Intent(RetrofitExample1Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button)findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** RetrofitExample1Activity goto RetrofitSampleIndexActivity - started **");
                Intent intent= new Intent(RetrofitExample1Activity.this, RetrofitSampleIndexActivity.class);
                startActivity(intent);
            }
        });
        /**/
    }
}
