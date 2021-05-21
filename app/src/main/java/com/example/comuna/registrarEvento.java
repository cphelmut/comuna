package com.example.comuna;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registrarEvento extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNombreEvento, edtDescripcionEvento, edtFechaInicio, edtCoordenadas;
    private Button btnRegistrar, btnAbrirMapa;
    private int LANZAR_ACTIVITY_MAPA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_evento);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtNombreEvento = findViewById(R.id.edt_nombreEvento_ATVRegistrarEventos);
        edtDescripcionEvento = findViewById(R.id.edt_descripcionEvento_ATVRegistrarEventos);
        edtCoordenadas = findViewById(R.id.edt_posicion_ATVRegistrarEventos);
        edtFechaInicio = findViewById(R.id.edt_fechaInicio_ATVRegistrarEventos);
        btnAbrirMapa = findViewById(R.id.btn_mapa_ATVRegistrarEventos);
        btnRegistrar = findViewById(R.id.btn_registrarEvento_ATVRegistrarEventos);

        btnAbrirMapa.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mapa_ATVRegistrarEventos:
                Intent intentMapa = new Intent(this, mapa.class);
                startActivityForResult(intentMapa, LANZAR_ACTIVITY_MAPA);
                break;
            case R.id.btn_registrarEvento_ATVRegistrarEventos:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
