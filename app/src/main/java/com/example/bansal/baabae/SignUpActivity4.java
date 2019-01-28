package com.example.bansal.baabae;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bansal.baabae.Models.SignUpForm;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class SignUpActivity4 extends AppCompatActivity {
SignUpForm form;
Button next;
EditText text;
EditText text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up4);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
         final DatabaseReference myRef = database.getReference("users");
        text=findViewById(R.id.editText7);
        text1=findViewById(R.id.editText8);
        final CheckBox accept=findViewById(R.id.radioButton3);
        next=findViewById(R.id.button7);
        TextView terms=findViewById(R.id.textView2);
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.baabae.in");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        next.setVisibility(View.INVISIBLE);
        form=(SignUpForm)getIntent().getSerializableExtra("FORM");
       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(accept.isChecked()==false)
               {
                   Toast.makeText(getApplicationContext(),"Please accept the terms and conditions to continue",Toast.LENGTH_SHORT).show();
                   return;
               }
               if(!isNetworkAvailable())
               {
                   Toast.makeText(getApplicationContext(),"Please check your Internet Connection",Toast.LENGTH_SHORT).show();
                   return;
               }
               if(!isValidMail(text.getText().toString())){
                   Toast.makeText(getApplicationContext(),"Please enter a valid Email address",Toast.LENGTH_SHORT).show();
                   text.setText("");
                   return;
               }
               form.setEmailId(text.getText().toString());
               form.setPassword(text1.getText().toString());
               myRef.child(form.getNumber()).setValue(form, new DatabaseReference.CompletionListener() {
                   @Override
                   public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                       if(databaseError!=null){
                           Toast.makeText(getApplicationContext(),"Account couldn't be created. Please check the Internet Connection and try again",Toast.LENGTH_SHORT).show();
                       return;
                       }
                       else
                       {
                           Toast.makeText(getApplicationContext(),"Account Created Successfully",Toast.LENGTH_SHORT).show();
                           Intent i = new Intent(getApplicationContext(),MainActivity.class);
                           i.putExtra("FORM", (Serializable) form);
                           startActivity(i);
                           overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
                           finish();
                       }
                   }
               });

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
    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
