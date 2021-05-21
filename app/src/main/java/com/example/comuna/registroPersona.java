package com.example.comuna;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import Logica.Usuarios;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class registroPersona extends AppCompatActivity implements Button.OnClickListener{
    protected EditText edtNombres, edtApellidos, edtCorreo, edtMovil;
    protected Button btnRegistrarCuenta, btnRegistrar;
    protected CheckBox chbTerminos;
    protected Usuarios objUsuario;
    protected int intResult = 0;
    String strResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_persona);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtNombres = (EditText)findViewById(R.id.edt_nombres_ATVRegistroPersona);
        edtApellidos = (EditText)findViewById(R.id.edt_apellidos_ATVRegistroPersona);
        edtCorreo = (EditText)findViewById(R.id.edt_correo_ATVRegistroPersona);
        edtMovil = (EditText)findViewById(R.id.edt_movil_ATVRegistroPersona);
        btnRegistrar = (Button)findViewById(R.id.btn_Registrar_ATVRegistroPersona);

        btnRegistrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Registrar_ATVRegistroPersona:
                Registrar_Persona();
                break;
        }
    }

    public void Registrar_Persona(){
        Thread threadRegistro = new Thread(new Runnable() {
            Handler handlerRegistro = new Handler();
            @Override
            public void run() {
                objUsuario = new Usuarios();
                strResult = objUsuario.Registrar_Persona(edtNombres.getText().toString(), edtApellidos.getText().toString(),edtMovil.getText().toString(), edtCorreo.getText().toString());
                handlerRegistro.post(new Runnable() {
                    @Override
                    public void run() {
                        if(strResult.contains("1")){
                            Intent intentRegistrarUsuario = new Intent(registroPersona.this, registroUsuario.class);
                            Bundle bundleRegistrarUsuario = new Bundle();
                            bundleRegistrarUsuario.putString("Correo", edtCorreo.getText().toString());
                            bundleRegistrarUsuario.putString("Movil", edtMovil.getText().toString());
                            intentRegistrarUsuario.putExtras(bundleRegistrarUsuario);
                            startActivity(intentRegistrarUsuario);
                            registroPersona.this.finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "El correo o Movil ya estan registrados",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });threadRegistro.start();

    }
}
