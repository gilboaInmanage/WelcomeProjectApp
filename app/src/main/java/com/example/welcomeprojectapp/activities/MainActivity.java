package com.example.welcomeprojectapp.activities;

import static com.example.welcomeprojectapp.applications.WelcomeApplication.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<>();
        adapter = new DataAdapter(this, items);
        recyclerView.setAdapter(adapter);

        // Fetch data from GeneralDeclarationResponse and update RecyclerView
        fetchData();
    }

    private void fetchData() {
        // Log GeneralDeclarationResponse for debugging
        Log.d("MainActivity", app.getSessionData().getGeneralDeclarationResponse().toString());

        // Extract data from GeneralDeclarationResponse
        GeneralDeclarationResponse response = (GeneralDeclarationResponse) app.getSessionData().getGeneralDeclarationResponse();

        if (response != null) {
            // Add languages to the items list
            for (Language language : response.getLanguages()) {
                items.add(new Item("Language", language.getTitle() + " (" + language.getDescription() + ")"));
            }

            // Add content pages to the items list
            for (ContentPage contentPage : response.getContentPages()) {
                items.add(new Item("Content Page", contentPage.getTitle() + " (" + contentPage.getRoute() + ")"));
            }

            // Add store features to the items list
            for (StoreFeature feature : response.getFeatures()) {
                items.add(new Item("Feature", feature.getTitle() + " (Sort Order: " + feature.getSortOrder() + ")"));
            }

            // Notify adapter of data changes
            adapter.notifyDataSetChanged();
        }
    }
}