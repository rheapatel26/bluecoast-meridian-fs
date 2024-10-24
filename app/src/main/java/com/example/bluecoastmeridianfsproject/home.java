package com.example.bluecoastmeridianfsproject;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
//manager
public class home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        bottomNavigationView
                = findViewById(R.id.bottomnavbar);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.markets);

    }
    GraphFragment graphfrag = new GraphFragment();
    GraphFragment youfrag = new GraphFragment();
    PortfolioFragment portfoliofrag = new PortfolioFragment();
    ClientFragment clientfrag = new ClientFragment();

    public void navigateToFragment(Fragment fragment, Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)  // Add to back stack so that the user can navigate back
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {
            case R.id.markets:
                navigateToFragment(new GraphFragment(), null);
                return true;

            case R.id.clients:
                navigateToFragment(new ClientFragment(), null);
                return true;

            case R.id.portfolio:
                navigateToFragment(new PortfolioFragment(), null);
                return true;
            case R.id.you:
                //navigateToFragment(new GraphFragment(), null);
                return true;
        }

        return false;
    }
}