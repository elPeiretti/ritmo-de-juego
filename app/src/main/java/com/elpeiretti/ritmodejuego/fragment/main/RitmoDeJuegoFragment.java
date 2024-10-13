package com.elpeiretti.ritmodejuego.fragment.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.elpeiretti.ritmodejuego.data.ClubDao;
import com.elpeiretti.ritmodejuego.databinding.FragmentRitmoDeJuegoBinding;
import com.elpeiretti.ritmodejuego.domain.Club;

public class RitmoDeJuegoFragment extends Fragment {

    private FragmentRitmoDeJuegoBinding binding;
    private ArrayAdapter<Club> clubAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRitmoDeJuegoBinding.inflate(inflater, container, false);
        binding.horaSalida.setFragmentManagerSupplier(this::getChildFragmentManager);
        binding.horaJuego.setFragmentManagerSupplier(this::getChildFragmentManager);

        clubAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line);
        binding.clubAutocomplete.setAdapter(clubAdapter);
        binding.clubAutocomplete.setOnItemClickListener((adapterView, view, i, l) -> {
            InputMethodManager inputManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        });

        ClubDao.getInstance().findAll(clubs -> {
            clubAdapter.clear();
            clubAdapter.addAll(clubs);
            clubAdapter.notifyDataSetChanged();
        }, errorMessage -> Toast.makeText(requireContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show());

        return binding.getRoot();
    }
}