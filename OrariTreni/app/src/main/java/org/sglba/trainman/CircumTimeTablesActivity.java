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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class CircumTimeTablesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circum_time);
        //Trains Button
        ImageView napoliSorrentoButton=findViewById(R.id.napoliSorrentoButton);
        napoliSorrentoButton.setImageResource(R.drawable.pdf_icon);
        ImageView napoliBaianoButton=findViewById(R.id.napoliBaianoButton);
        napoliBaianoButton.setImageResource(R.drawable.pdf_icon);
        ImageView napoliSarnoButton=findViewById(R.id.napoliSarnoButton);
        napoliSarnoButton.setImageResource(R.drawable.pdf_icon);
        ImageView napoliPoggiomarinoButton=findViewById(R.id.napoliPoggiomarinoButton);
        napoliPoggiomarinoButton.setImageResource(R.drawable.pdf_icon);
        ImageView napoliAcerraButton=findViewById(R.id.napoliAcerraButton);
        napoliAcerraButton.setImageResource(R.drawable.pdf_icon);
        ImageView napoliSanGiorgioButton=findViewById(R.id.napoliSanGiorgioButton);
        napoliSanGiorgioButton.setImageResource(R.drawable.pdf_icon);
        //Bus Button
        ImageView napoliSorrentoBusButton=findViewById(R.id.napoliSorrentoBusButton);
        napoliSorrentoBusButton.setImageResource(R.drawable.pdf_icon);
        ImageView napoliBaianoBusButton=findViewById(R.id.napoliBaianoBusButton);
        napoliBaianoBusButton.setImageResource(R.drawable.pdf_icon);
        ImageView napoliSarnoBusButton=findViewById(R.id.napoliSarnoBusButton);
        napoliSarnoBusButton.setImageResource(R.drawable.pdf_icon);
        ImageView napoliPoggiomarinoBusButton=findViewById(R.id.napoliPoggiomarinoBusButton);
        napoliPoggiomarinoBusButton.setImageResource(R.drawable.pdf_icon);
        //Trains Button OnClickListener
        napoliSorrentoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliSorrento");
            }
        });
        napoliBaianoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliBaiano");
            }
        });
        napoliSarnoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliSarno");
            }
        });
        napoliPoggiomarinoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliPoggiomarino");
            }
        });
        napoliAcerraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliAcerra");
            }
        });
        napoliSanGiorgioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliSanGiorgio");
            }
        });
        //Bus Button OnClickListener
        napoliSorrentoBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliSorrentoBus");
            }
        });
        napoliBaianoBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliBaianoBus");
            }
        });
        napoliSarnoBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliSarnoBus");
            }
        });
        napoliPoggiomarinoBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenPDF("NapoliPoggiomarinoBus");
            }
        });
    }

    private void checkPermissionAndOpenPDF(String pdfName) {
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
                openPDFFiles(pdfName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void openPDFFiles(String pdfName) {
        File fPath=new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/pdf");
        if (!fPath.exists()){
            fPath.mkdirs();
        }
        File f = new File(fPath,pdfName+".pdf");

        if (!f.exists()) {
            AssetManager assets=getAssets();
            try {
                copy(assets.open(pdfName+".pdf"), f);
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
