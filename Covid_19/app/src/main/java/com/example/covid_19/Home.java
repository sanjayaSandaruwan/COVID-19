package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressDialog rDialog;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    TextView txt_email;
    TextView txt_see;
    View hview;

    FrameLayout asd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        String uid = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("covid_19").child(uid);
        rDialog = new ProgressDialog(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        hview = navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(this);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new MainFragment());
        fragmentTransaction.commit();

        txt_email = (TextView) hview.findViewById(R.id.email);
        String email = modul.email_address.toString();

        txt_email.setText(email);
        Toast.makeText(Home.this, modul.email_address.toString(), Toast.LENGTH_SHORT).show();

        NavController navController;

        txt_see=findViewById(R.id.txt_see_more);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                firebaseAuth.signOut();
                Intent intMain = new Intent(Home.this, MainActivity.class);
                startActivity(intMain);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.nav_covid_19:
                Intent intCovid = new Intent(Home.this, Corona.class);
                startActivity(intCovid);
                break;
            case R.id.nave_symp:
                Intent intSymp = new Intent(Home.this, Symptom.class);
                startActivity(intSymp);
                break;
            case R.id.nave_statics:
                Intent intStack = new Intent(Home.this, Statics.class);
                startActivity(intStack);
                break;
            case R.id.nav_FAQ:
                Intent intfaq = new Intent(Home.this, Fq.class);
                startActivity(intfaq);
                break;
            case R.id.nav_about:
                Intent intab = new Intent(Home.this, About.class);
                startActivity(intab);
                break;

            case R.id.nav_contact:
                Intent incon = new Intent(Home.this, Contact.class);
                startActivity(incon);
                break;
        }
        return true;
    }
}
