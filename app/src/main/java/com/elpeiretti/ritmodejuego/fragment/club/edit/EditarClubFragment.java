package com.elpeiretti.ritmodejuego.fragment.club.edit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elpeiretti.ritmodejuego.MainActivity;
import com.elpeiretti.ritmodejuego.databinding.FragmentEditarClubBinding;
import com.elpeiretti.ritmodejuego.domain.Club;


public class EditarClubFragment extends Fragment {

    private FragmentEditarClubBinding binding;
    private MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity)
            activity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditarClubBinding.inflate(inflater, container, false);

        RecyclerView recycler = binding.hoyosRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        recycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        HoyoRecyclerAdapter adapter = new HoyoRecyclerAdapter(this::getChildFragmentManager);
        recycler.setAdapter(adapter);

        Club club = activity.getSelectedClub();

        adapter.setHoyos(club.getHoyos());
        if (club.getId() == null) {
            activity.setToolbalTitle("Crear Club");
        }
        else {
            binding.clubName.setText(club.getName());
        }

        return binding.getRoot();
    }
}