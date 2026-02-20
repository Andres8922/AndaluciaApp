package com.example.andaluciaapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andaluciaapp.PersonajeAdapter;
import com.example.andaluciaapp.Personaje;
import com.example.andaluciaapp.R;

import java.util.ArrayList;
import java.util.List;

public class PersonajesFragment {

    private RecyclerView recyclerView;
    private PersonajeAdapter adapter;
    private List<Personaje> listaPersonajes;

    public PersonajesFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout que contiene el RecyclerView
        View view = inflater.inflate(R.layout.fragment_personajes, container, false);

        // Vincular el componente del XML
        recyclerView = view.findViewById(R.id.recyclerPersonajes);

        // Configurar cómo se dispone la lista (Vertical)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar la lista y añadir los datos de los personajes
        llenarListaPersonajes();

        // Configurar el adaptador con la lista y asignarlo al RecyclerView
        adapter = new PersonajeAdapter(listaPersonajes);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void llenarListaPersonajes() {
        listaPersonajes = new ArrayList<>();

        // IMPORTANTE: Asegúrate de tener estas imágenes en res/drawable
        // o cambia R.drawable.nombre por ic_launcher_background para probar.

        listaPersonajes.add(new Personaje(
                R.raw.juanymedio,
                "Juan y Medio",
                "Poeta y dramaturgo granadino, una de las figuras más influyentes de la literatura española del siglo XX."));

        listaPersonajes.add(new Personaje(
                R.raw.illojuan,
                "Illojuan",
                "Pintor y escultor malagueño, creador del movimiento cubista y genio del arte moderno."));

        listaPersonajes.add(new Personaje(
                R.raw.davidevil,
                "David Evil",
                "Artista polifacética jerezana, apodada 'La Faraona', icono mundial del flamenco y el folclore."));

        listaPersonajes.add(new Personaje(
                R.raw.pajaroazul,
                "Pajaro Azul",
                "Pintor barroco sevillano, autor de 'Las Meninas' y considerado uno de los grandes maestros de la pintura universal."));

        listaPersonajes.add(new Personaje(
                R.raw.juanqui,
                "Joaquín der beti",
                "Poeta onubense y Premio Nobel de Literatura, autor de la célebre obra 'Platero y yo'."));
    }
}
