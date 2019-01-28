package com.example.bansal.baabae;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bansal.baabae.Models.OrderSummary;
import com.example.bansal.baabae.Models.SignUpForm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    public static  String Name;
    EditText number;
    EditText password;
   public static ArrayList<OrderSummary> recentOrders;
    Button login;
    SignUpForm userForm;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        number = findViewById(R.id.editText);
        Button signup=findViewById(R.id.button16);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),SignUpActivity1.class);
                startActivity(i);
            }
        });
        password=findViewById(R.id.editText2);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");

        login=findViewById(R.id.button3);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference myRef = database.getReference("users");
                //.child(number.getText().toString());

                myRef.child(number.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        try {
                            if (snapshot.child("password").getValue() != null) {
                                try {
                                    if(snapshot.child("password").getValue().equals( password.getText().toString()))
                                    {
                                       String name = snapshot.child("name").getValue().toString();
                                        Object value = (HashMap) snapshot.getValue();
                                      //  final Object value = ((HashMap) val).get(number.getText().toString());
                                        Object number = ((HashMap) value).get("number");
                                        userForm=new SignUpForm();
                                        userForm.setPassword((String) ((HashMap) value).get("password"));
                                        userForm.setEmailId((String) ((HashMap) value).get("emailId"));
                                        userForm.setCourseName((String) ((HashMap) value).get("courseName"));
                                        userForm.setCollegeName((String) ((HashMap) value).get("collegeName"));
                                        userForm.setName((String) ((HashMap) value).get("name"));
                                        userForm.setNumber((String) ((HashMap) value).get("number"));

                                        recentOrders=new ArrayList<OrderSummary>();
                                        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                                        i.putExtra("user",userForm);
                                        i.putExtra("name",name);
                                        i.putExtra("recent",recentOrders);
                                        startActivity(i);
                                        finish();
                                        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
                                        //userForm=(SignUpForm)snapshot.getValue().getClass();

                                    }
                                    else
                                        Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                               //     Log.e("TAG", "" + snapshot.getValue()); // your name values you will get here
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                                Log.e("TAG", " it's null.");
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }
        });
    }
}
