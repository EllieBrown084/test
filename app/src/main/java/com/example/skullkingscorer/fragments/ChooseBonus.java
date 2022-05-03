package com.example.skullkingscorer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.skullkingscorer.viewmodel.Game;
import com.example.skullkingscorer.R;

import java.util.ArrayList;

public class ChooseBonus extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_bonus, container, false);

        Game game = new ViewModelProvider(getActivity()).get(Game.class);

        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(view.findViewById(R.id.Player1_button));
        buttons.add(view.findViewById(R.id.Player2_button));
        buttons.add(view.findViewById(R.id.Player3_button));
        buttons.add(view.findViewById(R.id.Player4_button));
        buttons.add(view.findViewById(R.id.Player5_button));
        buttons.add(view.findViewById(R.id.Player6_button));
        Button go = view.findViewById(R.id.chooseBonusGo);

        for(int i=0; i<6; i++) {
            Button button = buttons.get(i);
            ids.add(button.getId());
        }

        for(int i=0; i<game.getNumberOfPlayers(); i++) {
            buttons.get(i).setText(game.getPlayer(i).getName());
        }

        for(int i=game.getNumberOfPlayers(); i<6; i++) {
            buttons.get(i).setVisibility(View.GONE);
        }

        NavController controller = NavHostFragment.findNavController(this);
        go.setOnClickListener(button -> {
            game.incrementRound();
//            System.out.println("Round# " + game.getRound());
//            game.printScores();
            controller.navigate(R.id.action_chooseBonus_to_inputBids);
        });

        buttons.forEach(button -> {
           button.setOnClickListener(a -> {
               int id = button.getId();
               int playerNumber = ids.indexOf(id);
               game.setPlayerGettingBonus(playerNumber);
//               System.out.println("Player# " + game.getPlayerGettingBonus());
               controller.navigate(R.id.action_chooseBonus_to_inputBonus);
           });
        });
        return view;
    }

}
