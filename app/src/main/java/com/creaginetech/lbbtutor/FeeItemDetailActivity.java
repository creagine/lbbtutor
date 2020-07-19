package com.creaginetech.lbbtutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.creaginetech.lbbtutor.Common.Common;

public class FeeItemDetailActivity extends AppCompatActivity {

    public TextView no, namaSiswa, bulan, tahun, jurusan, grade, tarif, fee, presensi,
            tglSpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_item_detail);
        getSupportActionBar().setTitle("Detail fee tutor");

        no = findViewById(R.id.textViewNoFeeItem);
        namaSiswa = findViewById(R.id.textViewNamaSiswaItem);
        bulan = findViewById(R.id.textViewBulanItem);
        tahun = findViewById(R.id.textViewTahunItem);
        jurusan = findViewById(R.id.textViewJurusanItem);
        grade = findViewById(R.id.textViewGradeItem);
        tarif = findViewById(R.id.textViewTarifItem);
        fee = findViewById(R.id.textViewFeeItem);
        presensi = findViewById(R.id.textViewPresensiItem);
        tglSpp = findViewById(R.id.textViewTglSppItem);

        no.setText(Common.feeSelected.getNo());
        namaSiswa.setText(Common.feeSelected.getNamaSiswa());
        bulan.setText(Common.feeSelected.getBulan());
        tahun.setText(Common.feeSelected.getTahun());
        jurusan.setText(Common.feeSelected.getJurusan());
        grade.setText(Common.feeSelected.getGrade());
        tarif.setText(Common.feeSelected.getTarif());
        fee.setText(Common.feeSelected.getFee());
        presensi.setText(Common.feeSelected.getPresensi());
        tglSpp.setText(Common.feeSelected.getTglSpp());

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
