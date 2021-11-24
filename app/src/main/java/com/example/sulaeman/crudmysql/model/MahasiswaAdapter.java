package com.example.sulaeman.crudmysql.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sulaeman.crudmysql.R;
import com.example.sulaeman.crudmysql.view.EditDataActivity;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ItemRowHolder>
{
    public Context context;
    private List<Mahasiswa> mahasiswas;

    public MahasiswaAdapter(Context context, List<Mahasiswa> mahasiswas) {
        this.context = context;
        this.mahasiswas = mahasiswas;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_layout,parent,false);
        return new ItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MahasiswaAdapter.ItemRowHolder holder, int position) {
        final Mahasiswa mahasiswa = mahasiswas.get(position);
        holder.nama.setText(mahasiswa.getNama());
        holder.nim.setText(mahasiswa.getNim());
        holder.alamat.setText(mahasiswa.getAlamat());
        holder.phone.setText(mahasiswa.getPhone());
        holder.lineKlick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(context, EditDataActivity.class);
                edit.putExtra("id_siswa",mahasiswa.getId());
                edit.putExtra("nama_siswa",mahasiswa.getNama());
                edit.putExtra("nim_siswa",mahasiswa.getNim());
                edit.putExtra("alamat_siswa",mahasiswa.getAlamat());
                edit.putExtra("phone_siswa",mahasiswa.getPhone());
                edit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(edit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mahasiswas ? mahasiswas.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder{

        TextView nama,nim,alamat,phone;
        LinearLayout lineKlick;
        public ItemRowHolder(@NonNull  View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama_siswa_row);
            nim = itemView.findViewById(R.id.nim_siswa_row);
            alamat = itemView.findViewById(R.id.alamat_siswa_row);
            phone = itemView.findViewById(R.id.phone_siswa_row);
            lineKlick = itemView.findViewById(R.id.line_klik_btn);
        }


    }


}
