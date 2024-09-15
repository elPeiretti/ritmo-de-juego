package com.elpeiretti.ritmodejuego.fragment.club;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elpeiretti.ritmodejuego.databinding.FragmentClubsBinding;

public class ClubsFragment extends Fragment {

    private FragmentClubsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClubsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}