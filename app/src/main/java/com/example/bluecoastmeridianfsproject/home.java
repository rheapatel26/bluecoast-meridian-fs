package com.example.bluecoastmeridianfsproject;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {
            case R.id.markets:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, graphfrag)
                        .commit();
                return true;

            case R.id.clients:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, clientfrag)
                        .commit();
                return true;

            case R.id.portfolio:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, portfoliofrag)
                        .commit();
                return true;
            case R.id.you:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, youfrag)
                        .commit();
                return true;
        }

        return false;
    }
}