package br.com.objetiveti.pedidodevendas.Services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ServiceAPI {

    @GET("json.testing")
    Call<ResponseBody> getTesting();

    @GET("json_produto.txt")
    Call<ResponseBody> getProdutos();

    Retrofit RETROFIT = new Retrofit
            .Builder()
            .baseUrl("http://objetiveti.dyndns.org:2242/fv/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
