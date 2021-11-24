package com.example.sulaeman.crudmysql.presenter;


import com.example.sulaeman.crudmysql.model.Mahasiswa;
import com.example.sulaeman.crudmysql.utils.api.ServiceGenerator;
import com.example.sulaeman.crudmysql.utils.api.service.MahasiswaService;
import com.example.sulaeman.crudmysql.utils.json.GetDataMahasiswaResponseJson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataMahasiswaPresenter {
    private GetDataMahasiswaView view;

    public GetDataMahasiswaPresenter(GetDataMahasiswaView view){
        this.view = view;
    }

    public void getData(){
        view.showLoading();
        MahasiswaService service = ServiceGenerator.createService(MahasiswaService.class,"admin","admin");
        service.getDataSiswa().enqueue(new Callback<GetDataMahasiswaResponseJson>() {
            @Override
            public void onResponse(Call<GetDataMahasiswaResponseJson> call, Response<GetDataMahasiswaResponseJson> response) {
                if (response.isSuccessful()){
                    view.hideLoading();
                    view.onGetResult(response.body().getMahasiswas());
                    view.onSuccess(response.body().getStatus());
                }else{
                    view.onErrorLoading("error!");
                }
            }

            @Override
            public void onFailure(Call<GetDataMahasiswaResponseJson> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
