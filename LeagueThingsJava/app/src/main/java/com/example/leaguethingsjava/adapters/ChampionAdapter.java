package com.example.leaguethingsjava.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.leaguethingsjava.R;
import com.example.leaguethingsjava.data.models.Champion;

import java.util.ArrayList;

public class ChampionAdapter extends ArrayAdapter<Champion> {

    private int roleColorResourceId;

    public ChampionAdapter(Activity context, ArrayList<Champion> champions, int roleColorResourceId){
        super(context,0,champions);
        this.roleColorResourceId = roleColorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent, false
            );
        }
        Champion currentChampion =getItem(position);

        TextView championNameTextView = (TextView) listItemView.findViewById(R.id.name_text_view);
        championNameTextView.setText(currentChampion.getName());

        TextView subRoleTextView = (TextView) listItemView.findViewById(R.id.subRole_text_view);
        subRoleTextView.setText(currentChampion.getSubRole());

        ImageView iconImageView = (ImageView)listItemView.findViewById(R.id.image);
        iconImageView.setImageResource(currentChampion.getIconId());
        // Make sure the view is visible
        iconImageView.setVisibility(View.VISIBLE);

        View textContainer  = listItemView.findViewById(R.id.text_container);

        //Set proper color
        int color = ContextCompat.getColor(getContext(),roleColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
