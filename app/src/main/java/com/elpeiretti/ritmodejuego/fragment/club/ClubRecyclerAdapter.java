package com.elpeiretti.ritmodejuego.fragment.club;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elpeiretti.ritmodejuego.databinding.RowClubBinding;
import com.elpeiretti.ritmodejuego.domain.Club;

import java.util.List;

public class ClubRecyclerAdapter extends RecyclerView.Adapter<ClubRecyclerAdapter.ViewHolder> {

    private List<Club> clubs;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowClubBinding rowBinding = RowClubBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Club club = clubs.get(position);
        holder.clubName.setText(club.getName());
    }

    @Override
    public int getItemCount() {
        return clubs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView clubName;

        public ViewHolder(RowClubBinding rowBinding) {
            super(rowBinding.getRoot());
            clubName = rowBinding.clubName;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Club> clubs) {
        this.clubs.clear();
        this.clubs.addAll(clubs);
        notifyDataSetChanged();
    }

}
