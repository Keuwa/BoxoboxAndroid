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
        sections.add(new Section("Luminosité","Graphique de la luminosité dans la pièce où à été installer Boxobox",
                "http://icon-icons.com/icons2/621/PNG/512/lightbulb-1_icon-icons.com_56942.png","Show more","MONITOR",MonitorSectionFragment.LUMINOSITY));
        sections.add(new Section("Température","Graphique de la température dans la pièce où à été installer Boxobox",
                "https://lh4.ggpht.com/Pi0WM4k3Z-Dz-eRVxUD7Xk6NGfANEuABQfKvcsTxy_vNcOxvz2L8h8LwP5nQsyfNlBSR=w300","Show more","MONITOR",MonitorSectionFragment.TEMPERATURE));
        sections.add(new Section("Bruit","Graphique du bruit dans la pièce où à été installer Boxobox",
                "https://i.ytimg.com/vi/s5kc8xBMkXI/maxresdefault.jpg","Show more","MONITOR",MonitorSectionFragment.NOISE));
    }
}