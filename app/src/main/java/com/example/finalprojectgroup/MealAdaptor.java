package com.example.finalprojectgroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MealAdaptor extends ArrayAdapter {

    List<MealDetails> mealDetails;
    private final LayoutInflater layoutInflater;
    private final int layoutResource;


    public MealAdaptor(@NonNull Context context, int resource, List<MealDetails> mealDetails){
        super(context, resource);
        this.mealDetails = mealDetails;
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutResource = resource;
    }

    @Override
    public int getCount(){
        return mealDetails.size();
    }

    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        v = layoutInflater.inflate(layoutResource, parent,false);
        TextView mealDay = v.findViewById(R.id.mealday);
        TextView mealBreakfast = v.findViewById(R.id.mealbreakfast);
        TextView mealLunch = v.findViewById(R.id.mealLuch);
        TextView mealSnacks = v.findViewById(R.id.mealSnacks);
        TextView mealDinner = v.findViewById(R.id.mealDinner);
        mealDay.setText(mealDay.getText() + " " + mealDetails.get(position).getDay());
        mealBreakfast.setText(mealBreakfast.getText() + " " + mealDetails.get(position).getBreakfast());
        mealLunch.setText(mealLunch.getText() + " " + mealDetails.get(position).getLunch());
        mealSnacks.setText(mealSnacks.getText() + " " + mealDetails.get(position).getDinner());
        mealDinner.setText(mealDinner.getText() + " " + mealDetails.get(position).getSnacks());
        return v;
    }
}
