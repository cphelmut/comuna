package com.example.comuna;

import android.content.Context;
import android.os.Bundle;

import Datos.clsNoticias;
import Logica.Noticias;
import adapter.adapterNoticias;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class frgNoticias extends Fragment {
    protected RecyclerView rcvNoticias;
    protected adapterNoticias adapterNoticias;
    protected Noticias objNoticias;
    protected List<clsNoticias> listNoticias;

    public frgNoticias() {
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
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);
        rcvNoticias = view.findViewById(R.id.rcv_listarNoticias_frgNoticias);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvNoticias.setLayoutManager(layoutManager);
        rcvNoticias.setItemAnimator(new DefaultItemAnimator());
        adapterNoticias = new adapterNoticias(getContext());
        Listar_Noticias();
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


    public void Listar_Noticias() {
        Thread threadListarNoticias = new Thread(new Runnable() {
            Handler handlerListarNoticias = new Handler();
            @Override
            public void run() {
                objNoticias = new Noticias();
                listNoticias = new ArrayList<clsNoticias>();
                listNoticias = objNoticias.Listar_Noticias();
                handlerListarNoticias.post(new Runnable() {
                    @Override
                    public void run() {
                        if(listNoticias!= null && listNoticias.size() > 0) {
                            adapterNoticias.setListNoticias(listNoticias);
                            rcvNoticias.setAdapter(adapterNoticias);
                            adapterNoticias.notifyDataSetChanged();
                        }
                    }
                });
            }
        });threadListarNoticias.start();
    }

}
