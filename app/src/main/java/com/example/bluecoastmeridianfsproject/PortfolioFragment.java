package com.example.bluecoastmeridianfsproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


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
            ArrayList<Integer> boughtList = bundle.getIntegerArrayList("boughtList");
            ArrayList<Integer> currentList = bundle.getIntegerArrayList("currentList");
            ArrayList<Integer> holdingList = bundle.getIntegerArrayList("holdingList");
            ArrayList<String> stocknameList = bundle.getStringArrayList("stocknameList");
            ArrayList<Integer> profitList = new ArrayList<>();

            for (int i = 0; i < stocknameList.size(); i++) {
                profitList.add(currentList.get(i) -  boughtList.get(i));
            }
            // Display the clients name and details

            if (stocknameList != null && profitList != null) {

                setupPieChart(view, stocknameList, profitList);
                setupBarChart(view,stocknameList, profitList);
            } else {
                // Handle the case when stocknameList or currentList is null
                Toast.makeText(getContext(), "Stock names or current data is missing", Toast.LENGTH_SHORT).show();
            }
            if (stocknameList != null && holdingList != null && profitList != null && boughtList != null && currentList != null) {
                populateTable(view, stocknameList, holdingList, profitList, boughtList, currentList);
            }

            //setupPieChart(stocknameList, currentList);
            //setupBarChart(stocknameList, currentList);
            clientText.setText(clientName);
        }

        return view;
    }

    private void setupPieChart(View view, ArrayList<String> stocknameList, ArrayList<Integer> currentList) {
        PieChart pieChart = view.findViewById(R.id.pieChart); // Ensure you have a PieChart in your layout

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        // Prepare the data for the pie chart
        for (int i = 0; i < stocknameList.size(); i++) {
            pieEntries.add(new PieEntry(currentList.get(i), stocknameList.get(i)));
        }

        // Create the dataset and set it to the PieChart
        PieDataSet dataSet = new PieDataSet(pieEntries, "Stock Performance");
        dataSet.setValueTextSize(14f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); // Set colors for different segments

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // Refresh the chart
    }

    private void setupBarChart(View view, ArrayList<String> stocknameList, ArrayList<Integer> currentList) {
        BarChart barChart = view.findViewById(R.id.barChart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < stocknameList.size(); i++) {
            barEntries.add(new BarEntry(i, currentList.get(i))); // x-axis is the index, y-axis is the value
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Stock Performance");
        barDataSet.setValueTextSize(14f);
        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
        barChart.invalidate(); // Refresh the chart
    }

    private void populateTable(View rootView, ArrayList<String> stocknameList, ArrayList<Integer> holdingList, ArrayList<Integer> profitList, ArrayList<Integer> boughtList, ArrayList<Integer> currentList) {
        TableLayout tableLayout = rootView.findViewById(R.id.portfolioTable);

        // Loop through the data and add rows to the table
        for (int i = 0; i < stocknameList.size(); i++) {
            TableRow row = new TableRow(getContext());

            // Create TextViews for each column
            TextView stockName = new TextView(getContext());
            stockName.setText(stocknameList.get(i));
            stockName.setPadding(8, 8, 8, 8);

            TextView holding = new TextView(getContext());
            holding.setText(String.valueOf(holdingList.get(i)));
            holding.setPadding(8, 8, 8, 8);

            TextView profit = new TextView(getContext());
            profit.setText(String.valueOf(profitList.get(i)));
            profit.setPadding(8, 8, 8, 8);

            TextView bought = new TextView(getContext());
            bought.setText(String.valueOf(boughtList.get(i)));
            bought.setPadding(8, 8, 8, 8);

            TextView current = new TextView(getContext());
            current.setText(String.valueOf(currentList.get(i)));
            current.setPadding(8, 8, 8, 8);

            // Add the TextViews to the TableRow
            row.addView(stockName);
            row.addView(holding);
            row.addView(profit);
            row.addView(bought);
            row.addView(current);

            // Add the TableRow to the TableLayout
            tableLayout.addView(row);
        }
    }
}