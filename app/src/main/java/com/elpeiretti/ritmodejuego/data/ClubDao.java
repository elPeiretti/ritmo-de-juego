package com.elpeiretti.ritmodejuego.data;

import androidx.annotation.NonNull;

import com.elpeiretti.ritmodejuego.domain.Club;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClubDao {

    private static ClubDao instance;

    private ClubDao() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static ClubDao getInstance() {
        if (instance == null)
            instance = new ClubDao();
        return instance;
    }

    public void save(Club club) {
        FirebaseDatabase.getInstance().getReference().push().setValue(club);
    }

    public void update(Club club) {
        FirebaseDatabase.getInstance().getReference().child(club.getId()).setValue(club);
    }

    public void delete(Club club) {
        FirebaseDatabase.getInstance().getReference().child(club.getId()).removeValue();
    }

    public void findAll(OnFindAllSuccessListener onFindAllSuccessListener, OnActionErrorListener onErrorListener) {
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Club> clubs = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Club club = data.getValue(Club.class);
                    if (club != null) {
                        club.setId(data.getKey());
                        clubs.add(club);
                    }
                }
                onFindAllSuccessListener.onSuccess(clubs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onErrorListener.onError(error.getMessage());
            }
        });
    }

    @FunctionalInterface
    public interface OnFindAllSuccessListener {
        void onSuccess(List<Club> clubs);
    }

    @FunctionalInterface
    public interface OnActionErrorListener {
        void onError(String errorMessage);
    }

}
