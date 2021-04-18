package com.example.sbertask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sbertask.MainActivity;
import com.example.sbertask.R;
import com.example.sbertask.model.Valute;

import java.util.ArrayList;

public class ValuteAdapter extends RecyclerView.Adapter<ValuteAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Valute> valutes;
    private MainActivity mainActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView rateTextView;
        public TextView nominalTextView;
        public TextView charCodeTextView;


        public MyViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name);
            rateTextView = view.findViewById(R.id.rate);
            nominalTextView = view.findViewById(R.id.nominal);
            charCodeTextView = view.findViewById(R.id.charCode);
        }
    }

    public ValuteAdapter(Context context, ArrayList<Valute> valutes, MainActivity mainActivity) {
        this.context = context;
        this.valutes = valutes;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.valute_list_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Valute valute = valutes.get(position);

        holder.nameTextView.setText(valute.getName());
        holder.charCodeTextView.setText("(" + valute.getCharCode() + ")");
        holder.nominalTextView.setText(valute.getNominal() + ": ");
        holder.rateTextView.setText(valute.getRate());



        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

               mainActivity.addAndEditCars(valute, position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return valutes.size();
    }

}
