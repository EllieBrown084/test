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

import com.example.skullkingscorer.viewmodel.Game;
import com.example.skullkingscorer.R;

import java.util.ArrayList;

//import dagger.hilt.android.AndroidEntryPoint;

public class InputPlayers extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_players, container, false);

        Game game = new ViewModelProvider(getActivity()).get(Game.class);

        ArrayList<EditText> playerEditTexts = new ArrayList<>();
        playerEditTexts.add(view.findViewById(R.id.Player1Edit));
        playerEditTexts.add(view.findViewById(R.id.Player2Edit));
        playerEditTexts.add(view.findViewById(R.id.Player3Edit));
        playerEditTexts.add(view.findViewById(R.id.Player4Edit));
        playerEditTexts.add(view.findViewById(R.id.Player5Edit));
        playerEditTexts.add(view.findViewById(R.id.Player6Edit));

        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.add(view.findViewById(R.id.Player1));
        textViews.add(view.findViewById(R.id.Player2));
        textViews.add(view.findViewById(R.id.Player3));
        textViews.add(view.findViewById(R.id.Player4));
        textViews.add(view.findViewById(R.id.Player5));
        textViews.add(view.findViewById(R.id.Player6));

        Button go = view.findViewById(R.id.inputPlayersGo);
        NavController controller = NavHostFragment.findNavController(this);

        for(int i=game.getNumberOfPlayers(); i<6; i++) {
            playerEditTexts.get(i).setVisibility(View.GONE);
            textViews.get(i).setVisibility(View.GONE);
        }

        go.setOnClickListener(button -> {
            ArrayList<String> playersNames = new ArrayList<>();
            for(int i=0; i<game.getNumberOfPlayers(); i++){
                EditText playerEdit = playerEditTexts.get(i);
                playersNames.add(playerEdit.getText().toString());

            }
            game.setPlayers(playersNames);

            controller.navigate(R.id.action_inputPlayers_to_inputBids);
        });

        return view;
    }
}