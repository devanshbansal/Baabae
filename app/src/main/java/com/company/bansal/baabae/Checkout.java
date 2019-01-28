package com.company.bansal.baabae;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Checkout extends AppCompatActivity {
Button cancel,Home;
EditText t1,t2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Home=findViewById(R.id.button9);
        t1=findViewById(R.id.editText9);
        t2=findViewById(R.id.editText10);
        t1.setEnabled(false);
        t2.setEnabled(false);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
