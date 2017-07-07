package com.example.vincent.boxobox.views.viewholder;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vincent.boxobox.R;
import com.example.vincent.boxobox.model.Section;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vincent on 03/07/2017.
 */

public class SectionCardViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private SectionButtonClickInterface sectionButtonClickInterface;
    private Section section;

    @BindView(R.id.section_card_image)
    ImageView image;

    @BindView(R.id.section_card_title)
    TextView title;

    @BindView(R.id.section_card_description)
    TextView description;

    @BindView(R.id.section_card_button)
    Button button;



    public SectionCardViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        context = itemView.getContext();
        this.sectionButtonClickInterface = (SectionButtonClickInterface)context;
    }

    public void bind(Section section){
        this.section = section;
        title.setText(section.getTitle());
        description.setText(section.getDescription());
        button.setText(section.getCallToAction());

        //TODO créer le fragment qui correspond a la section : Surement faire une classe générale SectionFragment et des sous classe thermometre / jeux / .....

        Glide.with(image.getContext()).load(section.getImage()).into(image);
    }

    @OnClick(R.id.section_card_button)
    public void onButtonClicked(){
        sectionButtonClickInterface.onButtonClicked(section.getTypes());
    }

    public interface SectionButtonClickInterface {
        void onButtonClicked(List<String> title);
    }
}
