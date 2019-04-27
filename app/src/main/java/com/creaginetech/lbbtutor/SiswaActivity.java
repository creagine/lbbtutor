package com.creaginetech.lbbtutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.creaginetech.lbbtutor.Common.Common;
import com.creaginetech.lbbtutor.Model.Jadwal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SiswaActivity extends AppCompatActivity {

    private TextView txtNama, txtJurusan, txtGrade, txtHari, txtJam;
    private Button btnPekan1, btnPekan2, btnPekan3, btnPekan4, btnPekanPenggantian;
    private Spinner spinnerBulan;
    private DatabaseReference jadwalRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);

        widgetInit();

        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");

        getDataSiswa();

        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i > 0){
                    // get spinner value

                    Common.bulanSelected = spinnerBulan.getSelectedItem().toString();

                    btnPekan1.setVisibility(View.VISIBLE);
                    btnPekan2.setVisibility(View.VISIBLE);
                    btnPekan3.setVisibility(View.VISIBLE);
                    btnPekan4.setVisibility(View.VISIBLE);
                    btnPekanPenggantian.setVisibility(View.VISIBLE);


                }else{
                    // show toast select gender

                    Toast.makeText(SiswaActivity.this, "Pilih bulan", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnPekan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "1";

                Intent intent = new Intent(SiswaActivity.this, PresensiActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnPekan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "2";

                Intent intent = new Intent(SiswaActivity.this, PresensiActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnPekan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "3";

                Intent intent = new Intent(SiswaActivity.this, PresensiActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnPekan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "4";

                Intent intent = new Intent(SiswaActivity.this, PresensiActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnPekanPenggantian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common.pekanSelected = "penggantian";

                Intent intent = new Intent(SiswaActivity.this, PresensiActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void widgetInit(){

        txtNama = findViewById(R.id.textViewNamaSiswa);
        txtJurusan = findViewById(R.id.textViewJurusan);
        txtGrade = findViewById(R.id.textViewGrade);
        txtHari = findViewById(R.id.textViewHari);
        txtJam = findViewById(R.id.textViewJam);
        btnPekan1 = findViewById(R.id.buttonPekan1);
        btnPekan2 = findViewById(R.id.buttonPekan2);
        btnPekan3 = findViewById(R.id.buttonPekan3);
        btnPekan4 = findViewById(R.id.buttonPekan4);
        btnPekanPenggantian = findViewById(R.id.buttonPenggantian);
        spinnerBulan = findViewById(R.id.spinnerBulan);

        btnPekan1.setVisibility(View.GONE);
        btnPekan2.setVisibility(View.GONE);
        btnPekan3.setVisibility(View.GONE);
        btnPekan4.setVisibility(View.GONE);
        btnPekanPenggantian.setVisibility(View.GONE);

    }

    private void getDataSiswa() {

        jadwalRef.child(Common.jadwalSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Jadwal jadwalSelected = dataSnapshot.getValue(Jadwal.class);

                txtNama.setText(jadwalSelected.getNamaSiswa());
                txtJurusan.setText(jadwalSelected.getJurusan());
                txtGrade.setText(jadwalSelected.getGrade());
                txtHari.setText(jadwalSelected.getHari());
                txtJam.setText(jadwalSelected.getJam());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
