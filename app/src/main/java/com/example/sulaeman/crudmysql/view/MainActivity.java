package com.example.sulaeman.crudmysql.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sulaeman.crudmysql.R;
import com.example.sulaeman.crudmysql.model.Mahasiswa;
import com.example.sulaeman.crudmysql.model.MahasiswaAdapter;
import com.example.sulaeman.crudmysql.presenter.GetDataMahasiswaPresenter;
import com.example.sulaeman.crudmysql.presenter.GetDataMahasiswaView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GetDataMahasiswaView {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    GetDataMahasiswaPresenter presenter;
    MahasiswaAdapter adapter;
    List<Mahasiswa> mahasiswa;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        progressBar = (ProgressBar) findViewById(R.id.progress_Bar);
        recyclerView = (RecyclerView) findViewById(R.id.list_siswa_data);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this,TambahDataActivity.class);
                startActivity(add);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        presenter = new GetDataMahasiswaPresenter(this);
        presenter.getData();


    }



    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetResult(List<Mahasiswa> mahasiswas) {
        adapter = new MahasiswaAdapter(this,mahasiswas);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mahasiswa = mahasiswas;
    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, "Data Berhasil Di Ambil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorLoading(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}