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
import com.example.welcomeprojectapp.models.Item;

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

        fetchData();
    }

    private void fetchData() {
        Log.d("MainActivity", app().getSessionData().toString());

    }
}