package com.creaginetech.lbbtutor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.creaginetech.lbbtutor.Common.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authListener;
    private Button btnAbout;

    CardView cardPresensi,cardSpp;

    ImageView imageAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        checkAuthState();

        cardPresensi = findViewById(R.id.cardPresensi);
        cardSpp = findViewById(R.id.cardSPP);
        imageAccount = findViewById(R.id.imageAccount);
        btnAbout = findViewById(R.id.buttonAbout);

        cardPresensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, JadwalActivity.class);
                startActivity(intent);

            }
        });

        cardSpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //todo: intent to spp activity
//                Intent intent = new Intent(MainActivity.this, JadwalActivity.class);
//                startActivity(intent);

            }
        });

        imageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);

            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(authListener);


    }

    private void checkAuthState() {

        authListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Common.currentUser = user.getUid();

                }
            }


        };

    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            mAuth.removeAuthStateListener(authListener);
        }
    }

}
