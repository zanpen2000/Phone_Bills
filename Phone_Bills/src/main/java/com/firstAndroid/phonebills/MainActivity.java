package com.firstAndroid.phonebills;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

    private Button sendButton;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = (Button) this.findViewById(R.id.btnSend);
        radioGroup = (RadioGroup) this.findViewById(R.id.radioGroup);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sms = SmsManager.getDefault();

                String smsText = null;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.netbill:
                        smsText = "5011";
                        break;
                    case R.id.phonebill:
                        smsText = "101";
                        break;
                }

                sms.sendTextMessage("10086", null, smsText, null, null);

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();

                dialog.setMessage(MainActivity.this.getString(R.string.msgBody));
                dialog.setCancelable(false);
                dialog.setButton(
                        DialogInterface.BUTTON_POSITIVE,
                        MainActivity.this.getString(R.string.msgTitle),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                dialog.show();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
