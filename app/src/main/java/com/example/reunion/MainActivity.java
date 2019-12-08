package com.example.reunion;

import android.app.LauncherActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import DI.DI;
import Model.listItem;
import Services.ApiService;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<listItem> listItems;
    private ApiService service;
    private ImageButton ajouter;
    private MyAdapter myAdapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onStart() {
        adapter.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    protected void onPause() {
        adapter.notifyDataSetChanged();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = DI.getService();
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewId);
        ajouter = (ImageButton) findViewById(R.id.ajout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = service.getReunion();
        adapter = new MyAdapter(this, listItems);
        myAdapter =new MyAdapter(this, listItems);
        recyclerView.setAdapter(adapter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Ajout_reunion_section.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuitem= menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuitem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String userInput = s.toLowerCase();
        List<listItem> newList =new ArrayList<>();
        for (listItem name: listItems)
        {
            Log.d(TAG, "onQueryTextChange: name "+ listItems);
            if (name.getSalle().toString().toLowerCase().contains(userInput) || name.getDate().toString().toLowerCase().contains(userInput)){
                newList.add(name);
                Log.d(TAG, "onQueryTextChange: match ");
            }
        }
        Log.d(TAG, "onQueryTextChange: names  " + listItems);
        Log.d(TAG, "onQueryTextChange: userInput  " + userInput);
        Log.d(TAG, "onQueryTextChange: newList "+ newList);
        myAdapter.updateListe(newList);
        adapter.notifyDataSetChanged();

        return true;
    }
}
