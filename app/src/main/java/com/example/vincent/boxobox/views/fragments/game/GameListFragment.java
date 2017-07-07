package com.example.vincent.boxobox.views.fragments.game;

import com.example.vincent.boxobox.model.Section;
import com.example.vincent.boxobox.views.fragments.RecyclerSectionFragment;


// TODO: FAIRE UN PIANO !!!

public class GameListFragment extends RecyclerSectionFragment {

    public GameListFragment() {
        // Required empty public constructor
    }

    public static GameListFragment newInstance() {
        return new GameListFragment();
    }


    @Override
    public void loadSections() {
        sections.add(new Section("Piano","Application piano","https://www.transport-de-piano-paris.com/wp-content/uploads/2017/05/Demenagement-piano.jpg","Jouer","GAME","PIANO"));

    }
}
