package com.example.movie2app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity  {
    EditText emailEt,passwordEt;
    TextView registerTv;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setReferencesView();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                checkueser();


            }
        });

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



    public void setReferencesView(){
        emailEt=findViewById(R.id.editText3);
        passwordEt=findViewById(R.id.editText4);
        registerTv=findViewById(R.id.textView14);
        loginBtn=findViewById(R.id.button2);

    }


    private void checkueser()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("moviesData" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();



        String email = sharedPreferences.getString("email","error");
         String password= sharedPreferences.getString("password","error");


        String emailUser = emailEt.getText().toString();
        String passwordUser= passwordEt.getText().toString();

        if(emailUser.equals(email) && passwordUser.equals(password) )
        {
            Toast.makeText(this,"Login Successfully",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(LoginActivity.this,NavDrawerActivity.class);
            startActivity(intent);
            finish();


        }
       else if (emailUser.equals("") && passwordUser.equals("") ){
            Toast.makeText(this,"Enter Email and password",Toast.LENGTH_LONG).show();

        }
        else if (emailUser!=(email) && passwordUser!=(password) ){
            Toast.makeText(this,"قم بالتسجيل للحصول على حساب",Toast.LENGTH_LONG).show();
        }



    }




}
