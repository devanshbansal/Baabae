package com.example.bansal.baabae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button login;
Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.button);
        signUp=findViewById(R.id.button2);
           }

           public void Login(View v){
           Intent i= new Intent(getApplicationContext(),LoginActivity.class);
           startActivity(i);
           finish();

           }

           public void SignUp(View v){
        Intent i = new Intent(getApplicationContext(),SignUpActivity1.class);
        startActivity(i);
               overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
           }
}
