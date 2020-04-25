package com.example.covid_19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        TextView a = view.findViewById(R.id.txt_see_more);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Tracker.class);
                intent.putExtra("some", "some data");
                startActivity(intent);

            }
        });

        TextView b = view.findViewById(R.id.map);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://news.google.com/covid19/map?hl=en-US&gl=US&ceid=US:en");

            }
        });


        return view;

    }

    public void clicked_btn(String url){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
