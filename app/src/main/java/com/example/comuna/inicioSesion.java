package com.example.comuna;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import Datos.clsUsuarios;
import Logica.Usuarios;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class inicioSesion extends AppCompatActivity implements View.OnClickListener {
    private EditText edtClave, edtContrasenia;
    private Button btnIniciarSesion, btnRegistro;
    protected Usuarios objUsuario;
    protected List<clsUsuarios> listUsuarios;
    protected boolean booleanSesion =false;
    protected int intId_Usuario = 0, intResult = 0;
    protected Datos.clsUsuarios objUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtClave = (EditText) findViewById(R.id.edt_usuario_ATVInicioSesion);
        edtContrasenia = (EditText)findViewById(R.id.edt_contrasenia_ATVInicioSesion);
        objUsuario = new Usuarios();
        btnIniciarSesion = (Button)findViewById(R.id.btn_login_ATVInicioSesion);
        btnRegistro = (Button)findViewById(R.id.btn_registro_ATVInicioSesion);
        edtClave.setText("Rikka");
        edtContrasenia.setText("123");
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Iniciar_Sesion();
            }
        });
        btnRegistro.setOnClickListener(this);
    }


    public void Iniciar_Sesion(){
        Thread threadLogin = new Thread(new Runnable() {
            Handler handlerLogin = new Handler();
            @Override
            public void run() {
                objUsuarios = new Datos.clsUsuarios();
                objUsuarios = objUsuario.Iniciar_Sesion(edtClave.getText().toString(), edtContrasenia.getText().toString());
                handlerLogin.post(new Runnable() {
                    @Override
                    public void run() {
                        if(objUsuarios != null){
                            booleanSesion = objUsuario.Registrar_VariableSession(objUsuarios.getId_Usuario(),objUsuarios.getRol(), getApplicationContext());
                            if(booleanSesion == true) {
                                Intent mainIntent = new Intent(inicioSesion.this, menuPrincipal.class);
                                inicioSesion.this.startActivity(mainIntent);
                                inicioSesion.this.finish();
                            }
                        }else{
                            btnIniciarSesion.setEnabled(true);
                            Toast.makeText(inicioSesion.this, "Error al Iniciar Sesion",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });threadLogin.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_registro_ATVInicioSesion:
                Intent intentRegistrar = new Intent(inicioSesion.this, registroPersona.class);
                startActivity(intentRegistrar);
                break;
        }
    }
}
