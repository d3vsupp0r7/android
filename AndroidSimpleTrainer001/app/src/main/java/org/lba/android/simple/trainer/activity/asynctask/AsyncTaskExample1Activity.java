package org.lba.android.simple.trainer.activity.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.AsyncTaskSampleIndexActivity;
import org.lba.android.simple.trainer.activity.DynamicTableSampleIndexActivity;
import org.lba.android.simple.trainer.activity.dynamictable.DynamicTableExample1SingleRowActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

public class AsyncTaskExample1Activity extends AppCompatActivity {

    private static final String TAG = AsyncTaskExample1Activity.class.getName();
    //
    Button toMainActivityButton;
    Button backToIndexSampleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example1);

        /**/
        toMainActivityButton = (Button)findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** AsyncTaskExample1Activity goto MainActivity - started **");
                Intent intent= new Intent(AsyncTaskExample1Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button)findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** AsyncTaskExample1Activity goto AsyncTaskSampleIndexActivity - started **");
                Intent intent= new Intent(AsyncTaskExample1Activity.this, AsyncTaskSampleIndexActivity.class);
                startActivity(intent);
            }
        });
    }
}
