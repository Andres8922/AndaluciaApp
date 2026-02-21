package com.example.andaluciaapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.andaluciaapp.R;
import java.util.List;

public class LottieAdapter extends RecyclerView.Adapter<LottieAdapter.ViewHolder> {
    private List<String> titulos;
    private List<Integer> recursos;

    public LottieAdapter(List<String> titulos, List<Integer> recursos) {
        this.titulos = titulos;
        this.recursos = recursos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lottie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(titulos.get(position));
        holder.lottie.setAnimation(recursos.get(position));
    }

    @Override
    public int getItemCount() { return titulos.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv; LottieAnimationView lottie;
        public ViewHolder(View v) {
            super(v);
            tv = v.findViewById(R.id.tvTituloAnim);
            lottie = v.findViewById(R.id.lottieItemView);
        }
    }
}