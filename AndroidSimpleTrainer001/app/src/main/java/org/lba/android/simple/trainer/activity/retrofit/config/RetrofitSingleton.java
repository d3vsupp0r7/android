package org.lba.android.simple.trainer.activity.retrofit.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    public static final String BASE_URL = "http://www.viaggiatreno.it";
    public static Retrofit retrofit;
    /*
    This public static method will return Retrofit client
    anywhere in the appplication
    */
    public static Retrofit getRetrofitClient() {
        /*If condition to ensure we don't create multiple
            retrofit instances in a single application
         */
        if (retrofit == null) {
            //Defining the Retrofit using Builder
            retrofit = new Retrofit.Builder()
                    //This is the only mandatory call on Builder object.
                    .baseUrl(BASE_URL)
                    // Convertor library used to convert response into POJO
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
