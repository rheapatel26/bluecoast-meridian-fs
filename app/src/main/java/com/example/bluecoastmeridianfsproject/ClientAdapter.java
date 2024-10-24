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
        TextView clientStocksTextView = convertView.findViewById(R.id.clientStocksTextView);

        // Set the first name
        if (client != null) {
            clientFirstNameTextView.setText(client.getFirstname());

            // Format stock information
            StringBuilder stocksInfo = new StringBuilder();
            for (int i = 0; i < client.getStocknames().size(); i++) {
                String stockName = client.getStocknames().get(i);
                int boughtQuantity = Math.toIntExact(client.getBought().get(i));
                int currentQuantity = Math.toIntExact(client.getHoldings().get(i)); // Assuming holdings is a List<Integer>

                stocksInfo.append(stockName)
                        .append(": Bought - ").append(boughtQuantity)
                        .append(", Current - ").append(currentQuantity)
                        .append("\n"); // New line for each stock
            }

            // Set the stocks information
            clientStocksTextView.setText(stocksInfo.toString().trim()); // Remove trailing newline
        }

        return convertView;
    }
}