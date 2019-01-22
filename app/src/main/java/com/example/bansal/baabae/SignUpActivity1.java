package com.example.bansal.baabae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.bansal.baabae.Models.SignUpForm;
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
        next.setVisibility(View.INVISIBLE);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


