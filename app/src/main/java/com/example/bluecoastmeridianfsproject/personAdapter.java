package com.example.bluecoastmeridianfsproject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class personAdapter extends FirebaseRecyclerAdapter<
        person, personAdapter.personsViewholder> {

    public personAdapter(
            @NonNull FirebaseRecyclerOptions<person> options) {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull person model) {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.firstname.setText(model.getFirstname());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.stocksnames.setText(model.getStocksnames());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.holdings.setText(model.getHoldings());
    }

    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person, parent, false);
        return new personAdapter.personsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView firstname, stocksnames, holdings;

        public personsViewholder(@NonNull View itemView) {
            super(itemView);

            firstname
                    = itemView.findViewById(R.id.firstname);
            stocksnames = itemView.findViewById(R.id.stocksnames);
            holdings = itemView.findViewById(R.id.holdings);
        }
    }
}