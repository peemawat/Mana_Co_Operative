package com.example.vision;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vision.Model.User;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class QrScannerForVerify extends AppCompatActivity implements View.OnClickListener  {
    private SQLiteDB sqLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomation_activity);
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
        if (result != null) {
            if (result.getContents() != null){
                String keyword = result.getContents();
                System.out.println("Keyword : "+keyword);
                verify(keyword);
            }
            else {
                Toast.makeText(this,"No result", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requesCode, resultCode, data);
        }
    }


    public void verify(String keyword){
        String verify = "verify";
        List<User> users = sqLiteDB.getAuthenData();
        if (keyword.equals(verify)) {
            Log.d("Reading", "Reading..");
            for (User user : users) {
                String log = "ID : " + user.get_id() + " Username : " + user.getUsername();
                Log.d("User", log);
                new SendMessage().execute(user.get_id() + "," + user.getUsername() + "," + user.getPassword());
                startActivity(new Intent(getApplication(), Info.class));
            }

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Invalid QR Code");
            builder.setPositiveButton("Try again !", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do something
                    startActivity(new Intent(getApplication(), QrScannerForVerify.class));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do something
                    startActivity(new Intent(getApplication(), Info.class));
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}