package com.creaginetech.lbbtutor.VIewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creaginetech.lbbtutor.Interface.ItemClickListener;
import com.creaginetech.lbbtutor.R;

public class JadwalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView siswaNama, siswaGrade, siswaJurusan, siswaJam, siswaHari;

    private ItemClickListener itemClickListener;

    public JadwalViewHolder(View itemView) {
        super(itemView);

        siswaNama = itemView.findViewById(R.id.textViewNamaSiswaJadwal);
        siswaGrade = itemView.findViewById(R.id.textViewGradeSiswaJadwal);
        siswaJurusan = itemView.findViewById(R.id.textViewJurusanSiswaJadwal);
        siswaJam = itemView.findViewById(R.id.textViewJamSiswaJadwal);
        siswaHari = itemView.findViewById(R.id.textViewHariSiswaJadwal);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

}
