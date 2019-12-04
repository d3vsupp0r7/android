package org.lba.android.simple.trainer.activity.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.DynamicTableSampleIndexActivity;
import org.lba.android.simple.trainer.activity.RetrofitSampleIndexActivity;
import org.lba.android.simple.trainer.activity.dynamictable.DynamicTableActivity;
import org.lba.android.simple.trainer.activity.dynamictable.DynamicTableExample1SingleRowActivity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitExample1Activity extends AppCompatActivity {


    private String TAG = RetrofitExample1Activity.class.getSimpleName();
    Button toMainActivityButton;
    Button backToIndexSampleButton;
    Button btnExecute;
    //Text
    EditText txtUrl;
    EditText txtParam1;
    EditText txtParam2;

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
        //Text init
        txtUrl =findViewById(R.id.txtUrl);
        txtParam1 = findViewById(R.id.txtParam1);
        txtParam2 = findViewById(R.id.txtParam2);

        btnExecute = (Button)findViewById(R.id.btnExecute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** RetrofitExample1Activity - btnExecute - started **");
                //1: Read data
                String url = txtUrl.getText().toString();
                String param1 = txtParam1.getText().toString();
                String param2 = txtParam2.getText().toString();
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"**URL: " + url + " param1: " + param1 + " - param2: " + param2);
                //Invocation
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }
        });
    }
}
