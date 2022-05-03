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

public class InputBids extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_bids, container, false);

        Game game = new ViewModelProvider(getActivity()).get(Game.class);


        ArrayList<EditText> playerEditTexts = new ArrayList<>();
        playerEditTexts.add(view.findViewById(R.id.player1Bid));
        playerEditTexts.add(view.findViewById(R.id.player2Bid));
        playerEditTexts.add(view.findViewById(R.id.player3Bid));
        playerEditTexts.add(view.findViewById(R.id.player4Bid));
        playerEditTexts.add(view.findViewById(R.id.player5Bid));
        playerEditTexts.add(view.findViewById(R.id.player6Bid));

        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.add(view.findViewById(R.id.inputBidsPlayer1));
        textViews.add(view.findViewById(R.id.inputBidsPlayer2));
        textViews.add(view.findViewById(R.id.inputBidsPlayer3));
        textViews.add(view.findViewById(R.id.inputBidsPlayer4));
        textViews.add(view.findViewById(R.id.inputBidsPlayer5));
        textViews.add(view.findViewById(R.id.inputBidsPlayer6));

        Button go = view.findViewById(R.id.inputBidsGo);
        NavController controller = NavHostFragment.findNavController(this);

        for(int i=0; i<game.getNumberOfPlayers(); i++) {
            textViews.get(i).setText(game.getPlayer(i).getName() + ": " + game.getPlayer(i).getScore());
        }

        for(int i=game.getNumberOfPlayers(); i<6; i++) {
            playerEditTexts.get(i).setVisibility(View.GONE);
        }

        TextView roundTextView = view.findViewById(R.id.inputBidsRound);
        roundTextView.setText("Round " + game.getRound());

        go.setOnClickListener(button -> {
            ArrayList<Integer> playersBids = new ArrayList<>();
            for(int i=0; i<game.getNumberOfPlayers(); i++) {
                EditText playerEdit = playerEditTexts.get(i);
                String text = playerEdit.getText().toString();
                playersBids.add(Integer.parseInt(text));

            }
            game.setBids(playersBids);
            controller.navigate(R.id.action_inputBids_to_inputTricks);
        });
        return view;
    }
}