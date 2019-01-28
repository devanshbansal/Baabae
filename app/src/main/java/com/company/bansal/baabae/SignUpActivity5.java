package com.company.bansal.baabae;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.company.bansal.baabae.Models.SignUpForm;

public class SignUpActivity5 extends AppCompatActivity {
SignUpForm form;
Button next;
EditText text;
EditText text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up5);
        form=(SignUpForm)getIntent().getSerializableExtra("FORM");

    }
}
