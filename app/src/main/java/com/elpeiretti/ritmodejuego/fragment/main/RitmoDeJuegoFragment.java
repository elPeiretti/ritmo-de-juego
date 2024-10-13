package com.elpeiretti.ritmodejuego.fragment.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.elpeiretti.ritmodejuego.data.ClubDao;
import com.elpeiretti.ritmodejuego.databinding.FragmentRitmoDeJuegoBinding;
import com.elpeiretti.ritmodejuego.domain.Club;
import com.elpeiretti.ritmodejuego.domain.Hoyo;

import java.util.Locale;

public class RitmoDeJuegoFragment extends Fragment {

    private FragmentRitmoDeJuegoBinding binding;
    private ArrayAdapter<Club> clubAdapter;

    private Club selectedClub;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRitmoDeJuegoBinding.inflate(inflater, container, false);
        binding.horaSalida.setFragmentManagerSupplier(this::getChildFragmentManager);
        binding.horaSalida.addTimeChangedListener((h,m) -> updateEstado());
        binding.horaJuego.setFragmentManagerSupplier(this::getChildFragmentManager);
        binding.horaJuego.addTimeChangedListener((h,m) -> updateEstado());
        binding.hoyoJuego.setNumberChangedListener(i -> updateEstado());
        binding.hoyoSalida.setNumberChangedListener(i -> updateEstado());

        clubAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line);
        binding.clubAutocomplete.setAdapter(clubAdapter);
        binding.clubAutocomplete.setOnItemClickListener(this::onAutocompleteItemClick);

        ClubDao.getInstance().findAll(clubs -> {
            clubAdapter.clear();
            clubAdapter.addAll(clubs);
            clubAdapter.notifyDataSetChanged();
        }, errorMessage -> Toast.makeText(requireContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show());

        return binding.getRoot();
    }

    private void onAutocompleteItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Object selectedItem = adapterView.getItemAtPosition(position);
        if (selectedItem instanceof Club) {
            selectedClub = (Club) selectedItem;
            updateEstado();
        } else {
            selectedClub = null;
        }

        InputMethodManager inputManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void updateEstado() {

        Integer hoyoSalida = binding.hoyoSalida.getSelectedNumber();
        Integer hoyoJuego = binding.hoyoJuego.getSelectedNumber();
        Integer horaSalida = binding.horaSalida.getHour();
        Integer minSalida = binding.horaSalida.getMinute();
        Integer horaJuego = binding.horaJuego.getHour();
        Integer minJuego = binding.horaJuego.getMinute();

        if (selectedClub == null || hoyoSalida == null || hoyoJuego == null ||
            horaJuego == null || minJuego == null || horaSalida == null || minSalida == null)
            return;

        int tiempoAcum = 0;
        for (int i = hoyoSalida; i < hoyoJuego; i++) {
            Hoyo h = selectedClub.getHoyos().get(i - 1);
            tiempoAcum += h.getHoras() * 60 + h.getMinutos();
        }
        int total = horaSalida * 60 + minSalida + tiempoAcum;
        int demora = horaJuego*60 + minJuego - total;

        if (demora>0) {
            binding.estadoText.setText("DEMORADO");
            binding.estadoTime.setVisibility(View.VISIBLE);
            binding.estadoTime.setText(String.format(String.format(Locale.getDefault(),"%02d:%02d", demora/60, demora%60)));
        } else {
            binding.estadoText.setText("EN HORARIO");
            binding.estadoTime.setVisibility(View.GONE);
        }

    }

}