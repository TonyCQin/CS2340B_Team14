package com.example.basementdungeoncrawler.viewModel;

import com.example.basementdungeoncrawler.Model.ScoresList;

import java.util.ArrayList;

public class EndScreenViewModel {
        ScoresList scoreslist = ScoresList.getList();
        ArrayList<Integer> top5 = scoreslist.getScores();

        //TODO: parse data here so endscreen can directcly generate it
}
