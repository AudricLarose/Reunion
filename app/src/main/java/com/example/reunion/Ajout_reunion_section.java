package com.example.reunion;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import DI.DI;
import Services.ApiService;

public class Ajout_reunion_section extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {
    private EditText nom_reunion;
    private EditText participant;
    private TextView heure;
    private TextView date;
    private EditText salle;
    private Button valider;
    private Button Bouton;
    private Button Bouton_date;
    private Spinner spinner;
    private TextView selection_Date;
    private ApiService service;
    private String text;
    private ImageView retour;
    private static final String TAG = "Ajout_reunion_section";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service=DI.getService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_reunion_section);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.salles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        nom_reunion = (EditText) findViewById(R.id.nom_reunion);
        participant = (EditText) findViewById(R.id.participant);
        retour = (ImageView) findViewById(R.id.imageView2);
        heure=(TextView) findViewById(R.id.selection);
        date=(TextView) findViewById(R.id.selectionDate);
        salle = (EditText) findViewById(R.id.salle);
        valider = (Button) findViewById(R.id.valider);
        Bouton = (Button) findViewById(R.id.Bouton_Heure);
        Bouton_date = (Button) findViewById(R.id.bouton_date);
        selection_Date = (TextView) findViewById(R.id.selectionDate);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((nom_reunion != null) && (participant != null) && (heure != null) && (text != null)&& (date != null)) {
                    Log.d(TAG, "onClick: " + nom_reunion.getText().toString());
                    Log.d(TAG, "onClick: " + participant.getText().toString());
                    Log.d(TAG, "onClick: " + text);
                    service.ajoutReunion(nom_reunion.getText().toString(),date.getText().toString(),heure.getText().toString(),participant.getText().toString(),text);
                    finish();
                }
            }

        });

        Bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment TimePicking = new TimePickerFragment();
                TimePicking.show(getSupportFragmentManager(), "time picker");
            }
        });
        Bouton_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment DatePicking = new DatePickerFragment();
                DatePicking.show(getSupportFragmentManager(), "Selection de la date");
            }
        });
    }
        @Override
        public void onTimeSet (TimePicker timePicker,int i, int i1){
            TextView textView = (TextView) findViewById(R.id.selection);
            textView.setText(i + ":" + i1);
        }


        @Override
        public void onDateSet (DatePicker datePicker,int year, int month, int day){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            String DateActuelle = DateFormat.getDateInstance().format(c.getTime());
            selection_Date.setText(DateActuelle);
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        text = parent.getItemAtPosition(i).toString();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


