package com.creaginetech.lbbtutor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creaginetech.lbbtutor.Common.Common;
import com.creaginetech.lbbtutor.Interface.ItemClickListener;
import com.creaginetech.lbbtutor.Model.Fee;
import com.creaginetech.lbbtutor.VIewHolder.FeeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FeeActivity extends AppCompatActivity {

    private TextView txtNama, txtJurusan;

    private FloatingActionButton btnAddFee;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    FirebaseRecyclerAdapter<Fee, FeeViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee);

        //TODO test FEE
        txtNama = findViewById(R.id.textViewNamaTutorFee);
        txtJurusan = findViewById(R.id.textViewJurusanTutorFee);

        recyclerView = findViewById(R.id.recyclerViewFee);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        setDataTutor();

        fetch();

    }

    private void setDataTutor(){

        txtNama.setText(Common.currentTutor.getNamaTutor());
        txtJurusan.setText(Common.currentTutor.getJurusanTutor());

    }

    private void fetch() {

        //TODO TES get fee tutor
        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Fee> options = new FirebaseRecyclerOptions.Builder<Fee>()
                .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Fee").orderByChild("namaTutor")
                                .equalTo(Common.currentTutor.getNamaTutor())
                        ,Fee.class)
                .build();

        //recycler adapter shop - ShopViewHolder
        adapter = new FirebaseRecyclerAdapter<Fee, FeeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FeeViewHolder viewHolder, int position, @NonNull Fee model) {

                viewHolder.no.setText(model.getNo());
                viewHolder.namaTutor.setText(model.getNamaTutor());
                viewHolder.namaSiswa.setText(model.getNamaSiswa());
                viewHolder.bulan.setText(model.getBulan());
                viewHolder.tahun.setText(model.getTahun());
                viewHolder.jurusan.setText(model.getJurusan());
                viewHolder.grade.setText(model.getGrade());
                viewHolder.tarif.setText(model.getTarif());
                viewHolder.fee.setText(model.getFee());
                viewHolder.presensi.setText(model.getPresensi());
                viewHolder.tglSpp.setText(model.getTglSpp());

                final Fee clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //Get CategoryId and send to new Activity
//                        Intent intent = new Intent(FeeTutorDetailActivity.this, TagihanDetailActivity.class);

                        //When user select shop, we will save shop id to select service of this shop
//                        Common.tagihanSelected = adapter.getRef(position).getKey();

//                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public FeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fee_item, parent, false);
                return new FeeViewHolder(itemView) {
                };
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
