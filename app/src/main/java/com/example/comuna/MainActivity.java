package com.example.comuna;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import Logica.Usuarios;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected Usuarios objUsuarios;
    protected int strRespuesta = 0;
    private final int SPLASH_DISPLAY_LENGTH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        int i1 =2;
        int i2 =322;
        int i3 = i1/i2;
        ArrayList l = new ArrayList(2);
        l.add(1);
        l.add(2);
        l.add(3);
        String a =""+i2;
        int b = a.length();
        Log.e("XXXXXXXX", "res"+b);

 */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Obtener_VariableSesion();
            }

        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
/*
    protected void Obtener_VariableSesion() {

        Thread threadVerificarSesion = new Thread() {
            @Override
            public void run() {
                int intId_Usuario = objUsuarios.Obtener_VariableSession(getApplicationContext(), 1);
                if (intId_Usuario != 0) {
                    //Verificar_UsuarioPersona();

                } else {
                    Intent loginIntent = new Intent(MainActivity.this, inicioSesion.class);
                    //Intent loginIntent = new Intent(MainActivity.this, registro.class);
                    MainActivity.this.startActivity(loginIntent);
                    MainActivity.this.finish();
                }

            }

        };
        threadVerificarSesion.start();
    }

 */

    protected void Obtener_VariableSesion() {
        objUsuarios = new Usuarios();
        int intId_Usuario = objUsuarios.Obtener_VariableSession(getApplicationContext(), 1);
        if (intId_Usuario != 0) {
            //Verificar_UsuarioPersona();
            Intent mainIntent = new Intent(MainActivity.this, menuPrincipal.class);
            //Intent loginIntent = new Intent(MainActivity.this, registro.class);
            MainActivity.this.startActivity(mainIntent);
            MainActivity.this.finish();
        } else {
            Intent loginIntent = new Intent(MainActivity.this, inicioSesion.class);
            //Intent loginIntent = new Intent(MainActivity.this, registro.class);
            MainActivity.this.startActivity(loginIntent);
            MainActivity.this.finish();
        }

    }

}
