package org.lba.android.simple.trainer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;
import org.lba.android.simple.trainer.model.Employee;

import java.util.ArrayList;
import java.util.List;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class DynamicTableActivity  extends AppCompatActivity {

    //
    PodamFactory factory;
    List<Employee> myEmployeeList;
    //UI components
    ProgressDialog mProgressBar;
    Button backToFirstActivityButton;
    //Layout components
    private TableLayout mTableLayout;
    ConstraintLayout mConstraintLayout;

    //Obtain class as TAG for logging
    private String TAG = RetrofitExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** DynamicTableActivity - started **");
        super.onCreate(savedInstanceState);

        mProgressBar = new ProgressDialog(this);

        //Layout management
        setContentView(R.layout.dynamic_table_activity);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.dynamicTableActivityLayout);
        mTableLayout = (TableLayout) findViewById(R.id.tableEmployee);
        mTableLayout.setStretchAllColumns(true);

        startLoadData();

    }

    public void startLoadData() {
        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** DynamicTableActivity - startLoadData - started **");
        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Fetching Employees..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new LoadDataTask().execute(0);
    }

    public void loadData() {
        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** DynamicTableActivity - loadData - started **");
        /**/
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize =0, mediumTextSize = 0;

        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

        /**/
        /* Activity Navigation - goBack Button */
        backToFirstActivityButton =(Button)findViewById(R.id.backToFirstActivityButton);
        backToFirstActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** DynamicTableActivity goto MainActivity - started **");
                Intent intent= new Intent(DynamicTableActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        /*Demo Data Builder*/
        myEmployeeList = new ArrayList<>();
        factory = new PodamFactoryImpl();

        for(int i = 0; i < 100; i++){
            Employee employeeElement = factory.manufacturePojoWithFullData(Employee.class);
            myEmployeeList.add(employeeElement);
        }
        /*Dynamic UI Building*/
        int rows = myEmployeeList.size();
        TextView textSpacer = null;
        mTableLayout.removeAllViews();
        //Row iteration
        for(int i = 0; i < rows; i ++) {
            Employee curEmployee = myEmployeeList.get(i);
            //
            final TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.LEFT);
            tv.setPadding(5, 15, 0, 15);
            tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
            tv.setText(String.valueOf(curEmployee.getId()));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            // TableRow
            final TableRow tr = new TableRow(this);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);

            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);

            /**/
            tr.addView(tv);
            mTableLayout.addView(tr, trParams);
            /*Separator*/
            final TableRow trSep = new TableRow(this);
            TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            trSep.setLayoutParams(trParamsSep);
            TextView tvSep = new TextView(this);
            TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

            tvSepLay.span = 4;
            tvSep.setLayoutParams(tvSepLay);
            tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
            tvSep.setHeight(1);
            trSep.addView(tvSep);
            mTableLayout.addView(trSep, trParamsSep);
        }

    }
    /********************/
    class LoadDataTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {

            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Task Completed.";

        }

        @Override
        protected void onPostExecute(String result) {

            mProgressBar.hide();
            loadData();

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

    }
}
