package com.example.test1application;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test1application.databinding.FragmentFoodBinding;

import java.util.List;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.ViewHolder> {

    //the list of the data
    private List<FoodModel> mValues;

    public FoodRecyclerViewAdapter(List<FoodModel> items) {
        mValues = items;
    }

    //called on each RecyclerView part creation
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentFoodBinding.inflate(LayoutInflater.from(parent.getContext())));

    }

    //sets the data from the list to the view holder
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nameTV.setText(mValues.get(position).getName());
        holder.calTV.setText(String.valueOf(mValues.get(position).getCalories()));
        holder.goalTV.setText(String.valueOf(mValues.get(position).isGoalAchieved()));
    }

    //returns the amount of data currently in the list
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView nameTV;
        public final TextView calTV;
        public final TextView goalTV;

        public Button updateBTN, deleteBTN;
        public FoodModel mItem;

        private Context context;

        public ViewHolder(FragmentFoodBinding binding) {
            super(binding.getRoot());
            nameTV = binding.nameTV;
            calTV = binding.calTV;
            goalTV = binding.goalTV;
            updateBTN = binding.updateBTN;
            deleteBTN = binding.deleteBTN;

            updateBTN.setOnClickListener(this);
            deleteBTN.setOnClickListener(this);

            context = binding.getRoot().getContext();
        }

        @Override
        public void onClick(View view) {
            DatabaseHelper db  =new DatabaseHelper(context);
            if(view == updateBTN)
            {
                int id = mItem.id;
                mItem = InputFragment.getNewFood();
                if(mItem == null || db.updateFood(id, mItem) == -1)
                {
                    Toast.makeText(context, "Updating failed", Toast.LENGTH_SHORT).show();
                }
            }else if(view == deleteBTN)
            {
                if(db.deleteFood(mItem.id) == -1)
                {
                    Toast.makeText(context, "Deleting failed", Toast.LENGTH_SHORT).show();
                }
            }
            FoodFragment foodFragment = new FoodFragment();
            FoodFragment.updateList(context);
        }
    }
}