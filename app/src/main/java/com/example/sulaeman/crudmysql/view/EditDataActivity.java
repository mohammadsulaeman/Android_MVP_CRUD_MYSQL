package com.example.sulaeman.crudmysql.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sulaeman.crudmysql.R;
import com.example.sulaeman.crudmysql.databinding.ActivityEditDataBinding;
import com.example.sulaeman.crudmysql.presenter.EditDeleteDataMahasiswaPresenter;
import com.example.sulaeman.crudmysql.presenter.EditDeleteDataMahasiswaView;

public class EditDataActivity extends AppCompatActivity implements EditDeleteDataMahasiswaView {

    String iddata, namadata, nimdata, alamatdata, phonedata;
    ActivityEditDataBinding binding;
    EditDeleteDataMahasiswaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //mengambil data sesuai dengan id yg di klik di halaman home
        Intent intent = getIntent();
        iddata = intent.getStringExtra("id_siswa");
        namadata = intent.getStringExtra("nama_siswa");
        nimdata = intent.getStringExtra("nim_siswa");
        alamatdata = intent.getStringExtra("alamat_siswa");
        phonedata = intent.getStringExtra("phone_siswa");

        binding.imgBackEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditDataActivity.this,MainActivity.class));
                finish();
            }
        });
        binding.namaSiswaEdit.setText(namadata);
        binding.nimSiswaEdit.setText(nimdata);
        binding.alamatSiswaEdit.setText(alamatdata);
        binding.phoneSiswaEdit.setText(phonedata);

        presenter = new EditDeleteDataMahasiswaPresenter(this);
        binding.btnEditSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditDataActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage("Apa Anda Yakin Akan Merubah Data ini?");
                builder.setPositiveButton("IYA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateDataSiswa();
                        dialog.dismiss();
                    }
                }).setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        binding.btnHapusSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditDataActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage("Apa Anda Yakin Akan Melakukan proses penghapusan data pada data ini ?");
                builder.setPositiveButton("IYA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteData(iddata);
                        Intent main = new Intent(EditDataActivity.this,MainActivity.class);
                        startActivity(main);
                        dialog.dismiss();
                    }
                }).setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }

        });
    }

    private void updateDataSiswa() {
        String namasiswa = binding.namaSiswaEdit.getText().toString();
        String nimsiswa = binding.nimSiswaEdit.getText().toString();
        String alamatsiswa = binding.alamatSiswaEdit.getText().toString();
        String phonesiswa = binding.phoneSiswaEdit.getText().toString();
         if (TextUtils.isEmpty(namasiswa)) {
            Toast.makeText(this, "nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(nimsiswa)) {
            Toast.makeText(this, "nim tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(alamatsiswa)) {
            Toast.makeText(this, "alamat tida boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phonesiswa)) {
            Toast.makeText(this, "phone tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            presenter.updateData(iddata,namasiswa,nimsiswa,alamatsiswa,phonesiswa);
             Intent main = new Intent(EditDataActivity.this,MainActivity.class);
             startActivity(main);
        }

    }



    @Override
    public void showProgress() {
        binding.progressBarEdit.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.progressBarEdit.setVisibility(View.GONE);
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