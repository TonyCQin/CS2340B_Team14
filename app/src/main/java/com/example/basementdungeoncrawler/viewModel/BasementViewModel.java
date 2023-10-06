package com.example.basementdungeoncrawler.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.basementdungeoncrawler.view.MainActivity;

public class BasementViewModel extends ViewModel {
    public void onQuitButtonClick() {
        System.exit(0);
    }
}
