package com.creaginetech.lbbtutor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.creaginetech.lbbtutor.Common.Common;
import com.creaginetech.lbbtutor.Model.Jadwal;
import com.creaginetech.lbbtutor.Model.Presensi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPresensiActivity extends AppCompatActivity {

    private EditText edtMateri, edtKeterangan;
    private Button btnSubmit, btnInputTanggal;
    private Spinner spinnerKehadiranSiswa;

    private DatabaseReference jadwalRef, presensiRef;

    private String namaSiswa, namaTutor, tanggal;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_presensi);
        getSupportActionBar().setTitle("Input presensi");

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");
        presensiRef = FirebaseDatabase.getInstance().getReference("Presensi");

        widgetInit();

        getJadwal();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pushPresensi();

            }
        });

        btnInputTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDateDialog();

            }
        });

    }

    private void widgetInit() {

        edtMateri = findViewById(R.id.editTextMateri);
        edtKeterangan = findViewById(R.id.editTextKeterangan);
        btnSubmit = findViewById(R.id.buttonSubmitPresensi);
        btnInputTanggal = findViewById(R.id.buttonInputTanggal);
        spinnerKehadiranSiswa = findViewById(R.id.spinnerKehadiranSiswa);

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

        String materi = edtMateri.getText().toString();
        String keterangan = edtKeterangan.getText().toString();
        String presensiSiswa = spinnerKehadiranSiswa.getSelectedItem().toString();
        String presensiTutor = "Hadir";
        String status = "Menunggu validasi admin";
        String idJadwal = Common.jadwalSelected;

        if (TextUtils.isEmpty(tanggal)) {
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

        if (presensiSiswa.equals("Kehadiran siswa")) {
            Toast.makeText(getApplicationContext(), "Masukkan kehadiran siswa!", Toast.LENGTH_SHORT).show();
            return;
        }

        String presensiId = presensiRef.push().getKey();

        Presensi newPresensi = new Presensi(namaSiswa, namaTutor, Common.bulanSelected,
                Common.pekanSelected, tanggal, materi, keterangan, status, presensiTutor, presensiSiswa, idJadwal);

        presensiRef.child(presensiId).setValue(newPresensi);

        Intent intent = new Intent(AddPresensiActivity.this, PresensiDetailActivity.class);
        startActivity(intent);
        finish();

    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                btnInputTanggal.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()));
                tanggal = dateFormatter.format(newDate.getTime());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

}
