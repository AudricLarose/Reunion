package com.example.reunion;

import android.app.DatePickerDialog;
import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapter.MyAdapter;
import DI.DI;
import Model.Reunion;

import Services.ApiService;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SalleDial.changement, DatePickerDialog.OnDateSetListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Reunion> listItems;
    private SalleDial.changement creation;
    private ApiService service;
    private ImageButton ajouter;
    private MyAdapter myAdapter;
    private ImageView refresh;
    public static Boolean ConfirmationReset;
    private final List<Reunion> ListReset=new ArrayList<>();
    private List<Reunion> ListOrignal=new ArrayList<>();
    private TextView listeGone;
    private List<Reunion> toutLaliste= new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = DI.getService();

        creation=new SalleDial.changement() {
    @Override
    public void application() {
        adapter.notifyDataSetChanged();
    }};
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewId);
        ajouter = (ImageButton) findViewById(R.id.ajout);
        refresh= (ImageView) findViewById(R.id.Refresh);
        listeGone= findViewById(R.id.reuniongone);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = service.getReunion();
        ListOrignal=service.getOriginal();
        adapter = new MyAdapter(this, listItems);
        adapter.notifyDataSetChanged();
        ListReset.addAll(service.getReunion());
        myAdapter =new MyAdapter(this, listItems);
        recyclerView.setAdapter(adapter);
        listItems= ListReset;
        myAdapter.updateListe2(listItems);
        creation.application();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmationReset=true;
                myAdapter.updateListe(listItems);
                creation.application();
                refresh.setVisibility(View.GONE);
            }
        });
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
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String userInput = s.toLowerCase();
        recherche (userInput);
        return true;
    }

    public void recherche(String item) {
        List<Reunion> newList =new ArrayList<>();
        for (Reunion name: listItems)
        {
            if (name.getSalle().toString().toLowerCase().contains(item) || name.getDate().toString().toLowerCase().contains(item)){
                newList.add(name);
            }
        }
        myAdapter.updateListe(newList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                openDialogSalle();
                adapter.notifyDataSetChanged();
                return true;
            case R.id.item3:
                DialogFragment DatePicking = new DatePickerFragment();
                DatePicking.show(getSupportFragmentManager(), "Selection de la date");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openDialogSalle() {
        SalleDial salleDial=new SalleDial();
        salleDial.show(getSupportFragmentManager(),"Selectionner une Salle");
        refresh.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("confirm_rotation",true);
        Log.d(TAG, "onSaveInstanceState: ok");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Boolean confirmation = savedInstanceState.getBoolean("confirm_rotation");
        if (confirmation==true){
            listItems=ListOrignal;
            myAdapter.updateListe2(listItems);
        }
    }

    @Override
    public void application() {
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onDateSet(DatePicker datePicker,int year, int month, int day) {
        toutLaliste=service.getALL();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String DateActuelle = DateFormat.getDateInstance().format(c.getTime());
        List<Reunion> newList =new ArrayList<>();

        for (Reunion name: toutLaliste)
        {
            if (name.getDate().toString().toLowerCase().contains(DateActuelle.toString().toLowerCase())){
                newList.add(name);
            }
        }
        refresh.setVisibility(View.VISIBLE);
        myAdapter.updateListe2(newList);
        creation.application();
    }
}
