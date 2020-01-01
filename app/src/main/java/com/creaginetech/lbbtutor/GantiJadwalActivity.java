package com.creaginetech.lbbtutor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GantiJadwalActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave, btnCancel, btnJamMulai, btnJamSelesai, btnTanggal, btnCekRuang,
            btnPilihJadwal, btnDetailJadwal;
    private Spinner spinnerHari, spinnerRuang;
    private ProgressBar progressBar;
    private EditText edtAlasan;

    private String tanggal, jamMulai, jamSelesai;

    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private DatabaseReference jadwalRef, tutorRef, siswaRef, gantiJadwalRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_jadwal);

        //TODO request pindah jadwal nanti isinya, tutor request kasih tanggal, admin konfirmasi tanggal

        //TODO pilih jadwal fungsinya nanti munculkan detail jadwal yang diganti, untuk dipantau oleh admin

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        // get reference to 'Siswa'
        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");
        tutorRef = FirebaseDatabase.getInstance().getReference("Tutor");
        siswaRef = FirebaseDatabase.getInstance().getReference("Siswa");
        gantiJadwalRef = FirebaseDatabase.getInstance().getReference("GantiJadwal");

        widgets();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {

            } else {
                btnPilihJadwal.setText(extras.getString("btnjadwal"));
            }
        } else {
            String strbtnjadwal;
            strbtnjadwal = (String) savedInstanceState.getSerializable("btnjadwal");
            btnPilihJadwal.setText(strbtnjadwal);
        }

        btnTanggal.setOnClickListener(this);
        btnPilihJadwal.setOnClickListener(this);
        btnJamMulai.setOnClickListener(this);
        btnJamSelesai.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnCekRuang.setOnClickListener(this);


    }

    private void widgets() {

        btnJamMulai = findViewById(R.id.buttonJamMulai);
        btnJamSelesai = findViewById(R.id.buttonJamSelesai);
        btnTanggal = findViewById(R.id.buttonTanggal);
        btnPilihJadwal = findViewById(R.id.buttonPilihJadwal);
        btnDetailJadwal = findViewById(R.id.buttonDetailJadwal);
        spinnerRuang = findViewById(R.id.spinnerRuang);
        spinnerHari = findViewById(R.id.spinnerHari);
        btnSave = findViewById(R.id.buttonSaveJadwal);
        btnCancel = findViewById(R.id.buttonCancelJadwal);
        btnCekRuang = findViewById(R.id.buttonCekRuang);
        progressBar = findViewById(R.id.progressBarJadwal);
        edtAlasan = findViewById(R.id.editTextAlasan);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonSaveJadwal){

            saveJadwal();

        } if(i == R.id.buttonPilihJadwal){

            Intent intent = new Intent(GantiJadwalActivity.this, PilihJadwalActivity.class);
            startActivity(intent);

        } if(i == R.id.buttonTanggal){

            showDateDialog();

        } if(i == R.id.buttonJamMulai){

            showTimeDialogJamMulai();

        } if(i == R.id.buttonJamSelesai){

            showTimeDialogJamSelesai();

        } if(i == R.id.buttonCekRuang){

            Intent intent = new Intent(GantiJadwalActivity.this, CekRuangActivity.class);
            startActivity(intent);

        }if(i == R.id.buttonDetailJadwal){

            Intent intent = new Intent(GantiJadwalActivity.this, GantiJadwalDetailActivity.class);
            startActivity(intent);

        }else if (i == R.id.buttonCancelJadwal){
            finish();
        }
    }

    private void saveJadwal() {

        progressBar.setVisibility(View.VISIBLE);

       //TODO sesuaikan dg input

        //TODO selectedGantiJadwal
        final String hari = spinnerHari.getSelectedItem().toString();
        String jam = jamMulai+" - "+jamSelesai;
        String ruang = spinnerRuang.getSelectedItem().toString();
        String alasan = edtAlasan.getText().toString();

        if (TextUtils.isEmpty(hari)) {
            Toast.makeText(getApplicationContext(), "Masukkan hari!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(jam)) {
            Toast.makeText(getApplicationContext(), "Masukkan jam!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(ruang)) {
            Toast.makeText(getApplicationContext(), "Masukkan ruang!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(alasan)) {
            Toast.makeText(getApplicationContext(), "Masukkan alasan!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        String jadwalId = jadwalRef.push().getKey();

        //TODO model ganti jadwal
//        Jadwal newJadwal = new Jadwal(namaSiswa, jurusan, grade, harga, tutor, hari, jam, tanggal,
//                ruang, pertemuan);

//        jadwalRef.child(jadwalId).setValue(newJadwal);

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
                btnTanggal.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()));
                tanggal = dateFormatter.format(newDate.getTime());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void showTimeDialogJamMulai() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                jamMulai = hourOfDay+":"+minute;
                btnJamMulai.setText(jamMulai);
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    private void showTimeDialogJamSelesai() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                jamSelesai = hourOfDay+":"+minute;
                btnJamSelesai.setText(jamSelesai);
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

}
