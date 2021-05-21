package com.example.comuna;

import android.content.Context;
import android.os.Bundle;

import Datos.clsEventos;
import Datos.clsUsuarios;
import Logica.Eventos;
import Logica.Usuarios;
import adapter.adapterEventos;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class frgEventos extends Fragment {
    private Eventos objEventos;
    private Usuarios objUsuarios;
    private List<clsEventos> listEventos;
    private adapterEventos adapterEventos;
    private RecyclerView rcvEventos;

    public frgEventos() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);
        rcvEventos = view.findViewById(R.id.rcv_listarEventos_frgEventos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvEventos.setLayoutManager(layoutManager);
        rcvEventos.setItemAnimator(new DefaultItemAnimator());
        Listar_Eventos();
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public void Listar_Eventos(){
        Thread threadListarEventos = new Thread(new Runnable() {
            Handler handlerListarEventos = new Handler();
            @Override
            public void run() {
                objEventos = new Eventos();
                objUsuarios = new Usuarios();
                adapterEventos = new adapterEventos(getContext());
                listEventos = new ArrayList<clsEventos>();
                Log.e("Hola JHon ", "Rol "+objUsuarios.Obtener_VariableSession(getContext(), 2));
                listEventos = objEventos.Listar_Eventos(objUsuarios.Obtener_VariableSession(getContext(), 2));
                handlerListarEventos.post(new Runnable() {
                    @Override
                    public void run() {
                        if(listEventos!= null && listEventos.size() > 0){
                            adapterEventos.setListEventos(listEventos);
                            rcvEventos.setAdapter(adapterEventos);
                            adapterEventos.notifyDataSetChanged();
                        }
                    }
                });
            }
        });threadListarEventos.start();
    }
}
