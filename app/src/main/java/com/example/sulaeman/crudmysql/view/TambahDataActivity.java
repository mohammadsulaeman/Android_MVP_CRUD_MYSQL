package com.example.sulaeman.crudmysql.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sulaeman.crudmysql.R;
import com.example.sulaeman.crudmysql.presenter.AddDataMahasiswaPresenter;
import com.example.sulaeman.crudmysql.presenter.AddDataMahasiswaView;

public class TambahDataActivity extends AppCompatActivity implements AddDataMahasiswaView {
    ImageView backBtn;
    EditText nama,nim,alamat,phone;
    AddDataMahasiswaPresenter presenter;
    ProgressBar progressBar;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        backBtn = (ImageView) findViewById(R.id.img_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(TambahDataActivity.this, MainActivity.class);
                startActivity(main);
            }
        });
        presenter = new AddDataMahasiswaPresenter(this);
        nama = (EditText) findViewById(R.id.nama_siswa_add);
        nim = (EditText) findViewById(R.id.nim_siswa_add);
        alamat = (EditText) findViewById(R.id.alamat_siswa_add);
        phone = (EditText) findViewById(R.id.phone_siswa_add);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_add);
        presenter = new AddDataMahasiswaPresenter(this);
        submit = (Button) findViewById(R.id.btn_add_siswa);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nama.getText().toString())){
                    Toast.makeText(TambahDataActivity.this, "nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(nim.getText().toString())){
                    Toast.makeText(TambahDataActivity.this, "nim tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(alamat.getText().toString())){
                    Toast.makeText(TambahDataActivity.this, "alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(TambahDataActivity.this, "phone tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    String tv_nama = nama.getText().toString();
                    String tv_nim = nim.getText().toString();
                    String tv_alamat = alamat.getText().toString();
                    String tv_phone = phone.getText().toString();
                    presenter.saveItemData(tv_nama,tv_nim,tv_alamat,tv_phone);
                    Intent main = new Intent(TambahDataActivity.this,MainActivity.class);
                    startActivity(main);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRequestSuccess(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onRequestError(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}