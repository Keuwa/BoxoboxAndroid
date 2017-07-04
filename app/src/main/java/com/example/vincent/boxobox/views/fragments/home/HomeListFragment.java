package com.example.vincent.boxobox.views.fragments.home;

import com.example.vincent.boxobox.model.Section;
import com.example.vincent.boxobox.views.fragments.RecyclerSectionFragment;


public class HomeListFragment extends RecyclerSectionFragment {

    public HomeListFragment() {
        // Required empty public constructor
    }

    public static HomeListFragment newInstance() {
        return new HomeListFragment();
    }

    @Override
    public void loadSections() {
        sections.add(new Section("Titre 1","description","https://www.w3schools.com/css/img_fjords.jpg","Show more"));
        sections.add(new Section("Titre 2","description 2","https://www.w3schools.com/css/img_fjords.jpg","Show more"));
    }


}
