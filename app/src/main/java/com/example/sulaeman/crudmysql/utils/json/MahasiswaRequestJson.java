package com.example.sulaeman.crudmysql.utils.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MahasiswaRequestJson {
    @Expose
    @SerializedName("id_siswa")
    private String id;

    @Expose
    @SerializedName("nama_siswa")
    private String nama;

    @Expose
    @SerializedName("nim_siswa")
    private String nim;

    @SerializedName("alamat_siswa")
    @Expose
    private String alamat;

    @Expose
    @SerializedName("phone_siswa")
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
