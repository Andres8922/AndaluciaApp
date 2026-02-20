package com.example.andaluciaapp.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.andaluciaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SonidosFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private int[] listaCanciones;
    private String[] titulosCanciones;
    private int indiceCancionActual = 0;

    private TextView tvNombrePista;
    private FloatingActionButton btnPlayPause;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sonidos, container, false);

        // Tus 4 audios en la carpeta res/raw
        listaCanciones = new int[]{R.raw.cancion1, R.raw.cancion2, R.raw.cancion3, R.raw.cancion4};

        // Pon aquí los nombres reales de las canciones
        titulosCanciones = new String[]{"Sa matao Paco", "Pim Pam", "Un resplando", "Tu ere un porrero"};

        tvNombrePista = view.findViewById(R.id.tvNombrePista);
        btnPlayPause = view.findViewById(R.id.btnPlayPause);
        ImageButton btnSiguiente = view.findViewById(R.id.btnSiguiente);
        ImageButton btnAnterior = view.findViewById(R.id.btnAnterior);

        inicializarMediaPlayer();

        // Lógica del botón Play/Pausa alternado
        btnPlayPause.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    mediaPlayer.start();
                    btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                }
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
        tvNombrePista.setText(titulosCanciones[indiceCancionActual]);

        // Al cargar una canción nueva, el botón se pone en modo Play
        btnPlayPause.setImageResource(android.R.drawable.ic_media_play);

        // NUEVA LÓGICA: Cuando termina la canción, vuelve al principio y se pone en Pausa
        mediaPlayer.setOnCompletionListener(mp -> {
            mediaPlayer.seekTo(0); // Vuelve al segundo 0
            btnPlayPause.setImageResource(android.R.drawable.ic_media_play); // Cambia el icono a Play
        });
    }

    private void cambiarCancion(int cambio) {
        indiceCancionActual += cambio;

        if (indiceCancionActual >= listaCanciones.length) indiceCancionActual = 0;
        if (indiceCancionActual < 0) indiceCancionActual = listaCanciones.length - 1;

        inicializarMediaPlayer();

        // Arrancar automáticamente al pulsar siguiente/anterior
        mediaPlayer.start();
        btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}