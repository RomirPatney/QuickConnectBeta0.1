package com.example.android.qcbeta01;

import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editText_phonenumber;
    TextView textView_phonenumber;

    private NfcAdapter nfcAdapter;

//    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    nfc_send_file tab2 = new nfc_send_file();
                    android.app.FragmentManager nfcpart_manager = getFragmentManager();
                    nfcpart_manager.beginTransaction().replace(R.id.content, tab2, tab2.getTag()).commit();
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    SettingsOfApp tab3 = new SettingsOfApp();
                    android.app.FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.content, tab3, tab3.getTag()).commit();

//                    View view = getLayoutInflater().inflate(R.layout.fragment_settings_of_app, null);
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        View view = getLayoutInflater().inflate(R.layout.fragment_settings_of_app, null);
//        editText_phonenumber = (EditText) view.findViewById(R.id.editText);
//        textView_phonenumber = (TextView) view.findViewById(R.id.checkphonenumber);
//        textView_phonenumber.setVisibility(View.GONE);
    }

    //nfc part

    public void sendFile(View view) {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // Check whether NFC is enabled on device
        if(!nfcAdapter.isEnabled()){
            // NFC is disabled, show the settings UI
            // to enable NFC
            Toast.makeText(this, "Please enable NFC.",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        }
        // Check whether Android Beam feature is enabled on device
        else if(!nfcAdapter.isNdefPushEnabled()) {
            // Android Beam is disabled, show the settings UI
            // to enable Android Beam
            Toast.makeText(this, "Please enable Android Beam.",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_NFCSHARING_SETTINGS));
        }
        else {
            // NFC and Android Beam both are enabled

            // File to be transferred
            // For the sake of this tutorial I've placed an image
            // named 'wallpaper.png' in the 'Pictures' directory
            String fileName = "wallpaper.png";

            // Retrieve the path to the user's public pictures directory
            File fileDirectory = Environment
                    .getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES);

            // Create a new file using the specified directory and name
            File fileToTransfer = new File(fileDirectory, fileName);
            fileToTransfer.setReadable(true, false);

            nfcAdapter.setBeamPushUris(
                    new Uri[]{Uri.fromFile(fileToTransfer)}, this);
        }
    }



    public void getphonenumber(View view){

        editText_phonenumber = (EditText) findViewById(R.id.editText);
        textView_phonenumber = (TextView) findViewById(R.id.checkphonenumber);
        textView_phonenumber.setVisibility(View.GONE);
        String Message =editText_phonenumber.getText().toString();
        String file_name = "Phone Number";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name,MODE_PRIVATE);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Message Saved is "+Message, Toast.LENGTH_LONG).show();
            editText_phonenumber.setText("");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void checkphonenumber (View view){

        //editText_phonenumber = (EditText) findViewById(R.id.editText);
        //textView_phonenumber = (TextView) findViewById(R.id.checkphonenumber);
        textView_phonenumber.setVisibility(View.GONE);
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("Phone Number");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            try {
                while ( (Message =bufferedReader.readLine())!=null){

                    stringBuffer.append(Message + "\n");
                }
                Toast.makeText(getApplicationContext(),"Message Displayed " + stringBuffer, Toast.LENGTH_LONG).show();
                textView_phonenumber.setText(stringBuffer.toString());
                textView_phonenumber.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
