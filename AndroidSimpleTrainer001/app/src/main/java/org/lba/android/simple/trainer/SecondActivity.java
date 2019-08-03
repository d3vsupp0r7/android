package org.lba.android.simple.trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

public class SecondActivity extends Activity {

    Button backToFirstActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SecondActivity - started **");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        backToFirstActivityButton =(Button)findViewById(R.id.backToFirstActivityButton);
        backToFirstActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SecondActivity goto MainActivity - started **");
                Intent intent= new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
