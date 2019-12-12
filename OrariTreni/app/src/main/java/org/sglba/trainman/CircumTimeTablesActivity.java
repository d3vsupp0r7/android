package org.sglba.trainman;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class CircumTimeTablesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CircumTimeTablesActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},102);
        }else {
            try {
                openPDFFiles();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void openPDFFiles() {
        File fPath=new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/pdf");
        if (!fPath.exists()){
            fPath.mkdirs();
        }
        File f = new File(fPath,"NapoliSorrento.pdf");

        if (!f.exists()) {
            AssetManager assets=getAssets();
            try {
                copy(assets.open("NapoliSorrento.pdf"), f);
            }
            catch (IOException e) {
                Log.e("FileProvider", "Exception copying from assets", e);
            }
        }

        Intent i=
                new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(f),"application/pdf");

        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(i);
    }
    static private void copy(InputStream in, File dst) throws IOException {
        FileOutputStream out=new FileOutputStream(dst);
        byte[] buf=new byte[1024];
        int len;

        while ((len=in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }
}
