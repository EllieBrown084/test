package com.example.skullkingscorer.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.skullkingscorer.viewmodel.Game;
import com.example.skullkingscorer.R;

import java.util.ArrayList;

public class FinalScore extends Fragment {
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_final_score, container, false);

        Game game = new ViewModelProvider(getActivity()).get(Game.class);
        NavController controller = NavHostFragment.findNavController(this);

        ArrayList<TextView> nameTextViews = new ArrayList<>();
        ArrayList<TextView> scoreTextViews = new ArrayList<>();

        nameTextViews.add(view.findViewById(R.id.finalFirstName));
        nameTextViews.add(view.findViewById(R.id.finalSecondName));
        nameTextViews.add(view.findViewById(R.id.finalThirdName));
        nameTextViews.add(view.findViewById(R.id.finalFourthName));
        nameTextViews.add(view.findViewById(R.id.finalFifthName));
        nameTextViews.add(view.findViewById(R.id.finalSixthName));

        scoreTextViews.add(view.findViewById(R.id.finalFirstScore));
        scoreTextViews.add(view.findViewById(R.id.finalSecondScore));
        scoreTextViews.add(view.findViewById(R.id.finalThirdScore));
        scoreTextViews.add(view.findViewById(R.id.finalFourthScore));
        scoreTextViews.add(view.findViewById(R.id.finalFifthScore));
        scoreTextViews.add(view.findViewById(R.id.finalSixthScore));

        for(int i=0; i<game.getNumberOfPlayers(); i++){
            nameTextViews.get(i).setText(game.getPlayer(i).getName());
            scoreTextViews.get(i).setText(Integer.toString(game.getPlayer(i).getScore()));
        }

        Button back = view.findViewById(R.id.redo);
        back.setOnClickListener(button -> {
            controller.navigate(R.id.action_finalScore_to_mainScreen);
        });

        return view;
    }
}
