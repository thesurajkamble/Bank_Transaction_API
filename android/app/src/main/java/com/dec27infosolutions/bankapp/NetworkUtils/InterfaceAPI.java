package com.dec27infosolutions.bankapp.NetworkUtils;

import com.dec27infosolutions.bankapp.Models.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InterfaceAPI {
    String API_ROUTE = "register";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
     Call<Register> RegisterNewUser(@Body Register register);
}
