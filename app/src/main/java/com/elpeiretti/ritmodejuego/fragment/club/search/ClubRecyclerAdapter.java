package com.elpeiretti.ritmodejuego.fragment.club.search;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.elpeiretti.ritmodejuego.databinding.RowClubBinding;
import com.elpeiretti.ritmodejuego.domain.Club;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ClubRecyclerAdapter extends ListAdapter<Club, ClubRecyclerAdapter.ViewHolder> implements Filterable {

    private List<Club> clubs = new ArrayList<>();
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence string) {
            FilterResults results = new FilterResults();
            List<Club> filtered = clubs.stream().filter(c -> c.getName().toLowerCase().contains(string)).collect(Collectors.toList());
            results.values = string.length() < 1 ? clubs : filtered;
            results.count = string.length() < 1 ? clubs.size() : filtered.size();
            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            submitList((List<Club>) filterResults.values);
        }
    };
    private Consumer<Club> onRowClickedListener;

    public ClubRecyclerAdapter() {
        super(new ClubDiffCallback());
    }

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
        holder.itemView.setOnClickListener(view -> onRowClickedListener.accept(club));
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView clubName;

        public ViewHolder(RowClubBinding rowBinding) {
            super(rowBinding.getRoot());
            clubName = rowBinding.clubName;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Club> clubs) {
        this.clubs.clear();
        this.clubs.addAll(clubs);
        // i am sorry :(
        submitList(null);
        submitList(this.clubs);
    }

    public void setOnRowClickedListener(Consumer<Club> listener) {
        this.onRowClickedListener = listener;
    }

    private static class ClubDiffCallback extends DiffUtil.ItemCallback<Club> {

        @Override
        public boolean areItemsTheSame(@NonNull Club oldItem, @NonNull Club newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Club oldItem, @NonNull Club newItem) {
            return areItemsTheSame(oldItem, newItem);
        }
    }

}
