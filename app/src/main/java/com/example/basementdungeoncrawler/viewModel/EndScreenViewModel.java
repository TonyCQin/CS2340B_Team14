package com.example.basementdungeoncrawler.viewModel;

import com.example.basementdungeoncrawler.Model.ScoresList;
import com.example.basementdungeoncrawler.Model.Score;

import java.util.ArrayList;

public class EndScreenViewModel {
        ScoresList scoreslist = ScoresList.getList();
        ArrayList<Score> top5 = scoreslist.getScores();


        //TODO: parse data here so endscreen can directcly generate it
}
