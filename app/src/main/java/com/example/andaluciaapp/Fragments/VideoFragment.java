package com.example.andaluciaapp.Fragments;

import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import com.example.andaluciaapp.R;

public class VideoFragment extends Fragment {

    private ExoPlayer player1, player2, player3;
    private PlayerView playerView1, playerView2, playerView3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        playerView1 = view.findViewById(R.id.playerView1);
        playerView2 = view.findViewById(R.id.playerView2);
        playerView3 = view.findViewById(R.id.playerView3);

        configurarControlDeVolumen(view);

        return view;
    }

    // Inicializamos los reproductores cuando la pantalla se hace visible
    @Override
    public void onStart() {
        super.onStart();

        // Inicializar Player 1
        player1 = new ExoPlayer.Builder(requireContext()).build();
        playerView1.setPlayer(player1);
        prepararVideo(player1, R.raw.video1);

        // Inicializar Player 2
        player2 = new ExoPlayer.Builder(requireContext()).build();
        playerView2.setPlayer(player2);
        prepararVideo(player2, R.raw.video2);

        // Inicializar Player 3
        player3 = new ExoPlayer.Builder(requireContext()).build();
        playerView3.setPlayer(player3);
        prepararVideo(player3, R.raw.video3);
    }

    private void prepararVideo(ExoPlayer player, int idVideoRaw) {
        String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + idVideoRaw;
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoPath));
        player.setMediaItem(mediaItem);
        player.prepare();
        // No le ponemos player.play() para que no arranquen los 3 a la vez al entrar
    }

    private void configurarControlDeVolumen(View view) {
        AudioManager audioManager = (AudioManager) requireActivity().getSystemService(Context.AUDIO_SERVICE);
        SeekBar seekBarVolumen = view.findViewById(R.id.seekBarVolumen);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBarVolumen.setMax(maxVolume);
        seekBarVolumen.setProgress(currentVolume);

        seekBarVolumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    // EVENTOS MULTIMEDIA IMPORTANTES

    // Pausar si minimizamos la app o abrimos otra cosa por encima
    @Override
    public void onPause() {
        super.onPause();
        if (player1 != null) player1.pause();
        if (player2 != null) player2.pause();
        if (player3 != null) player3.pause();
    }

    // Liberar la memoria completamente al salir del fragmento
    @Override
    public void onStop() {
        super.onStop();
        if (player1 != null) { player1.release(); player1 = null; }
        if (player2 != null) { player2.release(); player2 = null; }
        if (player3 != null) { player3.release(); player3 = null; }
    }
}