package com.example.android.qcbeta01;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsOfApp extends Fragment {

    EditText editText_phonenumber;
    TextView textView_phonenumber;

    public SettingsOfApp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_of_app, container, false);
        // Inflate the layout for this fragment
//        editText_phonenumber = (EditText) view.findViewById(R.id.editText);
//        textView_phonenumber = (TextView) view.findViewById(R.id.checkphonenumber);
//        textView_phonenumber.setVisibility(View.GONE);
        return inflater.inflate(R.layout.fragment_settings_of_app, container, false);
    }





}
