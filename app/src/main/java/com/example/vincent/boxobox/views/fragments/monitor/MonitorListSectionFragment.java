package com.example.vincent.boxobox.views.fragments.monitor;

import com.example.vincent.boxobox.model.Section;
import com.example.vincent.boxobox.views.fragments.RecyclerSectionFragment;

public class MonitorListSectionFragment extends RecyclerSectionFragment {

    public MonitorListSectionFragment() {
        // Required empty public constructor
    }

    public static MonitorListSectionFragment newInstance() {
        return new MonitorListSectionFragment();
    }

    @Override
    public void loadSections() {
        sections.add(new Section("Monitor","description","https://www.w3schools.com/css/img_fjords.jpg","Show more"));
        sections.add(new Section("Titre 2","description 2","https://www.w3schools.com/css/img_fjords.jpg","Show more"));
    }
}
