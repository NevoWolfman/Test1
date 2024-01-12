package com.example.test1application;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class InputFragment extends Fragment {

    private static EditText nameET, calET;
    private static CheckBox goalCB;
    private static Button addBTN;

    private static Context context;

    public InputFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        context = requireContext();

        nameET = view.findViewById(R.id.nameET);
        calET = view.findViewById(R.id.calET);
        goalCB = view.findViewById(R.id.goalCB);
        addBTN = view.findViewById(R.id.addBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(requireContext());

                FoodModel fm = getNewFood();
                //checks the inputs and if adding to the db failed
                if(fm == null || db.addFood(fm) == -1)
                {
                    Toast.makeText(requireContext(), "Adding Failed", Toast.LENGTH_SHORT).show();
                }
                else {
                    FoodFragment.updateList(requireContext());
                }
            }
        });

        return view;
    }

    //gets the inputs from the EditTexts and checks them
    public static FoodModel getNewFood()
    {
        String name = nameET.getText().toString();
        String cal = calET.getText().toString();
        boolean goal = goalCB.isChecked();

        if(name.isEmpty() || cal.isEmpty())
        {
            Toast.makeText(context, "Fill inputs", Toast.LENGTH_SHORT).show();
            return null;
        }
        else if(name.length() > 10 || cal.length() > 8)
        {
            Toast.makeText(context, "Inputs too long", Toast.LENGTH_SHORT).show();
            return null;
        }
        return new FoodModel(name, Integer.parseInt(cal), goal);
    }
}