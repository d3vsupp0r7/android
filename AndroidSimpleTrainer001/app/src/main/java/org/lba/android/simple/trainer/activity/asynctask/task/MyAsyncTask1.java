package org.lba.android.simple.trainer.activity.asynctask.task;

import android.os.AsyncTask;
import android.util.Log;

import org.lba.android.simple.trainer.activity.asynctask.AsyncTaskExample1Activity;
import org.lba.android.simple.trainer.costraints.ApplicationCostraintsEnum;

public class MyAsyncTask1 extends AsyncTask {

    private static final String TAG = MyAsyncTask1.class.getName();

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** MyAsyncTask1 - doInBackground - started **");
        // code that will run in the background
        /*
         This method is invoked on the background thread immediately after onPreExecute()
         finishes its execution. Main purpose of this method is to perform the background
         operations that can take a long time.
        */
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** MyAsyncTask1 - onPreExecute - started **");
        /*
        It invoked on the main UI thread before the task is executed.
        This method is mainly used to setup the task for instance by showing a
        ProgressBar or ProgressDialog in the UI
        */
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** MyAsyncTask1 - onProgressUpdate - started **");
        // receive progress updates from doInBackground
        /*
         This method is invoked on the main UI thread after a call to publishProgress(Progress…).
          Timing of the execution is undefined. This method is used to display any form
          of progress in the user interface while the background operations are executing.
        */
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object o) {
        Log.d(ApplicationCostraintsEnum.APP_NAME.getValue(),"** MyAsyncTask1 - onPostExecute - started **");
        // update the UI after background processes completes
        /*
         This method is invoked on the main UI thread after the background operation finishes
          in the doInBackground method. The result of the background operation is passed to
          this step as a parameter and then we can easily update our UI to show the results.
        */
        super.onPostExecute(o);
    }


}
