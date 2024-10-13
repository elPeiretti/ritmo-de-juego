package com.elpeiretti.ritmodejuego;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.elpeiretti.ritmodejuego.data.ClubDao;
import com.elpeiretti.ritmodejuego.databinding.ActivityMainBinding;
import com.elpeiretti.ritmodejuego.domain.Club;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding viewBinding;
    private NavController navController;
    private Club selectedClub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupSplashScreen();
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(viewBinding.navHostFragment.getFragment());
        NavigationUI.setupWithNavController(viewBinding.toolbar, navController,
                new AppBarConfiguration.Builder(navController.getGraph()).build());

        viewBinding.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_ver_clubs) {
                navController.navigate(R.id.action_ritmoDeJuegoFragment_to_clubsFragment);
                return true;
            }
            if (item.getItemId() == R.id.action_crear_club) {
                selectedClub = new Club();
                navController.navigate(R.id.action_clubsFragment_to_editarClubFragment);
                return true;
            }
            if (item.getItemId() == R.id.action_eliminar_club) {
                showDeleteClubDialog();
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

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {}
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

    private void showDeleteClubDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar club")
                .setMessage(String.format("Está seguro que desea eliminar el club: %s?", selectedClub.getName()))
                .setPositiveButton("Eliminar", (dialogInterface, i) -> {
                    ClubDao.getInstance().delete(selectedClub);
                    navController.popBackStack();
                })
                .setNeutralButton("Cancelar", null)
                .show();
    }

    private void setupSplashScreen() {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setOnExitAnimationListener(splashScreenViewProvider -> {
            final ObjectAnimator slideUp = ObjectAnimator.ofFloat(
                    splashScreenViewProvider.getView(),
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenViewProvider.getView().getHeight()
            );
            slideUp.setInterpolator(new AccelerateInterpolator());
            slideUp.setDuration(500L);
            slideUp.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    splashScreenViewProvider.remove();
                }
            });
            slideUp.start();
        });
    }
}