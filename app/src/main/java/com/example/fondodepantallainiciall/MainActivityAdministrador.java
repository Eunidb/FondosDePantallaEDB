package com.example.fondodepantallainiciall;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.fondodepantallainiciall.FragmentosAdministrador.InicioAdmin;
import com.example.fondodepantallainiciall.FragmentosAdministrador.ListAdmin;
import com.example.fondodepantallainiciall.FragmentosAdministrador.PerfilAdmin;
import com.example.fondodepantallainiciall.FragmentosAdministrador.RegistrarAdmin;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityAdministrador extends AppCompatActivity implements OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_administrador);

        Toolbar toolbar = findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_A);

        NavigationView navigationView = findViewById(R.id.nav_viewA);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        //Fragmento por defectoD

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new InicioAdmin()).commit();

            navigationView.setCheckedItem(R.id.InicioAdmin);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.InicioAdmin){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new InicioAdmin()).commit();
        } else if (itemId == R.id.PerfilAdmin){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new PerfilAdmin()).commit();
        }else if (itemId == R.id.RegistrarAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new RegistrarAdmin()).commit();
        }else if (itemId == R.id.ListarAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new ListAdmin()).commit();
        } else if (itemId == R.id.Salir) {
            CerrarSesion();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void ComprobandoInicioSesion (){
        if(user != null){
            // Si el administrador a iniciado sesion
            Toast.makeText(this, "Se ha iniciado sesión ", Toast.LENGTH_SHORT).show();
        }else{
            //Si no ha iniciado sesión, es porque el usuario es un cliente
            startActivity(new Intent(MainActivityAdministrador.this,MainActivity.class));
            finish();
        }
    }

    private void CerrarSesion(){
        firebaseAuth.signOut(); //null
        startActivity(new Intent(MainActivityAdministrador.this, MainActivity.class));
        Toast.makeText(this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        ComprobandoInicioSesion();
        super.onStart();
    }
}