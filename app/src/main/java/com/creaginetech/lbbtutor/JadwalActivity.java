package com.creaginetech.lbbtutor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.creaginetech.lbbtutor.Common.Common;
import com.creaginetech.lbbtutor.Interface.ItemClickListener;
import com.creaginetech.lbbtutor.Model.Jadwal;
import com.creaginetech.lbbtutor.Model.Tutor;
import com.creaginetech.lbbtutor.VIewHolder.JadwalViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class JadwalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private DatabaseReference jadwalRef, tutorRef;

    private String idTutor;

    FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        getSupportActionBar().setTitle("List Jadwal");

        jadwalRef = FirebaseDatabase.getInstance().getReference("Jadwal");
        tutorRef = FirebaseDatabase.getInstance().getReference("Tutor");

        recyclerView = findViewById(R.id.recyclerViewJadwal);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        getDataTutor();

    }

    public void getDataTutor(){

        tutorRef.child(Common.currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Tutor currentTutor = dataSnapshot.getValue(Tutor.class);

                idTutor = currentTutor.getIdTutor();

                fetch(idTutor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void fetch(String idTutor) {

        Query query = jadwalRef.orderByChild("idTutor").equalTo(idTutor);

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Jadwal> options = new FirebaseRecyclerOptions.Builder<Jadwal>()
                .setQuery(query,Jadwal.class)
                .build();

        //recycler adapter shop - ShopViewHolder
        adapter = new FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull JadwalViewHolder viewHolder, int position, @NonNull Jadwal model) {

                viewHolder.siswaNama.setText(model.getNamaSiswa());
                viewHolder.siswaGrade.setText(model.getGrade());
                viewHolder.siswaHari.setText(model.getHari());
                viewHolder.siswaJam.setText(model.getJam());
                viewHolder.siswaJurusan.setText(model.getJurusan());



                final Jadwal clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //When user select shop, we will save shop id to select service of this shop
                        Common.jadwalSelected = adapter.getRef(position).getKey();

                        getDataJadwal();

                    }
                });
            }

            @NonNull
            @Override
            public JadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.jadwal_item, parent, false);
                return new JadwalViewHolder(itemView) {
                };
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void getDataJadwal() {

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Jadwal");

        usersRef.child(Common.jadwalSelected)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Jadwal selectedJadwal = dataSnapshot.getValue(Jadwal.class);

                        String jumlahPertemuan = selectedJadwal.getPertemuan();

                        if(jumlahPertemuan.equals("4")){

                            Intent intent = new Intent(JadwalActivity.this, Presensi4PertemuanActivity.class);
                            startActivity(intent);

                        }else if(jumlahPertemuan.equals("8")){

                            Intent intent = new Intent(JadwalActivity.this, Presensi8PertemuanActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(JadwalActivity.this,
                                    "Jumlah pertemuan tidak diketahui", Toast.LENGTH_SHORT).show();
                        }

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
