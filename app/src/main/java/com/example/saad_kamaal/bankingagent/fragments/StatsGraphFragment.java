package com.example.saad_kamaal.bankingagent.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.database.DatabaseUtilsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saad_kamaal.bankingagent.activities.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saad_kamaal on 7/31/2017.
 */

public class StatsGraphFragment extends Fragment implements ValueEventListener {

    private DatabaseReference graphRef;
    private PieChart statsGraphPC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats_graph, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        statsGraphPC = (PieChart) view.findViewById(R.id.stats_graph_pc);
        graphRef = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://virtual-agent-d9704.firebaseio.com/root");
        graphRef.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        HashMap<String, Long> graphData = (HashMap<String, Long>) dataSnapshot.getValue();
        drawGraph(graphData);
    }

    private void drawGraph(HashMap<String, Long> graphData) {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(graphData.get("personalLoans"), "Personal Loans"));
        entries.add(new PieEntry(graphData.get("carLoans"), "Car Loans"));
        PieDataSet dataSet = new PieDataSet(entries, "Loan Statistics");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setSliceSpace(0f);
        PieData pieData = new PieData(dataSet);
        statsGraphPC.setData(pieData);
        statsGraphPC.invalidate();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
