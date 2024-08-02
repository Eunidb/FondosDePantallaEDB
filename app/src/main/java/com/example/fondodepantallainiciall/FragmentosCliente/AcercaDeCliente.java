package com.example.fondodepantallainiciall.FragmentosCliente;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.fondodepantallainiciall.InicioSesion;
import com.example.fondodepantallainiciall.R;

public class AcercaDeCliente extends Fragment {

    Button Acceder;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_acerca_de_cliente, container, false);
        Acceder= view.findViewById(R.id.Acceder);

        Acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Forma 1
//                startActivity(new Intent(getActivity(), InicioSesion.class));

                //Forma 2
                Intent intent = new Intent(getActivity(), InicioSesion.class);
                startActivity(intent);
            }
        });

        return view;
    }
}