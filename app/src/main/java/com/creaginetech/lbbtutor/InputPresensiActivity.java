package com.creaginetech.lbbtutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creaginetech.lbbtutor.Common.Common;
import com.creaginetech.lbbtutor.Model.Jadwal;
import com.creaginetech.lbbtutor.Model.Presensi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InputPresensiActivity extends AppCompatActivity {

    private EditText edtTanggalPresensi, edtBulanPresensi, edtTahunPresensi, edtMateri, edtKeterangan;
    private Button btnSubmit;

    private DatabaseReference jadwalRef, presensiRef;

    private String namaSiswa, namaTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_presensi);

        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");
        presensiRef = FirebaseDatabase.getInstance().getReference("Presensi");

        //TODO selesaikan input presensi
        widgetInit();

        getJadwal();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pushPresensi();

            }
        });

    }

    private void widgetInit() {

        edtTanggalPresensi = findViewById(R.id.editTextTanggal);
        edtBulanPresensi = findViewById(R.id.editTextBulan);
        edtTahunPresensi = findViewById(R.id.editTextTahun);
        edtMateri = findViewById(R.id.editTextMateri);
        edtKeterangan = findViewById(R.id.editTextKeterangan);
        btnSubmit = findViewById(R.id.buttonSubmitPresensi);

    }

    private void getJadwal() {

        jadwalRef.child(Common.jadwalSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Jadwal jadwalSelected = dataSnapshot.getValue(Jadwal.class);

                namaTutor = jadwalSelected.getTutor();
                namaSiswa = jadwalSelected.getNamaSiswa();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void pushPresensi() {

        String hariPresensi = edtTanggalPresensi.getText().toString();
        String bulanPresensi = edtBulanPresensi.getText().toString();
        String tahunPresensi = edtTahunPresensi.getText().toString();

        String tanggalPresensi = hariPresensi + "-" + bulanPresensi + "-" + tahunPresensi;
        String materi = edtMateri.getText().toString();
        String keterangan = edtKeterangan.getText().toString();

        if (TextUtils.isEmpty(tanggalPresensi)) {
            Toast.makeText(getApplicationContext(), "Masukkan tanggal!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(materi)) {
            Toast.makeText(getApplicationContext(), "Masukkan materi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(keterangan)) {
            Toast.makeText(getApplicationContext(), "Masukkan keterangan!", Toast.LENGTH_SHORT).show();
            return;
        }

        String presensiId = presensiRef.push().getKey();

        Presensi newPresensi = new Presensi(namaSiswa, namaTutor, Common.bulanSelected, Common.pekanSelected, tanggalPresensi, materi, keterangan);

        presensiRef.child(presensiId).setValue(newPresensi);

        Intent intent = new Intent(InputPresensiActivity.this, SiswaActivity.class);
        startActivity(intent);
        finish();

    }

}
