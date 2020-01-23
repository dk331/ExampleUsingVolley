package com.dhananjay.democreateaccount.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dhananjay.democreateaccount.R;
import com.dhananjay.democreateaccount.activity.AddAccountActivity;
import com.dhananjay.democreateaccount.adapters.ViewPagerAdapter;
import com.dhananjay.democreateaccount.utils.Constants;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountsFragment extends Fragment {

    private TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_accounts, container,
            false);


        CircleImageView circleImageView = root.findViewById(R.id.profile_image);
        ImageView imageView = root.findViewById(R.id.image_add);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                    AddAccountActivity.class));
            }
        });

        Picasso.get().load(Constants.profileUrl)
            .error(R.drawable.ic_person_black_24dp)
            .into(circleImageView);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // find views by id
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tablayout);

        // attach tablayout with viewpager
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter =
            new ViewPagerAdapter(getChildFragmentManager(),
                FragmentStatePagerAdapter
                    .BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        // add your fragments
        adapter.addFragment(new SavingsFragment(),
            getResources().getString(R.string.title_savings));
        adapter.addFragment(new CheckingsFragment(),
            getResources().getString(R.string.title_checkings));
        adapter.addFragment(new MyWalletFragment(),
            getResources().getString(R.string.title_my_wallet));

        // set adapter on viewpager
        viewPager.setAdapter(adapter);

        setupTabIcons();

        viewPager.setCurrentItem(1, true);
    }

    private void setupTabIcons() {
        Objects.requireNonNull(tabLayout.getTabAt(0))
            .setIcon(R.drawable.ic_home_black_24dp);
        Objects.requireNonNull(tabLayout.getTabAt(1))
            .setIcon(R.drawable.ic_home_black_24dp);
        Objects.requireNonNull(tabLayout.getTabAt(2))
            .setIcon(R.drawable.ic_home_black_24dp);
    }
}