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
    private ProgressBar progressBar;
    private DatabaseReference jadwalRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_jadwal_detail);

        //TODO test detail ganti jadwal

        widgetInit();

        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");

        getDataSiswa();

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

    private void getDataSiswa() {


        jadwalRef.child(Common.jadwalSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Jadwal jadwal = dataSnapshot.getValue(Jadwal.class);

                txtNama.setText(jadwal.getNamaSiswa());
                txtTutor.setText(jadwal.getTutor());
                txtJurusan.setText(jadwal.getJurusan());
                txtGrade.setText(jadwal.getGrade());
                txtHarga.setText(jadwal.getHarga());
                txtTanggal.setText(jadwal.getTanggal());
                txtJam.setText(jadwal.getJam());
                txtHari.setText(jadwal.getHari());
                txtRuangan.setText(jadwal.getRuang());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
