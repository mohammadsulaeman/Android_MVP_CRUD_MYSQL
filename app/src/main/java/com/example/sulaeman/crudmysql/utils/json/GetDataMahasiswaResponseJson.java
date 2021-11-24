package com.example.sulaeman.crudmysql.utils.json;

import com.example.sulaeman.crudmysql.model.Mahasiswa;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetDataMahasiswaResponseJson {
    @Expose
    @SerializedName("home")
    private List<Mahasiswa> mahasiswas = new ArrayList<>();


    @Expose
    @SerializedName("status")
    private String status;

    public List<Mahasiswa> getMahasiswas() {
        return mahasiswas;
    }

    public void setMahasiswas(List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
