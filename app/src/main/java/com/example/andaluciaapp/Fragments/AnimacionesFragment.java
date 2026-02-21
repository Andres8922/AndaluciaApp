package com.example.andaluciaapp.Fragments;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.andaluciaapp.R;
import com.example.andaluciaapp.Adapters.LottieAdapter;
import java.util.Arrays;

public class AnimacionesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_animaciones, container, false);

        RecyclerView rv = v.findViewById(R.id.rvAnimaciones);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // Asegúrate de tener estos 5 archivos en res/raw
        LottieAdapter adapter = new LottieAdapter(
                Arrays.asList("Toro", "Guitarra", "Olivas", "El Sol", "Feria"),
                Arrays.asList(R.raw.bull, R.raw.guitarra, R.raw.olives, R.raw.sun, R.raw.feria)
        );

        rv.setAdapter(adapter);
        return v;
    }
}