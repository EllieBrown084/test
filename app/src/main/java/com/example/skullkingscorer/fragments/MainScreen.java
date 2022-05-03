package com.example.skullkingscorer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.skullkingscorer.viewmodel.Game;
import com.example.skullkingscorer.R;

public class MainScreen extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        Button newGame = view.findViewById(R.id.NewGame);
        Button scoreBoard = view.findViewById(R.id.ScoreBoardButton);

        NavController controller = NavHostFragment.findNavController(this);
        newGame.setOnClickListener(button -> {
            controller.navigate(R.id.action_mainScreen_to_newGame);
        });

        scoreBoard.setOnClickListener(button -> {
            controller.navigate(R.id.action_mainScreen_to_scoreBoard);
        });

        return view;
    }
}