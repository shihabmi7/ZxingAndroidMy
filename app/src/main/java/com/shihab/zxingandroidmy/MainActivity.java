package com.shihab.zxingandroidmy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button button_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_show = (Button) findViewById(R.id.button_show);

        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                barcodeScan();

            }
        });
    }


    //use this method to scan barcode.
    private void barcodeScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan now");
        integrator.setResultDisplayDuration(0);
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            Log.e("Result", "" + scanContent);
            Toast.makeText(getApplicationContext(),""+scanContent,Toast.LENGTH_LONG).show();
            //do your stuff here..
        }
    }
}
