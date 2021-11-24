package com.example.sulaeman.crudmysql.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.sulaeman.crudmysql.utils.api.ServiceGenerator;
import com.example.sulaeman.crudmysql.utils.api.service.MahasiswaService;
import com.example.sulaeman.crudmysql.utils.json.DeleteMahasiswaRequetsJson;
import com.example.sulaeman.crudmysql.utils.json.ResponseJson;
import com.example.sulaeman.crudmysql.utils.json.UpdateRequestJson;
import com.example.sulaeman.crudmysql.view.EditDataActivity;
import com.example.sulaeman.crudmysql.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDeleteDataMahasiswaPresenter {
    private EditDeleteDataMahasiswaView view;
    private Context context;
    public EditDeleteDataMahasiswaPresenter(EditDeleteDataMahasiswaView view) {
        this.view = view;
    }

    public void updateData(String id, String nama,String nim,String alamat,String phone){
        view.showProgress();
        UpdateRequestJson request = new UpdateRequestJson();
        request.setId(id);
        request.setNama(nama);
        request.setNim(nim);
        request.setAlamat(alamat);
        request.setPhone(phone);
        MahasiswaService service = ServiceGenerator.createService(MahasiswaService.class,request.getNama(),request.getNim());
        service.editmahasiswa(request).enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                view.hideProgress();
                if (response.isSuccessful()){
                    if (response.body().getStatus().equalsIgnoreCase("success")){
                        view.onRequestSuccess(response.body().getStatus());

                    }else{
                        view.onRequestError(response.body().getStatus());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseJson> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
    public void deleteData(String id){
        view.showProgress();
        DeleteMahasiswaRequetsJson request = new DeleteMahasiswaRequetsJson();
        request.setId(id);
        MahasiswaService service = ServiceGenerator.createService(MahasiswaService.class,"admin","admin");
        service.deletemahasiswa(request).enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                view.hideProgress();
                if (response.isSuccessful()){
                    if (response.body().getStatus().equalsIgnoreCase("success")){
                        view.onRequestSuccess(response.body().getStatus());
                    }else{
                        view.onRequestError(response.body().getStatus());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseJson> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}
