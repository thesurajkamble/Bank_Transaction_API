package com.dec27infosolutions.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.dec27infosolutions.bankapp.Models.Register;
import com.dec27infosolutions.bankapp.NetworkUtils.InterfaceAPI;
import com.dec27infosolutions.bankapp.R;
import com.dec27infosolutions.bankapp.Utils.util;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    AutoCompleteTextView username, emailreg, pass, confirm_pass;
    Button btnSignup;
    TextView tvSignin;
    CoordinatorLayout coordinatorLayout;
    private InterfaceAPI interfaceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        util util = new util(username, pass, confirm_pass, emailreg);

        if (util.CheckForEmptyInput()) {
            setInputEmptyError();
        }

        if (!util.checkPasswords()) {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Password Mismatch!!", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            interfaceAPI = retrofit.create(InterfaceAPI.class);
            RegisterNewUser();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void RegisterNewUser() {
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
        username = findViewById(R.id.atvUsernameReg);
        emailreg = findViewById(R.id.atvEmailLog);
        pass = findViewById(R.id.atvPasswordLog);
        confirm_pass = findViewById(R.id.atvPasswordConfirm);


    }

    private void setInputEmptyError() {
        username.setError("required");
        pass.setError("required");
        confirm_pass.setError("required");
        emailreg.setError("required");
    }


}
