package com.creaginetech.lbbtutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.creaginetech.lbbtutor.Common.Common;
import com.creaginetech.lbbtutor.Model.Jadwal;
import com.creaginetech.lbbtutor.Model.Presensi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PresensiActivity extends AppCompatActivity {

    private TextView txtBulan, txtPekan, txtTanggal, txtMateri, txtKeterangan, txtKehadiranTutor,
    tztKehadiranSiswa;

    private DatabaseReference presensiRef, jadwalRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi);

        //TODO tampilkan isi dari node "Presensi" jika sudah mengisi presensi

        presensiRef = FirebaseDatabase.getInstance().getReference("Presensi");
        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");

        widgetInit();

        getDataSiswa();


        //TODO kehadiran siswa dan tutor

    }

    private void getDataSiswa() {

        jadwalRef.child(Common.jadwalSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Jadwal jadwalSelected = dataSnapshot.getValue(Jadwal.class);

                String namaSiswa = jadwalSelected.getNamaSiswa();

                getPresensiSiswa(namaSiswa);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void widgetInit(){

        txtBulan = findViewById(R.id.textViewBulan);
        txtPekan = findViewById(R.id.textViewPekan);
        txtTanggal = findViewById(R.id.textViewTanggal);
        txtMateri = findViewById(R.id.textViewMateri);
        txtKeterangan = findViewById(R.id.textViewKeterangan);
        txtKehadiranTutor = findViewById(R.id.textViewKehadiranTutor);
        tztKehadiranSiswa = findViewById(R.id.textViewKehadiranSiswa);

    }

    private void getPresensiSiswa(String namaSiswa){

        //TODO jangan lupa kasih ketententuan sesuai sama bulan dan pekan yang dipilih
        Toast.makeText(PresensiActivity.this, namaSiswa + " inside method", Toast.LENGTH_SHORT).show();

        Query query = presensiRef.orderByChild("namaSiswa").equalTo(namaSiswa);

        //TODO get presensi dimana bulan dan pekan sesuai ketentuan
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if(data.exists()) {
                        if (!data.getValue(Presensi.class).getBulan().equals(Common.bulanSelected) &&
                                !data.getValue(Presensi.class).getPekan().equals(Common.pekanSelected)) {
                            txtBulan.setText(data.getValue(Presensi.class).getBulan());
                            txtPekan.setText(data.getValue(Presensi.class).getPekan());
                            txtTanggal.setText(data.getValue(Presensi.class).getTanggalPresensi());
                            txtMateri.setText(data.getValue(Presensi.class).getMateri());
                            txtKeterangan.setText(data.getValue(Presensi.class).getKeterangan());
                        }
                    } else {

                        Toast.makeText(PresensiActivity.this,
                            "Data belum ditemukan, silahkan mengisi data presensi",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(PresensiActivity.this, InputPresensiActivity.class);
                    startActivity(intent);
                    finish();

                    }
                }

//                if (dataSnapshot.exists()) {
                //do ur stuff

//                Presensi presensiSelected = dataSnapshot.getValue(Presensi.class);

//                    if(presensiSelected.getBulan().equals(Common.bulanSelected) &&
//                    presensiSelected.getPekan().equals(Common.pekanSelected)){

//                Toast.makeText(PresensiActivity.this, presensiSelected.getBulan(), Toast.LENGTH_SHORT).show();



//                    }
//
//                } else {
//                    //do something if not exists
//
//
//
//                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
