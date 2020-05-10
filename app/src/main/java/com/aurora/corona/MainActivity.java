/*
 * Corona Stats
 * Copyright (C) 2020, Rahul Kumar Patel <auroraoss.dev@gmail.com>
 *
 * Aurora Store is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 * Corona Stats is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Aurora Store.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aurora.corona;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aurora.corona.viewmodel.CaseReportModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarConfiguration mAppBarConfiguration;
    @BindView(R.id.container)
    CoordinatorLayout container;
    @BindView(R.id.nav_view)
    BottomNavigationView bottomNavigationView;


//    @BindView(R.id.swipe_layout)
//    SwipeRefreshLayout swipeLayout;
private boolean doubleBackPressed=false;


    static boolean matchDestination(@NonNull NavDestination destination, @IdRes int destId) {
        NavDestination currentDestination = destination;
        while (currentDestination.getId() != destId && currentDestination.getParent() != null) {
            currentDestination = currentDestination.getParent();
        }
        return currentDestination.getId() == destId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_format_list_bulleted_black_24dp);
        getSupportActionBar().setTitle("   COVID-19 Tracker");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view2);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.precaution, R.id.symptom,
//                R.id.nav_slideshow,
//                R.id.nav_tools,
//                R.id.nav_share,
                R.id.aboutdeveloper)
                .setDrawerLayout(drawer)
                .build();
        NavController navcontroller = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navcontroller, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navcontroller);
            navigationView.setNavigationItemSelectedListener(this);

        toolbar.setOnClickListener(view -> drawer.openDrawer(GravityCompat.START));
//        ActionBarDrawerToggle toggle;
//        toggle = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ButterKnife.bind(this);

        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //Avoid Adding same fragment to NavController, if clicked on current BottomNavigation item
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == bottomNavigationView.getSelectedItemId())
                return false;
            NavigationUI.onNavDestinationSelected(item, navController);

            return true;
        });

        //Check correct BottomNavigation item, if navigation_main is done programmatically
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            final Menu menu = bottomNavigationView.getMenu();
            final int size = menu.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = menu.getItem(i);
                if (matchDestination(destination, item.getItemId())) {
                    item.setChecked(true);
                }
            }

        });

        final CaseReportModel caseReportModel = new ViewModelProvider(this).get(CaseReportModel.class);
        caseReportModel.getData().observe(this, result -> {
            if (result)
                showSnackBar("Database updated", null);
//            swipeLayout.setRefreshing(false);
        });

        caseReportModel.getError().observe(this, s -> {
            showSnackBar("Failed to retrieve new data", v -> caseReportModel.fetchOnlineData());
//            swipeLayout.setRefreshing(false);
        });

//        swipeLayout.setOnRefreshListener(caseReportModel::fetchOnlineData);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    protected void onPause() {
//        swipeLayout.setRefreshing(false);
        super.onPause();
    }

    protected void showSnackBar(String message, View.OnClickListener clickListener) {
        Snackbar snackbar = Snackbar.make(container, message, Snackbar.LENGTH_SHORT);
        snackbar.setAnchorView(bottomNavigationView);
        snackbar.setTextColor(getResources().getColor(R.color.colorAccent));
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorBackground));
        if (clickListener != null)
            snackbar.setAction("Retry", clickListener);
        snackbar.show();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(doubleBackPressed) {
            super.onBackPressed();
        }
        else{
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }
            else{
                doubleBackPressed=true;
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> doubleBackPressed=false, 2000);
            }

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.precaution:
                Intent i=new Intent(this,AboutCorona.class);
                startActivity(i);
                break;
            case R.id.symptom:
                Intent j=new Intent(this,Symptoms.class);
                startActivity(j);
                break;
            case R.id.aboutdeveloper:
                Intent k=new Intent(this,AboutDeveloper.class);
                startActivity(k);
                break;
        }
        return false;
    }
}
