package com.creaginetech.lbbtutor.VIewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creaginetech.lbbtutor.Interface.ItemClickListener;
import com.creaginetech.lbbtutor.R;

public class FeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView no, namaSiswa, bulan, tahun, jurusan, grade, tarif, fee, presensi,
            tglSpp;

    private ItemClickListener itemClickListener;

    public FeeViewHolder(View itemView) {
        super(itemView);

        no = itemView.findViewById(R.id.textViewNoFeeItem);
        namaSiswa = itemView.findViewById(R.id.textViewNamaSiswaItem);
        bulan = itemView.findViewById(R.id.textViewBulanItem);
        tahun = itemView.findViewById(R.id.textViewTahunItem);
        jurusan = itemView.findViewById(R.id.textViewJurusanItem);
        tglSpp = itemView.findViewById(R.id.textViewTglSppItem);

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
