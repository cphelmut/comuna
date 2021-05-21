package com.example.comuna;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import Logica.Noticias;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class registrarNoticia extends AppCompatActivity implements View.OnClickListener{
    private EditText edtNombreNoticia, edtDescripcionNoticia;
    private Button btnRegistrarNoticia, btnGaleria;
    private ImageView imvNoticia;
    private Bitmap bitmapImagenNoticia;
    protected String strURL ="";
    protected Noticias objNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_noticia);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtNombreNoticia = findViewById(R.id.edt_nombreNoticia_ATVRegistrarNoticia);;
        edtDescripcionNoticia = findViewById(R.id.edt_descripcionNoticia_ATVRegistrarNoticia);
        btnRegistrarNoticia = findViewById(R.id.btn_registrarNoticia_ATVRegistrarNoticia);
        imvNoticia = findViewById(R.id.imv_imagenNoticia_ATVRegistrarNoticia);
        btnGaleria = findViewById(R.id.btn_abrirGaleriaNoticias_ATVRegistrarNoticia);
        btnRegistrarNoticia.setOnClickListener(this);
        btnGaleria.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_registrarNoticia_ATVRegistrarNoticia:
                Registrar_Noticia();
                break;
            case R.id.btn_abrirGaleriaNoticias_ATVRegistrarNoticia:
                Intent intent = new Intent();
                intent.setType("image/");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent, "Seleccionar Imagen"), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data.getData() != null){
            Uri uri = data.getData();
            try {
                bitmapImagenNoticia = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imvNoticia.setImageBitmap(bitmapImagenNoticia);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageUploadToServerFunction();
        }
    }

    public void ImageUploadToServerFunction(){
        ByteArrayOutputStream byteArrayOutputStreamObject;
        byteArrayOutputStreamObject = new ByteArrayOutputStream();
        bitmapImagenNoticia.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
        byte[] bytes = byteArrayOutputStreamObject.toByteArray();
        strURL = Base64.encodeToString(bytes, Base64.DEFAULT);

    }

    public void Registrar_Noticia(){
        Thread threadRegistrarNoticia = new Thread(new Runnable() {
            Handler handlerRegistrarNoticia = new Handler();
            @Override
            public void run() {
                objNoticias = new Noticias();
                final Boolean boolRes = objNoticias.Registrar_Noticia(edtNombreNoticia.getText().toString(), edtDescripcionNoticia.getText().toString(), strURL);
                handlerRegistrarNoticia.post(new Runnable() {
                    @Override
                    public void run() {
                        if(boolRes.equals(true)){

                        }else{

                        }
                    }
                });
            }
        });threadRegistrarNoticia.start();
    }

}
