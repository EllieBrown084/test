package com.example.skullkingscorer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.skullkingscorer.viewmodel.Game;
import com.example.skullkingscorer.R;
import com.example.skullkingscorer.viewmodel.Player;

import java.util.ArrayList;

public class InputBonus extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_bonus, container, false);

        Game game = new ViewModelProvider(getActivity()).get(Game.class);

        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        ArrayList<Integer> checkBoxesChecked = new ArrayList<>();
        checkBoxes.add(view.findViewById(R.id.captured14)); //1
        checkBoxes.add(view.findViewById(R.id.captured14Black)); //2
        checkBoxes.add(view.findViewById(R.id.capturedPirates)); //3
        checkBoxes.add(view.findViewById(R.id.capturedSkullKing)); //4
        checkBoxes.add(view.findViewById(R.id.lootCard)); //5
        checkBoxes.add(view.findViewById(R.id.wageredPoints)); //6

        ArrayList<EditText> editTexts = new ArrayList<>();
        editTexts.add(view.findViewById(R.id.captured14Amount)); //1
        editTexts.add(view.findViewById(R.id.capturedPiratesAmount)); //3
        editTexts.add(view.findViewById(R.id.wageredPointsAmount)); //6

        Button go = view.findViewById(R.id.inputBonusGo);
        Button addMoreBonus = view.findViewById(R.id.addMoreBonus);

        NavController controller = NavHostFragment.findNavController(this);
        go.setOnClickListener(button -> {
            Player player = game.getPlayer(game.getPlayerGettingBonus());
            for(int i=0; i<6; i++){
               if(checkBoxes.get(i).isChecked()){
                   checkBoxesChecked.add(i+1);
               }
            }

            for(int i=0; i<checkBoxesChecked.size(); i++){
                if(checkBoxesChecked.get(i) == 1){
                    String text = editTexts.get(0).getText().toString();
                    int fourteens = Integer.parseInt(text);
                    game.addBonus(player, checkBoxesChecked.get(i), fourteens, 0);
                }
                else if(checkBoxesChecked.get(i) == 3){
                    String text = editTexts.get(1).getText().toString();
                    int pirates = Integer.parseInt(text);
                    game.addBonus(player, checkBoxesChecked.get(i), 0, pirates);
                }
                else if(checkBoxesChecked.get(i) == 6) {
                    String text = editTexts.get(2).getText().toString();
                    int wager = Integer.parseInt(text);
                    game.handleWager(player, wager, player.getBid(), player.getWon());
                }
                else{
                    game.addBonus(player, checkBoxesChecked.get(i), 0, 0);
                }
            }
            game.incrementRound();
//            System.out.println("Round# " + game.getRound());
            game.printScores();
            controller.navigate(R.id.action_inputBonus_to_inputBids);
        });

        addMoreBonus.setOnClickListener(button -> {
            Player player = game.getPlayer(game.getPlayerGettingBonus());
            for(int i=0; i<6; i++){
                if(checkBoxes.get(i).isChecked()){
                    checkBoxesChecked.add(i+1);
                }
            }

            for(int i=0; i<checkBoxesChecked.size(); i++){
                if(checkBoxesChecked.get(i) == 1){
                    String text = editTexts.get(0).getText().toString();
                    int fourteens = Integer.parseInt(text);
                    game.addBonus(player, checkBoxesChecked.get(i), fourteens, 0);
                }
                else if(checkBoxesChecked.get(i) == 3){
                    String text = editTexts.get(1).getText().toString();
                    int pirates = Integer.parseInt(text);
                    game.addBonus(player, checkBoxesChecked.get(i), 0, pirates);
                }
                else if(checkBoxesChecked.get(i) == 6) {
                    String text = editTexts.get(2).getText().toString();
                    int wager = Integer.parseInt(text);
                    game.handleWager(player, wager, player.getBid(), player.getWon());
                }
                else{
                    game.addBonus(player, checkBoxesChecked.get(i), 0, 0);
                }
            }
            controller.navigate(R.id.action_inputBonus_to_chooseBonus);
        });
        return view;
    }
}
