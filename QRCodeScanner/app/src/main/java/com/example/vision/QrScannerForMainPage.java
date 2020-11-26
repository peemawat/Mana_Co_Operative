package com.example.vision;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vision.Model.User;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class QrScannerForMainPage extends AppCompatActivity implements View.OnClickListener  {
    private SQLiteDB sqLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanCode();
        sqLiteDB = new SQLiteDB(this);
    }


    @Override
    public void onClick(View view) {
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }


    @Override
    protected void onActivityResult(int requesCode,int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requesCode, resultCode, data);
        List<User> userCheck = sqLiteDB.getAuthenData();
        if (result != null) {
            if (result.getContents() != null){
                String keyword = result.getContents();
                System.out.println("Keyword : "+keyword+"\nUser length : "+userCheck.toArray().length);
                decisionPage(keyword);

            }
            else {
                Toast.makeText(this,"No result", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requesCode, resultCode, data);
        }
    }


    public void decisionPage(String keyword){
        String register = "register";
        if (keyword.equals(register)){
            startActivity(new Intent(getApplication(),Registration.class));
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Invalid QR Code");
            builder.setPositiveButton("Try again !", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do something
                    startActivity(new Intent(getApplication(), QrScannerForMainPage.class));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do something
                    startActivity(new Intent(getApplication(), MainActivity.class));
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
}