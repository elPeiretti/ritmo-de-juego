package com.elpeiretti.ritmodejuego.fragment.club.edit;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elpeiretti.ritmodejuego.databinding.RowHoyoBinding;
import com.elpeiretti.ritmodejuego.domain.Hoyo;

import java.util.ArrayList;
import java.util.List;

public class HoyoRecyclerAdapter extends RecyclerView.Adapter<HoyoRecyclerAdapter.ViewHolder> {

    private List<Hoyo> hoyos = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowHoyoBinding rowBinding = RowHoyoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HoyoRecyclerAdapter.ViewHolder(rowBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hoyo hoyo = hoyos.get(position);

        holder.numeroHoyo.setText("Hoyo "+hoyo.getNumber());
    }

    @Override
    public int getItemCount() {
        return hoyos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView numeroHoyo;
        EditText tiempoHoyo;
        EditText parHoyo;
        EditText handicapHoyo;

        public ViewHolder(RowHoyoBinding rowBinding) {
            super(rowBinding.getRoot());
            numeroHoyo = rowBinding.numero;
            tiempoHoyo = rowBinding.tiempo;
            parHoyo = rowBinding.par;
            handicapHoyo = rowBinding.handicap;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setHoyos(List<Hoyo> hoyos) {
        this.hoyos = hoyos;
        notifyDataSetChanged();
    }

}
