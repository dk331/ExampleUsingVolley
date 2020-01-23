package com.dhananjay.democreateaccount.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dhananjay.democreateaccount.R;
import com.dhananjay.democreateaccount.views.CustomBottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        CustomBottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration =
            new AppBarConfiguration.Builder(
                R.id.navigation_accounts, R.id.navigation_budget,
                R.id.navigation_blank,
                R.id.navigation_reports, R.id.navigation_dashboard
            ).build();
        NavController navController = Navigation.findNavController(this,
            R.id.nav_host_fragment);
       /* NavigationUI.setupActionBarWithNavController(this, navController,
                appBarConfiguration);*/
        NavigationUI.setupWithNavController(navView, navController);

        Menu menuNav = navView.getMenu();
        MenuItem nav_item = menuNav.findItem(R.id.navigation_blank);
        nav_item.setEnabled(false);
    }

    public void onFabClicked(View view) {
        startActivity(new Intent(BottomNavigationActivity.this,
            AddAccountActivity.class));
    }
}
