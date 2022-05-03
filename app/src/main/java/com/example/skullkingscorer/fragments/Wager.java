package com.example.skullkingscorer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.skullkingscorer.R;
import com.example.skullkingscorer.viewmodel.Game;

import java.util.ArrayList;

public class Wager extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wager, container, false);

        Game game = new ViewModelProvider(getActivity()).get(Game.class);
        NavController controller = NavHostFragment.findNavController(this);

        ArrayList<CheckBox> playerCheckBoxes = new ArrayList<>();
        playerCheckBoxes.add(view.findViewById(R.id.wagerPlayer1));
        playerCheckBoxes.add(view.findViewById(R.id.wagerPlayer2));
        playerCheckBoxes.add(view.findViewById(R.id.wagerPlayer3));
        playerCheckBoxes.add(view.findViewById(R.id.wagerPlayer4));
        playerCheckBoxes.add(view.findViewById(R.id.wagerPlayer5));
        playerCheckBoxes.add(view.findViewById(R.id.wagerPlayer6));

        ArrayList<CheckBox> pointCheckBoxes = new ArrayList<>();
        pointCheckBoxes.add(view.findViewById(R.id.points10CheckBox));
        pointCheckBoxes.add(view.findViewById(R.id.points20CheckBox));

        Button go = view.findViewById(R.id.wagerGo);

        for(int i=0; i<game.getNumberOfPlayers(); i++) {
            playerCheckBoxes.get(i).setText(game.getPlayer(i).getName());
        }

        for(int i=game.getNumberOfPlayers(); i<6; i++) {
            playerCheckBoxes.get(i).setVisibility(View.GONE);
        }

        go.setOnClickListener(button -> {
            int playerIndex = -1;
            int pointsWagered = 0;
            for(int i=0; i<game.getNumberOfPlayers(); i++) {
                if(playerCheckBoxes.get(i).isChecked()){
                    playerIndex = i;
                }
            }
            for(int i=0; i<2; i++) {
                if(pointCheckBoxes.get(i).isChecked()) {
                    pointsWagered = 10 + 10*i;
                }
            }

            if(playerIndex != -1 && pointsWagered != 0) {
                game.getPlayer(playerIndex).setWageredPoints(pointsWagered);
                System.out.println(game.getPlayer(playerIndex).getWageredPoints());
            }

            controller.navigate(R.id.action_wager_to_inputTricks);
        });

        return view;
    }
}
