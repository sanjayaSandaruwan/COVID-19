package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;



import java.util.ArrayList;

public class Statics extends AppCompatActivity implements IMainPresenter.View{

    private ProgressBar progressBar;
    private TextView tvError;
    private BarChart barChart;

    private MainPresenterImpl mainPresenter;

    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statics);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("COVID-19");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progress_bar);
        tvError = findViewById(R.id.tv_error);
        barChart = findViewById(R.id.bar_chart);

        mainPresenter = new MainPresenterImpl(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.downloadData();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);
        barChart.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
        barChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        progressBar.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        barChart.setVisibility(View.GONE);

        tvError.setText(error);
    }

    @Override
    public void dataDownloadedSuccessfully(ArrayList<String> datesArrayList, ArrayList<Status> statusArrayList) {
        ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
        for (int i = 0; i < datesArrayList.size(); i++) {
            barEntryArrayList.add(new BarEntry(statusArrayList.get(i).getConfirmed(), i));
        }

        BarDataSet barDataSet = new BarDataSet(barEntryArrayList, "Confirmed");
        BarData barData = new BarData(datesArrayList,barDataSet);
        barChart.setData(barData);
        barChart.animateY(3000);
    }



}
