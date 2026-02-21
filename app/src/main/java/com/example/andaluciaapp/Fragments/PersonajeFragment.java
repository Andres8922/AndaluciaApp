package com.example.andaluciaapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.andaluciaapp.Adapters.PersonajeAdapter;
import com.example.andaluciaapp.Modelos.Personaje;
import com.example.andaluciaapp.R;
import java.util.ArrayList;
import java.util.List;

public class PersonajeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personajes, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerPersonajes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Personaje> lista = new ArrayList<>();

        lista.add(new Personaje(R.raw.juanymedio, "Juan y Medio", "El presentador más querido de Andalucía y mediador oficial de la tercera edad."));
        lista.add(new Personaje(R.raw.illojuan, "IlloJuan", "LMDShow. El malagueño que ha conquistado Twitch con su humor y naturalidad."));
        lista.add(new Personaje(R.raw.davidevil, "David Evil", "Referente de la cultura digital y el estilo andaluz contemporáneo."));
        lista.add(new Personaje(R.raw.pajaroazul, "Pájaro Azul", "Personaje icónico de la cultura popular y las redes sociales en Andalucía."));
        lista.add(new Personaje(R.raw.juanqui, "Joaquín del Betis", "La alegría de Andalucía. Leyenda del fútbol y maestro del chiste y el buen rollo."));

        recyclerView.setAdapter(new PersonajeAdapter(lista));

        return view;
    }
}