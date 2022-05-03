package com.example.skullkingscorer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.skullkingscorer.viewmodel.Game;
import com.example.skullkingscorer.R;

import java.util.ArrayList;

public class InputTricks extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_tricks, container, false);

        Game game = new ViewModelProvider(getActivity()).get(Game.class);

        ArrayList<EditText> playerEditTexts = new ArrayList<>();
        playerEditTexts.add(view.findViewById(R.id.player1Tricks));
        playerEditTexts.add(view.findViewById(R.id.player2Tricks));
        playerEditTexts.add(view.findViewById(R.id.player3Tricks));
        playerEditTexts.add(view.findViewById(R.id.player4Tricks));
        playerEditTexts.add(view.findViewById(R.id.player5Tricks));
        playerEditTexts.add(view.findViewById(R.id.player6Tricks));

        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.add(view.findViewById(R.id.inputTricksPlayer1));
        textViews.add(view.findViewById(R.id.inputTricksPlayer2));
        textViews.add(view.findViewById(R.id.inputTricksPlayer3));
        textViews.add(view.findViewById(R.id.inputTricksPlayer4));
        textViews.add(view.findViewById(R.id.inputTricksPlayer5));
        textViews.add(view.findViewById(R.id.inputTricksPlayer6));

        Button changeBid = view.findViewById(R.id.changeBid);
        Button go = view.findViewById(R.id.inputTricksGo);
        Button wager = view.findViewById(R.id.wagerButton);

        for(int i=0; i<game.getNumberOfPlayers(); i++) {
            textViews.get(i).setText(game.getPlayer(i).getName() + ": " + game.getPlayer(i).getScore() + "/" + game.getPlayer(i).getBid());
        }

        for(int i=game.getNumberOfPlayers(); i<6; i++) {
            playerEditTexts.get(i).setVisibility(View.GONE);
            textViews.get(i).setVisibility(View.GONE);
        }

        TextView round = view.findViewById(R.id.inputTricksRound);
        round.setText("Round " + game.getRound());

        NavController controller = NavHostFragment.findNavController(this);
        changeBid.setOnClickListener(button -> {
            controller.navigate(R.id.action_inputTricks_to_changeBid);
        });

        go.setOnClickListener(button -> {
            ArrayList<Integer> playersTricks = new ArrayList<>();
            for(int i=0; i<game.getNumberOfPlayers(); i++) {
                EditText playerEdit = playerEditTexts.get(i);
                String text = playerEdit.getText().toString();
                int won = Integer.parseInt(text);
                playersTricks.add(won);
                game.getPlayer(i).setWon(won);
            }
            game.calculateScores(playersTricks);

            if(game.getRound() == 10) {
//                game.saveScores();
                controller.navigate(R.id.action_inputTricks_to_finalScore);}
            else {controller.navigate(R.id.action_inputTricks_to_chooseBonus);}
        });

        wager.setOnClickListener(button -> {
            controller.navigate(R.id.action_inputTricks_to_wager);
        });
        return view;
    }
}
