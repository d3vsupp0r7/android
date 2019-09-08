package org.lba.android.simple.trainer.activity.basic.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Date;

public class LiveDataObject {

    private static MutableLiveData<Long> data = new MutableLiveData<Long>();

    //sets latest time to LiveData
    public static LiveData<Long> getData(){
        data.setValue(new Date().getTime());
        return data;
    }
}
