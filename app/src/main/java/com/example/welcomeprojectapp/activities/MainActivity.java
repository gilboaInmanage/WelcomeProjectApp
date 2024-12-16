package com.example.welcomeprojectapp.activities;

import static com.example.welcomeprojectapp.applications.WelcomeApplication.app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.welcomeprojectapp.R;
import com.example.welcomeprojectapp.adapters.DataAdapter;
import com.example.welcomeprojectapp.data.ContentPage;
import com.example.welcomeprojectapp.data.Language;
import com.example.welcomeprojectapp.data.StoreFeature;
import com.example.welcomeprojectapp.models.Item;
import com.example.welcomeprojectapp.server_responses.GeneralDeclarationResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<>();
        adapter = new DataAdapter(this, items);
        recyclerView.setAdapter(adapter);

        fetchData();
    }

    private void fetchData() {
        //Log.d("MainActivity", app.getSessionData().getGeneralDeclarationResponse().toString());

        GeneralDeclarationResponse response = (GeneralDeclarationResponse) app.getSessionData().getGeneralDeclarationResponse();

        if (response != null) {
            for (Language language : response.getLanguages()) {
                items.add(new Item("Language", language.getTitle() + " (" + language.getDescription() + ")"));
            }

            for (ContentPage contentPage : response.getContentPages()) {
                items.add(new Item("Content Page", contentPage.getTitle() + " (" + contentPage.getRoute() + ")"));
            }

            for (StoreFeature feature : response.getFeatures()) {
                items.add(new Item("Feature", feature.getTitle() + " (Sort Order: " + feature.getSortOrder() + ")"));
            }

            adapter.notifyDataSetChanged();
        }
    }
}