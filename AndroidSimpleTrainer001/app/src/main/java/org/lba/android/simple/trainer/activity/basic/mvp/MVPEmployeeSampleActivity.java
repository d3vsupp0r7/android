package org.lba.android.simple.trainer.activity.basic.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.lba.android.simple.trainer.MainActivity;
import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.ArchitectureSampleIndexActivity;
import org.lba.android.simple.trainer.activity.basic.mvp.presenter.EmployeePresenter;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

public class MVPEmployeeSampleActivity extends AppCompatActivity implements EmployeePresenter.View{

    private EmployeePresenter presenter;
    private TextView myTextView;
    private ProgressBar progressBar;
    /**/
    Button toMainActivityButton;
    Button backToIndexSampleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpemployee_sample);


        presenter = new EmployeePresenter(this);

        myTextView = findViewById(R.id.myTextView);
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText email = findViewById(R.id.surnameEditText);
        initProgressBar();


        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updateName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                hideProgressBar();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updateSurname(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                hideProgressBar();
            }
        });


        /**/
        toMainActivityButton = (Button)findViewById(R.id.toMainActivityButton);
        toMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** MVPEmployeeSampleActivity goto MainActivity - started **");
                Intent intent= new Intent(MVPEmployeeSampleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToIndexSampleButton = (Button)findViewById(R.id.backToIndexSampleButton);
        backToIndexSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** MVPEmployeeSampleActivity goto ArchitectureSampleIndexActivity - started **");
                Intent intent= new Intent(MVPEmployeeSampleActivity.this, ArchitectureSampleIndexActivity.class);
                startActivity(intent);
            }
        });
    }


    /**/
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels,
                250);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
        showProgressBar();
    }

    /************/
    @Override
    public void updateUserInfoTextView(String info) {
        myTextView.setText(info);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
