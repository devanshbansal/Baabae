package com.example.bansal.baabae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bansal.baabae.Models.SignUpForm;

import java.io.Serializable;

public class SignUpActivity3 extends AppCompatActivity {
SignUpForm form;
Button next;
EditText text;
EditText text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);
        text=findViewById(R.id.editText5);
        text1=findViewById(R.id.editText6);
        next=findViewById(R.id.button6);
        next.setVisibility(View.INVISIBLE);
        form=(SignUpForm)getIntent().getSerializableExtra("FORM");
       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               form.setCollegeName(text.getText().toString());
               form.setCourseName(text1.getText().toString());
               Intent i = new Intent(getApplicationContext(),SignUpActivity4.class);
               i.putExtra("FORM", (Serializable) form);
               startActivity(i);
               overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
               finish();
           }
       });
        text1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0)
                    next.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
