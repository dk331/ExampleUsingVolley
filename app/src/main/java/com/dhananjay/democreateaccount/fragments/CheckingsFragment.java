package com.dhananjay.democreateaccount.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.dhananjay.democreateaccount.R;
import com.dhananjay.democreateaccount.activity.AddAccountActivity;

public class CheckingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_checkings, container,
            false);

        AppCompatButton button = root.findViewById(R.id.btn_add_account);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                    AddAccountActivity.class));
            }
        });

        return root;
    }
}
