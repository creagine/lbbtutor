package com.creaginetech.lbbtutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.creaginetech.lbbtutor.Common.Common;

public class GantiJadwalLihatActivity extends AppCompatActivity {

    private TextView txtNama, txtTutor, txtTanggal, txtJam, txtHari,
            txtRuangan, txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_jadwal_lihat);
        getSupportActionBar().setTitle("Ganti Jadwal Detail");

        widgetInit();

        getDataJadwal();
    }

    private void widgetInit(){

        txtNama = findViewById(R.id.textViewNamaSiswaLihat);
        txtTutor = findViewById(R.id.textViewNamaTutorLihat);
        txtTanggal = findViewById(R.id.textViewTanggalLihat);
        txtJam = findViewById(R.id.textViewJamLihat);
        txtHari = findViewById(R.id.textViewHariLihat);
        txtRuangan = findViewById(R.id.textViewRuanganLihat);
        txtStatus = findViewById(R.id.textViewStatusLihat);


    }

    private void getDataJadwal(){

        txtNama.setText(Common.jadwalPenggantiSelected.getSiswa());
        txtTutor.setText(Common.jadwalPenggantiSelected.getTutor());
        txtTanggal.setText(Common.jadwalPenggantiSelected.getTanggal());
        txtJam.setText(Common.jadwalPenggantiSelected.getJam());
        txtHari.setText(Common.jadwalPenggantiSelected.getHari());
        txtRuangan.setText(Common.jadwalPenggantiSelected.getRuang());
        txtStatus.setText(Common.jadwalPenggantiSelected.getStatus());

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
