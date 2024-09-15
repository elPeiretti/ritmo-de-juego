package com.elpeiretti.ritmodejuego.fragment.club;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elpeiretti.ritmodejuego.databinding.FragmentClubsBinding;
import com.elpeiretti.ritmodejuego.domain.Club;

import java.util.ArrayList;
import java.util.Collections;

public class ClubsFragment extends Fragment {

    private FragmentClubsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClubsBinding.inflate(inflater, container, false);

        RecyclerView recycler = binding.clubsRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        recycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        // TODO: update with repository
        ArrayList<Club> clubList = new ArrayList<>();
        for(int i=1; i<11; i++) {
            Club c = new Club();
            c.setId((long) i);
            c.setName("Club Number " + i);
            clubList.add(c);
        }
        recycler.setAdapter(new ClubRecyclerAdapter(clubList));
        return binding.getRoot();
    }
}