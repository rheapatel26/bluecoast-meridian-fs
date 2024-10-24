package com.example.bluecoastmeridianfsproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PortfolioFragment extends Fragment {


    public PortfolioFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);
        TextView clientText;
        clientText = view.findViewById(R.id.clientText);

        // Get the arguments passed from the FirstFragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            String clientName = bundle.getString("clientName");


            // Display the client's name and details
            clientText.setText(clientName);
        }

        return view;

    }
}