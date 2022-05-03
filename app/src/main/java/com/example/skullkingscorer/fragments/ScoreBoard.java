package com.example.skullkingscorer.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.skullkingscorer.R;
import com.example.skullkingscorer.model.HighScore;
import com.example.skullkingscorer.viewmodel.Game;

import java.util.ArrayList;


public class ScoreBoard extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score_board, container, false);

        Game game = new ViewModelProvider(getActivity()).get(Game.class);
        NavController controller = NavHostFragment.findNavController(this);

        ArrayList<TextView> nameTextViews = new ArrayList<>();
        ArrayList<TextView> scoreTextViews = new ArrayList<>();

        nameTextViews.add(view.findViewById(R.id.firstPlaceName));
        nameTextViews.add(view.findViewById(R.id.secondPlaceName));
        nameTextViews.add(view.findViewById(R.id.thirdPlaceName));
        nameTextViews.add(view.findViewById(R.id.fourthPlaceName));
        nameTextViews.add(view.findViewById(R.id.fifthPlaceName));

        scoreTextViews.add(view.findViewById(R.id.firstPlaceScore));
        scoreTextViews.add(view.findViewById(R.id.secondPlaceScore));
        scoreTextViews.add(view.findViewById(R.id.thirdPlaceScore));
        scoreTextViews.add(view.findViewById(R.id.fourthPlaceScore));
        scoreTextViews.add(view.findViewById(R.id.fifthPlaceScore));

        ArrayList<HighScore> highScores = game.getHighScores();

        int number;
        if(highScores.size()<5) {number = highScores.size();}
        else {number = 5;}

        for(int i=0; i<number; i++) {
            nameTextViews.get(i).setText(highScores.get(i).name);
            scoreTextViews.get(i).setText(highScores.get(i).score);
        }

        Button back = view.findViewById(R.id.backToMain);
        back.setOnClickListener(button -> {
            controller.navigate(R.id.action_scoreBoard_to_mainScreen);

        });



        return view;
    }
}