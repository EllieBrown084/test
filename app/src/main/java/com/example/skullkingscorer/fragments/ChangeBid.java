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

import com.example.skullkingscorer.viewmodel.Game;
import com.example.skullkingscorer.R;

import java.util.ArrayList;

public class ChangeBid extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_bid, container, false);

        Game game = new ViewModelProvider(getActivity()).get(Game.class);

        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(view.findViewById(R.id.changeBidPlayer1));
        checkBoxes.add(view.findViewById(R.id.changeBidPlayer2));
        checkBoxes.add(view.findViewById(R.id.changeBidPlayer3));
        checkBoxes.add(view.findViewById(R.id.changeBidPlayer4));
        checkBoxes.add(view.findViewById(R.id.changeBidPlayer5));
        checkBoxes.add(view.findViewById(R.id.changeBidPlayer6));
        CheckBox increment = view.findViewById(R.id.incrementBid);
        CheckBox decrement = view.findViewById(R.id.decrementBid);

        Button go = view.findViewById(R.id.changeBidGo);
        NavController controller = NavHostFragment.findNavController(this);
        go.setOnClickListener(button -> {
            for(int i=0; i<game.getNumberOfPlayers(); i++){
                boolean checked = ((CheckBox) checkBoxes.get(i)).isChecked();
                if(checked){
                    if(increment.isChecked()) {game.changeBid(1, game.getPlayer(i));}
                    else if(decrement.isChecked()) {game.changeBid(-1, game.getPlayer(i));}
                }
            }
            controller.navigate(R.id.action_changeBid_to_inputTricks);
        });
        return view;
    }
}
