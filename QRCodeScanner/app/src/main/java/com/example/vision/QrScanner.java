package com.example.vision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrScanner extends AppCompatActivity implements View.OnClickListener  {
    Button scanBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomation_activity);
        scanCode();
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
                String verify = "verify";
                String keyword = result.getContents();
                System.out.println(keyword);
                if (keyword.equalsIgnoreCase(verify)){
                    Intent intent = new Intent(getApplication(),VerifyData.class);
                    startActivity(intent);
                }
            }
            else {
                Toast.makeText(this,"No result", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requesCode, resultCode, data);
        }
    }
}