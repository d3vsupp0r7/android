package org.lba.android.simple.trainer.activity.basic.ui.autocomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.BasicUIIndexActivity;
import org.lba.android.simple.trainer.activity.DatabaseSampleIndexActivity;
import org.lba.android.simple.trainer.activity.database.DatabaseCRUDActivityWithRoom;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

public class AutoCompleteExampleActivity extends AppCompatActivity {

    //
    Context mContext;
    //
    Button toMainActivityButton;
    Button backToIndexSampleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_example);

        /**/
        toMainActivityButton = (Button) findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** AutoCompleteExampleActivity goto MainActivity - started **");
                Intent intent = new Intent(AutoCompleteExampleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button) findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(), "** AutoCompleteExampleActivity goto BasicUIIndexActivity - started **");
                Intent intent = new Intent(AutoCompleteExampleActivity.this, BasicUIIndexActivity.class);
                startActivity(intent);
            }
        });
    }
}
