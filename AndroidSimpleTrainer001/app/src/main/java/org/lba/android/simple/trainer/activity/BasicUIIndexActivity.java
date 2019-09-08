package org.lba.android.simple.trainer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.basic.ui.autocomplete.AutoCompleteExampleActivity;

public class BasicUIIndexActivity extends AppCompatActivity {

    private static final String TAG = BasicUIIndexActivity.class.getName();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_uiindex);

        /**/
        Button example1 = (Button)findViewById(R.id.dbButtonSample1);

        /**/
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button btn = (Button) view;

                switch (btn.getId()) {

                    case R.id.dbButtonSample1:
                        /*Build Intent object with sourceActivity(this) and targetActivity*/
                        intent = new Intent(BasicUIIndexActivity.this, AutoCompleteExampleActivity.class);
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
