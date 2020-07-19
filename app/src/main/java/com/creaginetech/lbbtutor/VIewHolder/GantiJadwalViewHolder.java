package com.creaginetech.lbbtutor.VIewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creaginetech.lbbtutor.Interface.ItemClickListener;
import com.creaginetech.lbbtutor.R;

public class GantiJadwalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView siswa, tutor, tanggal, jam, hari, ruang, status;

    private ItemClickListener itemClickListener;

    public GantiJadwalViewHolder(View itemView) {
        super(itemView);

        siswa = itemView.findViewById(R.id.textViewNamaSiswaGantiJadwal);
        tanggal = itemView.findViewById(R.id.textViewTanggalGantiJadwal);
        jam = itemView.findViewById(R.id.textViewJamGantiJadwal);
        hari = itemView.findViewById(R.id.textViewHariGantiJadwal);
        ruang = itemView.findViewById(R.id.textViewRuangGantiJadwal);
        status = itemView.findViewById(R.id.textViewStatusGantiJadwal);

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
