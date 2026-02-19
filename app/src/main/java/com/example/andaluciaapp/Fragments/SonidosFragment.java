package com.example.andaluciaapp.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.andaluciaapp.R;

public class SonidosFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private int[] listaCanciones; // Asegúrate de tener audios en res/raw
    private int indiceCancionActual = 0;
    private TextView tvNombrePista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sonidos, container, false);

        // Referencias a tus mp3 (Crea la carpeta res/raw y añade 4 canciones)
        listaCanciones = new int[]{R.raw.cancion1, R.raw.cancion2, R.raw.cancion3, R.raw.cancion4};

        tvNombrePista = view.findViewById(R.id.tvNombrePista);
        Button btnPlay = view.findViewById(R.id.btnPlay);
        Button btnPause = view.findViewById(R.id.btnPause);
        Button btnSiguiente = view.findViewById(R.id.btnSiguiente);
        Button btnAnterior = view.findViewById(R.id.btnAnterior);

        inicializarMediaPlayer();

        btnPlay.setOnClickListener(v -> {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

        btnPause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        btnSiguiente.setOnClickListener(v -> cambiarCancion(1));
        btnAnterior.setOnClickListener(v -> cambiarCancion(-1));

        return view;
    }

    private void inicializarMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(getContext(), listaCanciones[indiceCancionActual]);
        tvNombrePista.setText("Pista " + (indiceCancionActual + 1));
    }

    private void cambiarCancion(int cambio) {
        indiceCancionActual += cambio;
        if (indiceCancionActual >= listaCanciones.length) indiceCancionActual = 0;
        if (indiceCancionActual < 0) indiceCancionActual = listaCanciones.length - 1;

        inicializarMediaPlayer();
        mediaPlayer.start();
    }

    // GESTIÓN DE EVENTOS MULTIMEDIA IMPORTANTÍSIMA
    // Se detiene al cambiar de pantalla o minimizar la app
    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    // Libera recursos cuando el Fragment se destruye del todo
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}