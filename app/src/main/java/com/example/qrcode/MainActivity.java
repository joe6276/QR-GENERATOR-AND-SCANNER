package com.example.qrcode;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button scan, generate;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name= findViewById(R.id.name);
        scan= findViewById(R.id.scan);
        generate= findViewById(R.id.generate);
        imageView= findViewById(R.id.imageView);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                PackageManager.PERMISSION_GRANTED);
        generate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String data = name.getText().toString();
                        if(data.isEmpty()){
                            Toast.makeText(MainActivity.this, "enter a value",Toast.LENGTH_LONG).show();
                        }else
                        {
                            QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
//                            4 rgEncoder.setColorWhite(Color.BLUE);
                            try {
                                // Getting QR-Code as Bitmap
                                Bitmap bitmap = qrgEncoder.getBitmap();
                                // Setting Bitmap to ImageView
                                imageView.setImageBitmap(bitmap);
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                }
        );
        scan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent (MainActivity.this,ScannerActivity2.class);
                        startActivity(i);
                        finish();
                    }
                }
        );
    }

}