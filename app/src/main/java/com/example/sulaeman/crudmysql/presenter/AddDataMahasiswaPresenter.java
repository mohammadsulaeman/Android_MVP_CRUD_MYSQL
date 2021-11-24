package com.example.sulaeman.crudmysql.presenter;

import com.example.sulaeman.crudmysql.utils.api.ServiceGenerator;
import com.example.sulaeman.crudmysql.utils.api.service.MahasiswaService;
import com.example.sulaeman.crudmysql.utils.json.MahasiswaRequestJson;
import com.example.sulaeman.crudmysql.utils.json.ResponseJson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDataMahasiswaPresenter {
    private AddDataMahasiswaView view;

    public AddDataMahasiswaPresenter(AddDataMahasiswaView view) {
        this.view = view;
    }

    public void saveItemData(String nama,String nim,String alamat,String phone){
        view.showProgress();
        MahasiswaRequestJson request = new MahasiswaRequestJson();
        request.setNama(nama);
        request.setNim(nim);
        request.setAlamat(alamat);
        request.setPhone(phone);
        MahasiswaService service = ServiceGenerator.createService(MahasiswaService.class,request.getNama(),request.getNim());
        service.saveSiswa(request).enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                if (response.isSuccessful()){
                    view.hideProgress();
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
