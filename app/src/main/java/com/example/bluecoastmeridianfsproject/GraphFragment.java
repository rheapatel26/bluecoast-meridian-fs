package com.example.bluecoastmeridianfsproject;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class GraphFragment extends Fragment {

    public GraphFragment() {
        // Required empty public constructor
    }

    private static final String APIkey = "PEE287KX0VZOGFRR";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_graph, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Safely access the TextView after the view is created
        TextView text = view.findViewById(R.id.textViewgraphfrag);
        Button btn = view.findViewById(R.id.searchsubmitbtn);
        EditText searchbar = view.findViewById(R.id.stocksearchbar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock_search = searchbar.getText().toString();
                RequestQueue requestQueue;
                String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+stock_search+"&interval=5min&apikey="+APIkey;
                requestQueue = Volley.newRequestQueue(requireContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    // Extract the "Time Series (5min)" object
                                    JSONObject timeSeries = response.getJSONObject("Time Series (5min)");
                                    ArrayList<Entry> entries = new ArrayList<>();
                                    ArrayList<String> xLabels = new ArrayList<>();

                                    // Iterate through the keys (timestamps)
                                    Iterator<String> keys = timeSeries.keys();
                                    int index = 0;
                                    while (keys.hasNext()) {
                                        String timeStamp = keys.next();

                                        // Get the data for each time
                                        JSONObject timeData = timeSeries.getJSONObject(timeStamp);
                                        double stockPrice = timeData.getDouble("4. close");

                                        // Add the data to the list (x = index, y = stockPrice)
                                        entries.add(new Entry(index, (float) stockPrice));
                                        xLabels.add(timeStamp);
                                        index++;
                                    }
                                    plotGraph(entries, xLabels);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("ERROR", "Error parsing JSON: " + e.getMessage());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if(error.networkResponse!=null&& error.networkResponse.data!=null)
                                {
                                    String responeBody = new String(error.networkResponse.data);
                                    Log.e("API_ERROR", "Error Response: " + responeBody);
                                }
                                Log.e("API_ERROR", "Error fetching stock price: " + error.getMessage());
                            }
                        });

                requestQueue.add(request);
            }
        });





    }

    private void plotGraph(ArrayList<Entry> entries, ArrayList<String> xLabels) {
        LineChart lineChart = getView().findViewById(R.id.lineChart);
        LineDataSet dataSet = new LineDataSet(entries, "Stock Price");

        // Customize the dataset (optional)
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);

        // Create the LineData object
        LineData lineData = new LineData(dataSet);

        // Set the data to the chart
        lineChart.setData(lineData);
        lineChart.invalidate();  // Refresh the chart

        // Set X-Axis labels (time)
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels));
        xAxis.setLabelRotationAngle(-45); // Rotate labels for readability
        xAxis.setGranularity(1f); // Ensure one label per entry
    }
}