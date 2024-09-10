package com.elpeiretti.ritmodejuego.fragment.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elpeiretti.ritmodejuego.databinding.FragmentRitmoDeJuegoBinding;

public class RitmoDeJuegoFragment extends Fragment {

    private FragmentRitmoDeJuegoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRitmoDeJuegoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}