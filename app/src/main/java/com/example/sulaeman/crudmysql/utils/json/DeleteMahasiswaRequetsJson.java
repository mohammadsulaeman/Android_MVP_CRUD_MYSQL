package com.example.sulaeman.crudmysql.utils.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteMahasiswaRequetsJson {

    @Expose
    @SerializedName("id_siswa")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
