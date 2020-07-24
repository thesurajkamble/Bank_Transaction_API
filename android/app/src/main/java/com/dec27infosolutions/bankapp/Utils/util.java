package com.dec27infosolutions.bankapp.Utils;

import android.widget.AutoCompleteTextView;

public  class util {
    private AutoCompleteTextView email, password, username, confirm_pass;

    public  util(){}

    public util(AutoCompleteTextView username, AutoCompleteTextView password, AutoCompleteTextView confirm_pass, AutoCompleteTextView email) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.confirm_pass = confirm_pass;
    }



    public boolean CheckForEmptyInput() {
        return username.length() == 0 || password.length() == 0 || confirm_pass.length() == 0 || email.length() == 0;
    }

    public boolean checkPasswords()
    {
        return password.toString().equals(confirm_pass.toString());
    }


}
