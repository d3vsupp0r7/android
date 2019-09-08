package org.lba.android.simple.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.basic.livedata.SimpleLiveDataExampleActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

public class AboutActivity extends AppCompatActivity {

    Button toMainActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        /**/
        toMainActivityButton = (Button)findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** AboutActivity goto MainActivity - started **");
                Intent intent= new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
