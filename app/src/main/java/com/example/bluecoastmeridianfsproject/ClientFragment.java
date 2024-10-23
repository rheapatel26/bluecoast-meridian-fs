package com.example.bluecoastmeridianfsproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        return view;
    }

    // Custom adapter class
    private class ClientAdapter extends ArrayAdapter<Client> {
        public ClientAdapter(@NonNull android.content.Context context, List<Client> clients) {
            super(context, 0, clients);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_client, parent, false);
            }

            // Get the data item for this position
            Client client = getItem(position);
            TextView clientInfoTextView = convertView.findViewById(R.id.clientInfoTextView);

            // Format the client info string
            if (client != null) {
                String info = client.getFirstname() + ": Holdings - " +
                        client.getHoldings() + ", Stocks - " +
                        client.getStocknames() + ", Bought - " +
                        client.getBought();
                clientInfoTextView.setText(info);
            }

            return convertView;
        }
    }
}
