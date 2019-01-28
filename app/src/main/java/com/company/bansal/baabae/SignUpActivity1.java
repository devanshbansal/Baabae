package com.company.bansal.baabae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.company.bansal.baabae.Models.SignUpForm;
import java.io.Serializable;

public class SignUpActivity1 extends AppCompatActivity {
Button next;
EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);
        final SignUpForm form = new SignUpForm();
        next=findViewById(R.id.button4);
        text=findViewById(R.id.editText3);
        final RadioGroup gender=findViewById(R.id.gender);
        next.setVisibility(View.INVISIBLE);
        final RadioButton male=findViewById(R.id.radioButton);
        final RadioButton female=findViewById(R.id.radioButton2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = gender.getCheckedRadioButtonId();
                if(selectedId==-1)
                {
                    Toast.makeText(getApplicationContext(),"Please select the gender",Toast.LENGTH_SHORT).show();
                    return;
                }

                // find the radiobutton by returned id
                RadioButton r = (RadioButton) findViewById(selectedId);
                form.setGender(r.getText().toString());
                form.setName(text.getText().toString());
                Intent i = new Intent(getApplicationContext(),SignUpActivity2.class);
                i.putExtra("FORM", (Serializable) form);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
                finish();
            }
        });
        text.addTextChangedListener(new TextWatcher() {
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


