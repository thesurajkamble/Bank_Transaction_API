package com.dec27infosolutions.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dec27infosolutions.bankapp.Models.Register;
import com.dec27infosolutions.bankapp.NetworkUtils.InterfaceAPI;
import com.dec27infosolutions.bankapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    AutoCompleteTextView username, emailreg, pass, confirm_pass;
    Button btnSignup;
    TextView tvSignin;
    //    CoordinatorLayout coordinatorLayout;
    private InterfaceAPI interfaceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.atvUsernameReg);
        emailreg = findViewById(R.id.atvEmailLog);
        pass = findViewById(R.id.atvPasswordLog);
        confirm_pass = findViewById(R.id.atvPasswordConfirm);
        btnSignup = findViewById(R.id.btnSignUp);
        tvSignin = findViewById(R.id.tvForgotPass);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "on click", Toast.LENGTH_LONG).show();
                try {
                    Toast.makeText(getApplicationContext(), "retrofit ", Toast.LENGTH_LONG).show();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://flask-bank-api-1.herokuapp.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    interfaceAPI = retrofit.create(InterfaceAPI.class);
                    Toast.makeText(getApplicationContext(), "before call", Toast.LENGTH_LONG).show();
                    makeNetworkCall();
                    Toast.makeText(getApplicationContext(), "after call", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /***
     * Oncreate ends here
     */

//    private void RegisterNewUser() {
//
//        util util = new util(username, pass, confirm_pass, emailreg);
//        if (!util.CheckForEmptyInput()) {
//            Toast.makeText(getApplicationContext(), "username password required", Toast.LENGTH_LONG).show();
//        }
//
//        if (!util.checkPasswords()) {
//            Toast.makeText(getApplicationContext(), "password mismatch", Toast.LENGTH_LONG).show();
//        }


    //}

    private void makeNetworkCall() {
        Toast.makeText(getApplicationContext(), "in make n/w call", Toast.LENGTH_LONG).show();
        String str_username, str_password, str_email;
        str_username = username.getText().toString();
        str_password = pass.getText().toString();
        str_email = emailreg.getText().toString();
        Register register = new Register();
        register.setUsername(str_username);
        register.setPassword(str_password);
        register.setEmail(str_email);
        Call<Register> call = interfaceAPI.RegisterNewUser(register);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "unable to regsiter user!!", Toast.LENGTH_SHORT)
                            .show();
                }
                if (response.code() == 200) {
                    RedirectNewUser();
                } else {
                    Toast.makeText(RegisterActivity.this, "Status Code" + response.code(), Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Exception " + t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void RedirectNewUser() {
        Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
        startActivity(intent);
    }

    private void findViews() {



    }
}
