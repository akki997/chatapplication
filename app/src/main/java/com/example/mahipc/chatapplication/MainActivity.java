package com.example.mahipc.chatapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button button;


    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        } else {
            // do nothing
        }
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number= editText.getText().toString();
                String sms= textView.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number,null,sms,null,null);
                    Toast.makeText(MainActivity.this, "SENT", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
        @Override
        public void onRequestPermissionsResult ( int requestCode, String[] permission,
        int[] grantResults){
            switch (requestCode) {
                case 1: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    {
                        if (ContextCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                        }

                    } else{
                        Toast.makeText(this, "No permission granted!", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

            }
        }
    }
