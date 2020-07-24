package com.dec27infosolutions.bankapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.dec27infosolutions.bankapp.R;

public class RegisterActivity extends AppCompatActivity {

    AutoCompleteTextView username, emailreg , pass, confirm_pass ;
    Button btnSignup ;
    TextView tvSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = findViewById(R.id.atvUsernameReg);
        emailreg = findViewById(R.id.atvEmailLog);
        pass = findViewById(R.id.atvPasswordLog);
        confirm_pass = findViewById(R.id.atvPasswordConfirm);


        
    }
}
