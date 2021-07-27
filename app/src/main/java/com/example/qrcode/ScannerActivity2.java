package com.example.qrcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class ScannerActivity2 extends AppCompatActivity {
CodeScanner codeScanner;
        CodeScannerView scannerView;
        TextView resultdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner2);
        scannerView= findViewById(R.id.scanner_view);
        resultdata= findViewById(R.id.textView3);



        codeScanner= new CodeScanner(this,scannerView);
        codeScanner.setDecodeCallback(
                new DecodeCallback() {
                    @Override
                    public void onDecoded(@NonNull Result result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            resultdata.setText(result.getText());
                            Toast.makeText(ScannerActivity2.this,result.getText(),Toast.LENGTH_LONG).show();
                        }
                    });
                    }
                }
        );
        scannerView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        codeScanner.startPreview();
                        resultdata.setText("");
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }
}