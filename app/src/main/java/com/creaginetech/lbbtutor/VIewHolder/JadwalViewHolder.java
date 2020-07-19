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
        siswaGrade = itemView.findViewById(R.id.textViewGradeJadwal);
        siswaJurusan = itemView.findViewById(R.id.textViewJurusanJadwal);
        siswaJam = itemView.findViewById(R.id.textViewJamJadwal);
        siswaHari = itemView.findViewById(R.id.textViewHariJadwal);

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
