package com.example.sulaeman.crudmysql.presenter;

public interface AddDataMahasiswaView {
    void showProgress();
    void hideProgress();
    void onRequestSuccess(String status);
    void onRequestError(String status);
}
