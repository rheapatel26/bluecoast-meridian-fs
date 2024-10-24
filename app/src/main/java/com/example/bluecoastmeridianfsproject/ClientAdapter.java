package com.example.bluecoastmeridianfsproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClientAdapter extends ArrayAdapter<Client> {

    public ClientAdapter(@NonNull Context context, List<Client> clients) {
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
        TextView clientFirstNameTextView = convertView.findViewById(R.id.clientFirstNameTextView);
        TextView clientTotalsTextView = convertView.findViewById(R.id.clientTotalsTextView); // Assuming a new TextView for totals

        // Set the first name
        if (client != null) {
            clientFirstNameTextView.setText(client.getFirstname());

            // Initialize totals
            int totalInvested = 0;
            int totalCurrent = 0;

            // Calculate totals for all stocks
            for (int i = 0; i < client.getStocknames().size(); i++) {
                int boughtQuantity = Math.toIntExact(client.getBought().get(i));
                int currentPrice = Math.toIntExact(client.getCurrent().get(i));
                int holdings = Math.toIntExact(client.getHoldings().get(i));

                // Accumulate total invested and current total
                totalInvested += boughtQuantity * holdings;
                totalCurrent += currentPrice * holdings;
            }

            // Set the totals information
            clientTotalsTextView.setText("Total Invested: " + totalInvested + ", Current Total: " + totalCurrent);
        }

        return convertView;
    }
}
