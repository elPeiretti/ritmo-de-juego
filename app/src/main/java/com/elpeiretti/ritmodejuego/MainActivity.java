package com.elpeiretti.ritmodejuego;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.elpeiretti.ritmodejuego.databinding.ActivityMainBinding;
import com.elpeiretti.ritmodejuego.domain.Club;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding viewBinding;
    private Club selectedClub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());

        NavController navController = NavHostFragment.findNavController(viewBinding.navHostFragment.getFragment());
        NavigationUI.setupWithNavController(viewBinding.toolbar, navController,
                new AppBarConfiguration.Builder(navController.getGraph()).build());

        viewBinding.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_ver_clubs) {
                navController.navigate(R.id.action_ritmoDeJuegoFragment_to_clubsFragment);
                return true;
            } else if (item.getItemId() == R.id.action_crear_club) {
                selectedClub = new Club();
                navController.navigate(R.id.action_clubsFragment_to_editarClubFragment);
                return true;
            }
            return false;
        });

        navController.addOnDestinationChangedListener((controller, navDestination, bundle) -> {
            int destinationId = navDestination.getId();
            if (destinationId == R.id.clubsFragment)
                setMenuItemVisible(1);
            else if (destinationId == R.id.editarClubFragment)
                setMenuItemVisible(2);
            else
                setMenuItemVisible(0);
        });

        setMenuItemVisible(0);
        setContentView(viewBinding.getRoot());
    }

    public void setMenuItemVisible(int itemIndex) {
        Menu menu = viewBinding.toolbar.getMenu();
        for (int i=0 ; i<menu.size(); i++)
            menu.getItem(i).setVisible(i == itemIndex);
    }

    public void setSelectedClub(Club club) {
        this.selectedClub = club;
    }

    public Club getSelectedClub() {
        return this.selectedClub;
    }

    public void setToolbalTitle(String text) {
        viewBinding.toolbar.setTitle(text);
    }
}