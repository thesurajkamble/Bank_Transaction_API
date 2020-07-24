package com.dec27infosolutions.bankapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dec27infosolutions.bankapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_btn = findViewById(R.id.btnSignIn);
        TextView register_tv = findViewById(R.id.tv_createAcc);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });


        register_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tvintent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(tvintent);
            }
        });

    }
}
