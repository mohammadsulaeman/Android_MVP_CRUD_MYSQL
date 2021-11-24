package com.example.sulaeman.crudmysql.utils.api.service;

import com.example.sulaeman.crudmysql.utils.json.DeleteMahasiswaRequetsJson;
import com.example.sulaeman.crudmysql.utils.json.GetDataMahasiswaResponseJson;
import com.example.sulaeman.crudmysql.utils.json.MahasiswaRequestJson;
import com.example.sulaeman.crudmysql.utils.json.ResponseJson;
import com.example.sulaeman.crudmysql.utils.json.UpdateRequestJson;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface MahasiswaService {

    @POST("mahasiswaapi/tambahdata")
    Call<ResponseJson> saveSiswa (@Body MahasiswaRequestJson param);

    @GET("mahasiswaapi/lihatdata")
    Call<GetDataMahasiswaResponseJson> getDataSiswa();

    @POST("mahasiswaapi/edit_mahasiswa")
    Call<ResponseJson> editmahasiswa (@Body UpdateRequestJson param);

    @POST("mahasiswaapi/delete_mahasiswa")
    Call<ResponseJson> deletemahasiswa (@Body DeleteMahasiswaRequetsJson param);

}

