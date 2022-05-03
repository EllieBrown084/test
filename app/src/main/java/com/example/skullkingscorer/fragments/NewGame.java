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
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.skullkingscorer.R;
import com.example.skullkingscorer.viewmodel.Game;


public class NewGame extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);
        Game game = new ViewModelProvider(getActivity()).get(Game.class);

        CheckBox expPack = (CheckBox)view.findViewById(R.id.expansionPackCheck);
        Button go = view.findViewById(R.id.newGameGo);
        EditText numberOfPlayersEditText = view.findViewById(R.id.numberOfPlayers);

        NavController controller = NavHostFragment.findNavController(this);

        expPack.setOnClickListener(checkbox -> {
            boolean checked = ((CheckBox) expPack).isChecked();
            //TODO send this information somewhere maybe
        });

        go.setOnClickListener(button -> {
            String text = numberOfPlayersEditText.getText().toString();
            Integer numberOfPlayers = Integer.parseInt(text);
            game.setNumberOfPlayers(numberOfPlayers);
            controller.navigate(R.id.action_newGame_to_inputPlayers);
        });

        return view;
    }

    public void pleaseHelp(View view) {
        // Check if box now checked
        boolean checked = ((CheckBox) view).isChecked();

    }
}