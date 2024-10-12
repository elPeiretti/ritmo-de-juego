package com.elpeiretti.ritmodejuego.fragment.club.edit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elpeiretti.ritmodejuego.MainActivity;
import com.elpeiretti.ritmodejuego.data.ClubDao;
import com.elpeiretti.ritmodejuego.databinding.FragmentEditarClubBinding;
import com.elpeiretti.ritmodejuego.domain.Club;
import com.elpeiretti.ritmodejuego.domain.Hoyo;
import com.elpeiretti.ritmodejuego.util.TextChangedListener;


public class EditarClubFragment extends Fragment {

    private FragmentEditarClubBinding binding;
    private MainActivity activity;
    private Club club;

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
        club = activity.getSelectedClub();

        RecyclerView recycler = binding.hoyosRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        recycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        HoyoRecyclerAdapter adapter = new HoyoRecyclerAdapter(this::getChildFragmentManager, club);
        recycler.setAdapter(adapter);

        if (club.getId() == null) {
            activity.setToolbalTitle("Crear Club");
            activity.setMenuItemVisible(-1);
        }
        else {
            binding.clubName.setText(club.getName());
        }

        binding.clubName.addTextChangedListener((TextChangedListener) editable ->
                club.setName(editable.toString()));

        binding.saveClub.setOnClickListener(view -> {
            if (!allFieldsFilled()) {
                Toast.makeText(requireContext(), "Campos incompletos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (club.getId() == null)
                ClubDao.getInstance().save(club);
            else
                ClubDao.getInstance().update(club);
            NavHostFragment.findNavController(this).popBackStack();
        });

        return binding.getRoot();
    }

    private boolean allFieldsFilled() {
        if (club.getName() == null || club.getName().isBlank())
            return false;

        for (Hoyo h : club.getHoyos()) {
            if (h.hasNullValues())
                return false;
        }

        return true;
    }
}