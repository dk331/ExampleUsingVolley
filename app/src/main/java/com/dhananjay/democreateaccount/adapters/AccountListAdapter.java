package com.dhananjay.democreateaccount.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dhananjay.democreateaccount.R;
import com.dhananjay.democreateaccount.models.Account;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AccountListAdapter extends
    RecyclerView.Adapter<AccountListAdapter.MyViewHolder> {

    private ArrayList<Account> dataSet;

    public AccountListAdapter(ArrayList<Account> data) {
        this.dataSet = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.cards_layout, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public void updateList(ArrayList<Account> accountArrayList) {
        dataSet.clear();
        dataSet.addAll(0, accountArrayList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,
                                 final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getTitle());
        textViewVersion.setText(dataSet.get(listPosition).getSubTitle());

        Picasso.get()
            .load(dataSet.get(listPosition).getImageUrl())
            .into(imageView);
    }

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        } else {
            return 0;
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textTitle);
            this.textViewVersion = itemView.findViewById(R.id.textSubTitle);
            this.imageViewIcon = itemView.findViewById(R.id.imageView);
        }
    }
}
