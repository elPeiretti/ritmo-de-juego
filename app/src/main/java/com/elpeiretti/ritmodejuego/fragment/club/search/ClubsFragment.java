package com.elpeiretti.ritmodejuego.fragment.club.search;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elpeiretti.ritmodejuego.MainActivity;
import com.elpeiretti.ritmodejuego.R;
import com.elpeiretti.ritmodejuego.data.ClubDao;
import com.elpeiretti.ritmodejuego.databinding.FragmentClubsBinding;
import com.elpeiretti.ritmodejuego.util.TextChangedListener;


public class ClubsFragment extends Fragment {

    private MainActivity activity;
    private FragmentClubsBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity)
            activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClubsBinding.inflate(inflater, container, false);

        RecyclerView recycler = binding.clubsRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        recycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        ClubRecyclerAdapter adapter = new ClubRecyclerAdapter();
        adapter.setOnRowClickedListener(club -> {
            activity.setSelectedClub(club);
            NavHostFragment.findNavController(this).navigate(R.id.action_clubsFragment_to_editarClubFragment);
        });
        recycler.setAdapter(adapter);
        ClubDao.getInstance().findAll(adapter::updateData, errorMessage -> Log.e("CLUBS", errorMessage));

        binding.clubSearch.addTextChangedListener((TextChangedListener) editable ->
                adapter.getFilter().filter(editable.toString()));
        return binding.getRoot();
    }
}