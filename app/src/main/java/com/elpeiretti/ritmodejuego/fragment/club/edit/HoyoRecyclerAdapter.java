package com.elpeiretti.ritmodejuego.fragment.club.edit;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elpeiretti.ritmodejuego.component.CustomTimeInput;
import com.elpeiretti.ritmodejuego.component.LabeledEditText;
import com.elpeiretti.ritmodejuego.databinding.RowHoyoBinding;
import com.elpeiretti.ritmodejuego.domain.Club;
import com.elpeiretti.ritmodejuego.domain.Hoyo;

import java.util.function.Supplier;

public class HoyoRecyclerAdapter extends RecyclerView.Adapter<HoyoRecyclerAdapter.ViewHolder> {

    private Club club;
    private final Supplier<FragmentManager> fragmentManagerSupplier;

    public HoyoRecyclerAdapter(Supplier<FragmentManager> fragmentManagerSupplier, Club club) {
        this.fragmentManagerSupplier = fragmentManagerSupplier;
        this.club = club;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowHoyoBinding rowBinding = RowHoyoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HoyoRecyclerAdapter.ViewHolder(rowBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hoyo hoyo = club.getHoyos().get(position);

        holder.numeroHoyo.setText("Hoyo "+hoyo.getNumber());

        holder.tiempoHoyo.setFragmentManagerSupplier(this.fragmentManagerSupplier);
        if (hoyo.getHoras() != null && hoyo.getMinutos() != null)
            holder.tiempoHoyo.setTime(hoyo.getHoras(), hoyo.getMinutos());

        if (hoyo.getPar() != null)
            holder.parHoyo.setText(hoyo.getPar().toString());
        if (hoyo.getHandicap() != null)
            holder.handicapHoyo.setText(hoyo.getHandicap().toString());

        setListeners(holder, hoyo);
    }

    @Override
    public int getItemCount() {
        return club.getHoyos().size();
    }

    private void setListeners(ViewHolder holder, Hoyo hoyo) {
        holder.parHoyo.addTextChangedListener(editable -> {
            Integer par = editable.toString().isBlank() ? null : Integer.valueOf(editable.toString());
            hoyo.setPar(par);
        });
        holder.handicapHoyo.addTextChangedListener(editable -> {
            Integer hpc = editable.toString().isBlank() ? null : Integer.valueOf(editable.toString());
            hoyo.setHandicap(hpc);
        });
        holder.tiempoHoyo.addTimeChangedListener((h,m) -> {
            hoyo.setHoras(h);
            hoyo.setMinutos(m);
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView numeroHoyo;
        CustomTimeInput tiempoHoyo;
        LabeledEditText parHoyo;
        LabeledEditText handicapHoyo;

        public ViewHolder(RowHoyoBinding rowBinding) {
            super(rowBinding.getRoot());
            numeroHoyo = rowBinding.numero;
            tiempoHoyo = rowBinding.tiempo;
            parHoyo = rowBinding.par;
            handicapHoyo = rowBinding.handicap;
        }
    }

}
