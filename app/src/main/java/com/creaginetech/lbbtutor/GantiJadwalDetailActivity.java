package com.creaginetech.lbbtutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.creaginetech.lbbtutor.Common.Common;
import com.creaginetech.lbbtutor.Model.Jadwal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GantiJadwalDetailActivity extends AppCompatActivity {

    private TextView txtNama, txtTutor, txtJurusan, txtGrade, txtHarga, txtTanggal, txtJam, txtHari,
            txtRuangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_jadwal_detail);
        getSupportActionBar().setTitle("Ganti Jadwal Detail");

        widgetInit();

        getDataJadwal();

    }

    private void widgetInit(){

        txtNama = findViewById(R.id.textViewNamaSiswaDetail);
        txtTutor = findViewById(R.id.textViewNamaTutorDetail);
        txtJurusan = findViewById(R.id.textViewJurusanDetail);
        txtGrade = findViewById(R.id.textViewGradeDetail);
        txtHarga = findViewById(R.id.textViewHargaDetail);
        txtTanggal = findViewById(R.id.textViewTanggalDetail);
        txtJam = findViewById(R.id.textViewJamDetail);
        txtHari = findViewById(R.id.textViewHariDetail);
        txtRuangan = findViewById(R.id.textViewRuanganDetail);


    }

    private void getDataJadwal(){

                txtNama.setText(Common.jadwalGantiSelected.getNamaSiswa());
                txtTutor.setText(Common.jadwalGantiSelected.getTutor());
                txtJurusan.setText(Common.jadwalGantiSelected.getJurusan());
                txtGrade.setText(Common.jadwalGantiSelected.getGrade());
                txtHarga.setText(Common.jadwalGantiSelected.getHarga());
                txtTanggal.setText(Common.jadwalGantiSelected.getTanggal());
                txtJam.setText(Common.jadwalGantiSelected.getJam());
                txtHari.setText(Common.jadwalGantiSelected.getHari());
                txtRuangan.setText(Common.jadwalGantiSelected.getRuang());

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
