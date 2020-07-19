package com.creaginetech.lbbtutor;

import android.content.Intent;
import android.os.Handler;
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
import android.widget.Toast;

import com.creaginetech.lbbtutor.Common.Common;
import com.creaginetech.lbbtutor.Interface.ItemClickListener;
import com.creaginetech.lbbtutor.Model.Fee;
import com.creaginetech.lbbtutor.VIewHolder.FeeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeeActivity extends AppCompatActivity {

    private TextView txtNama, txtJurusan;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    FirebaseRecyclerAdapter<Fee, FeeViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee);
        getSupportActionBar().setTitle("List fee tutor");

        txtNama = findViewById(R.id.textViewNamaTutorFee);
        txtJurusan = findViewById(R.id.textViewJurusanTutorFee);

        recyclerView = findViewById(R.id.recyclerViewFee);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        if(Common.currentTutor != null){

            setDataTutor();

            fetch();

        } else {

            Toast.makeText(FeeActivity.this, "Loading . .", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    setDataTutor();

                    fetch();
                }
            },2000);

        }

    }

    private void setDataTutor(){

        txtNama.setText(Common.currentTutor.getNamaTutor());
        txtJurusan.setText(Common.currentTutor.getJurusanTutor());

    }

    private void fetch() {

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Fee> options = new FirebaseRecyclerOptions.Builder<Fee>()
                .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Fee").orderByChild("idTutor")
                                .equalTo(Common.currentTutor.getIdTutor())
                        ,Fee.class)
                .build();

        //recycler adapter shop - ShopViewHolder
        adapter = new FirebaseRecyclerAdapter<Fee, FeeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FeeViewHolder viewHolder, int position, @NonNull Fee model) {

                viewHolder.no.setText(model.getNo());
                viewHolder.namaSiswa.setText(model.getNamaSiswa());
                viewHolder.bulan.setText(model.getBulan());
                viewHolder.tahun.setText(model.getTahun());
                viewHolder.jurusan.setText(model.getJurusan());
                viewHolder.tglSpp.setText(model.getTglSpp());

                final Fee clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //Get CategoryId and send to new Activity
                        Intent intent = new Intent(FeeActivity.this, FeeItemDetailActivity.class);

                        //When user select shop, we will save shop id to select service of this shop
                        Common.keyFeeSelected = adapter.getRef(position).getKey();

                        getDataFee();

                        startActivity(intent);

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

    private void getDataFee() {

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Fee");

        usersRef.child(Common.keyFeeSelected)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Fee selectedFee = dataSnapshot.getValue(Fee.class);
                        Common.feeSelected = selectedFee;

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
