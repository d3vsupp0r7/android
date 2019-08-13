package org.lba.android.simple.trainer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.dynamictable.DynamicTableActivity;
import org.lba.android.simple.trainer.activity.dynamictable.DynamicTableExample1SingleRowActivity;

public class DynamicTableSampleIndexActivity extends AppCompatActivity {

    private static final String TAG = DynamicTableSampleIndexActivity.class.getName();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_table_sample_index);
        /**/
        Button example1 = (Button)findViewById(R.id.dynTabButtonSample1);
        Button example2 = (Button)findViewById(R.id.dynTabButtonSample2);

        /**/
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button btn = (Button)view;
                switch (btn.getId()){

                    case R.id.dynTabButtonSample1:
                        /*Build Intent object with sourceActivity(this) and targetActivity*/
                         intent = new Intent(DynamicTableSampleIndexActivity.this, DynamicTableActivity.class);
                        /*Call target (second) activity*/
                        startActivity(intent);
                        break;
                    case R.id.dynTabButtonSample2:
                        /*Build Intent object with sourceActivity(this) and targetActivity*/
                        intent = new Intent(DynamicTableSampleIndexActivity.this, DynamicTableExample1SingleRowActivity.class);
                        /*Call target (second) activity*/
                        startActivity(intent);
                        break;
                }
            }
        };

        //Add example
        example1.setOnClickListener(listener);
        example2.setOnClickListener(listener);
    }
}
