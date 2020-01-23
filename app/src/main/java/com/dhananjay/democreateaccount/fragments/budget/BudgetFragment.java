package com.dhananjay.democreateaccount.fragments.budget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dhananjay.democreateaccount.R;

public class BudgetFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BudgetViewModel budgetViewModel = ViewModelProviders.of(this)
            .get(BudgetViewModel.class);
        View root = inflater.inflate(R.layout.fragment_budget, container,
            false);
        final TextView textView = root.findViewById(R.id.text_budget);
        budgetViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}