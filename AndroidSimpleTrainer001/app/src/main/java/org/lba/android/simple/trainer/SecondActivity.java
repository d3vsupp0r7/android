package org.lba.android.simple.trainer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;
import org.lba.android.simple.trainer.model.Employee;

public class SecondActivity extends Activity {

    Button backToFirstActivityButton;
    Button showPopupButton;
    PopupWindow mPopupWindow;
    //
    TextView printSharedDataTextView;
    //
    Context mContext;
    ConstraintLayout mConstraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SecondActivity - started **");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.secondActivityLayout);

        /* Activity Navigation - goBack Button */
        backToFirstActivityButton =(Button)findViewById(R.id.backToFirstActivityButton);
        backToFirstActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** SecondActivity goto MainActivity - started **");
                Intent intent= new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        /** Get Shared data**/
        Intent intent = getIntent();
        Employee sharedEmployee = (Employee)intent.getExtras().get("employeeShared");
        printSharedDataTextView = (TextView)findViewById(R.id.printSharedDataTextView);
        printSharedDataTextView.setMovementMethod(new ScrollingMovementMethod());
        printSharedDataTextView.setText(sharedEmployee.toString());

        /*Manage popou section*/
        mContext = this;
        showPopupButton = (Button)findViewById(R.id.showPopupButton);
        showPopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.simple_popup_layout,null);
                //

                Drawable d = new ColorDrawable(Color.BLUE);
                //
                mPopupWindow = new PopupWindow(
                        customView,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                );

                // Get a reference for the custom view close button
                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
                closeButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                mPopupWindow.setBackgroundDrawable(d);
                mPopupWindow.showAtLocation(mConstraintLayout, Gravity.CENTER,0,0);
            }
        });
    }


}
