package com.example.currencychange.apis;

import com.example.currencychange.models.CurrencyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServece {

      @GET("latest")
    Call<CurrencyResponse> getCurrencySourses(@Query("access_key") String key,
                                              @Query("base") String base);

}
