package com.example.sulaeman.crudmysql.presenter;

import com.example.sulaeman.crudmysql.model.Mahasiswa;

import java.util.List;

public interface GetDataMahasiswaView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Mahasiswa> mahasiswas);
    void onSuccess(String msg);
    void onErrorLoading(String status);
}
