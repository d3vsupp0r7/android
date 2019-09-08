package org.lba.android.simple.trainer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.asynctask.AsyncTaskExample1Activity;

public class AsyncTaskSampleIndexActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_sample_index);

        /**/
        Button example1 = (Button)findViewById(R.id.btnToExample1);

        /**/
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button btn = (Button)view;
                switch (btn.getId()){

                    case R.id.btnToExample1:
                        /*Build Intent object with sourceActivity(this) and targetActivity*/
                        intent = new Intent(AsyncTaskSampleIndexActivity.this, AsyncTaskExample1Activity.class);
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
