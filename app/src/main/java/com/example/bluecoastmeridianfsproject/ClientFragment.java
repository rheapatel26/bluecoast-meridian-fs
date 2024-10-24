package com.example.bluecoastmeridianfsproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClientFragment extends Fragment {

    private ListView clientListView;
    private List<Client> clientList; // Change to hold Client objects
    private ClientAdapter adapter; // Use a custom adapter

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client, container, false);
        clientListView = view.findViewById(R.id.clientListView);
        clientList = new ArrayList<>();

        // Initialize Firebase Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clients");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clientList.clear(); // Clear the list to avoid duplicates
                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {
                    Client client = clientSnapshot.getValue(Client.class);
                    if (client != null) {
                        clientList.add(client); // Add the client to the list
                    }
                }
                // Set up the adapter
                adapter = new ClientAdapter(getContext(), clientList);
                clientListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });

        clientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item
                Client selectedClient = clientList.get(position);

                // Create a bundle to pass data to the second fragment
                Bundle bundle = new Bundle();
                bundle.putString("clientName", selectedClient.getFirstname());

                // Create an instance of the second fragment and pass the bundle
                PortfolioFragment portfoliofrag = new PortfolioFragment();
                portfoliofrag.setArguments(bundle);

                // Perform the fragment transaction to replace the current fragment with SecondFragment
                ((home) getActivity()).navigateToFragment(portfoliofrag, bundle);
            }
        });

        return view;
    }

}
