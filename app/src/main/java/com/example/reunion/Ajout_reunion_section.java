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

import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

import DI.DI;
import Model.Reunion;
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
    private String spinTextSalle;
    private int heureCompare;
    private Boolean checkmail;
    private ImageView retour;
    private List<Reunion> liste;
    private static final String TAG = "Ajout_reunion_section";
    private Date date1;
    private Date heureFinale;
    private Boolean creneauChecker=false;

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
        checkmail=false;
        nom_reunion = (EditText) findViewById(R.id.nom_reunion);
        participant = (EditText) findViewById(R.id.participant);
        retour = (ImageView) findViewById(R.id.imageView2);
        heure=(TextView) findViewById(R.id.selection);
        date=(TextView) findViewById(R.id.selectionDate);
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
                creneauChecker=false;
                mailChecker();
                doublon();
                if ((nom_reunion.getText().toString() != "") && (participant.getText().toString() != "") && (heure.getText().toString() != "") && (spinTextSalle.toString() != "")&& (date.getText().toString() != "") && (checkmail==true)&& (creneauChecker==true)) {
                    service.ajoutReunion(nom_reunion.getText().toString(),date.getText().toString(),heure.getText().toString(),participant.getText().toString(),spinTextSalle);
                    finish();
                }
                else if ((checkmail==true) ||(creneauChecker==true)){ Toast.makeText(Ajout_reunion_section.this,"Veuillez remplir tous les champs ! ",Toast.LENGTH_SHORT).show();}
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
        });}

    private void doublon() {
        liste = service.getReunion();
        if (liste.isEmpty()) {
            creneauChecker = true;
        } else {
            for (Reunion ladate : liste) {     // rechercher liste
                if (date.getText().toString().equals(ladate.getDate()) && (spinTextSalle.equals(ladate.getSalle()))) {                        //si il y a doublons avec date et salle en mm temps
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    try {
                        String heureActuelle = heure.getText().toString() + ":00";
                        Date dateActuelle = sdf.parse(heure.getText().toString() + ":00");
                        Date date3 = sdf.parse(ladate.getHeure() + ":00");
                        // Date date1=sdf.parse(i+":"+i1+":00");
                        Date heureFinale = new Date(date3.getTime() + 2700000); // Ajouter 1 heure en millisecondes
                        Date heureFinale2 = new Date(date3.getTime() - 2700000); // Ajouter 1 heure en millisecondes
                        if ((dateActuelle.compareTo(heureFinale) < 0) && (dateActuelle.compareTo(heureFinale2) > 0)) {
                            creneauChecker = false;
                            Toast.makeText(getApplicationContext(), "La salle est deja prise a ce creneau la ", Toast.LENGTH_SHORT).show();
                            break;
                        } else {
                            creneauChecker = true;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    creneauChecker = true;
                }
            }
        }
    }

    private void mailChecker() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (participant.getText().toString().matches(emailPattern))
        {
            checkmail=true;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"le mail est invalide !",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTimeSet (TimePicker timePicker,int i, int i1){
        TextView textView = (TextView) findViewById(R.id.selection);
        if (i1<10){
        textView.setText(i + ":" + "0"+ i1);}
        if (i1>10){
            textView.setText(i + ":" + i1);}


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
        spinTextSalle = parent.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

