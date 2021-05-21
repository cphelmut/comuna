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

public class registroUsuario extends AppCompatActivity implements View.OnClickListener {
    protected EditText  edtNombreUsuario, edtContra, edtContra2;
    protected Button  btnRegistrar;
    protected Usuarios objUsuario;
    protected clsUsuarios objSesion;
    protected int intResult = 0;
    protected String strCorreo = "", strMovil = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        edtNombreUsuario = findViewById(R.id.edt_usuario_ATVRegistroUsuario);
        edtContra = findViewById(R.id.edt_contrasenia_ATVRegistroUsuario);
        edtContra2 = findViewById(R.id.edt_contraseniaConfirmacion_ATVRegistroUsuario);
        btnRegistrar = findViewById(R.id.btn_Registrar_ATVRegistroUsuario);
        Obtener_Datos();
        btnRegistrar.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Registrar_ATVRegistroUsuario:
                Registrar_Usuario();
                break;
        }
    }


    public void Registrar_Usuario(){
        Thread threadRegistro = new Thread(new Runnable() {
            @Override
            public void run() {

                        objUsuario = new Usuarios();
                        objSesion = new clsUsuarios();
                        objSesion = objUsuario.Registrar_Usuario(edtNombreUsuario.getText().toString(), edtContra.getText().toString(), strMovil, strCorreo);
                        if(objSesion != null){
                            Boolean boolRegistrar = objUsuario.Registrar_VariableSession(objSesion.getId_Usuario(), objSesion.getRol(), getApplicationContext());
                            if(boolRegistrar.equals(true)){
                                Intent intentMenuPrincipal = new Intent(registroUsuario.this, menuPrincipal.class);
                                startActivity(intentMenuPrincipal);
                            }else{
                                Log.e("Hola Jhon", "Variable");
                            }
                        }else {
                            Log.e("Hola Jhon", "No registra");
                        }


            }
        });threadRegistro.start();


    }

    public void Obtener_Datos(){
        Bundle bundleCargarDatos = getIntent().getExtras();
        if (bundleCargarDatos != null) {
            strCorreo = bundleCargarDatos.getString("Correo");
            strMovil = bundleCargarDatos.getString("Movil");
        }
    }
}
