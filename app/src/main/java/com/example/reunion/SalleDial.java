package com.example.reunion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import DI.DI;
import Model.listItem;
import Services.ApiService;

public class SalleDial extends AppCompatDialogFragment {
    private CheckBox choix1;
    private CheckBox choix2;
    private CheckBox choix3;
    private CheckBox choix4;
    private CheckBox choix5;
    private CheckBox choix6;
    private CheckBox choix7;
    private CheckBox choix8;
    private CheckBox choix9;
    private CheckBox choix10;
    private String choix;
    private List<listItem> listItems;
    private MyAdapter myAdapter;
    private ApiService service;
    private changement creation;
    private String text;
    private Spinner spinner;
    private static final String TAG = "SalleDial";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.salle_layout, null);
        service = DI.getService();
        listItems = service.getReunion();
        myAdapter = new MyAdapter(getContext(), listItems);
        builder.setView(view);
        builder.setTitle("Choix de la salle");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Appliquer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                verifie(text);
            }
        });
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.salles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text = adapterView.getItemAtPosition(i).toString().toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        myAdapter.notifyDataSetChanged();
        //     choix1= view.findViewById(R.id.checkBox1);
        //     choix2= view.findViewById(R.id.checkBox2);
        //     choix3= view.findViewById(R.id.checkBox3);
        //     choix4= view.findViewById(R.id.checkBox4);
        //     choix5= view.findViewById(R.id.checkBox5);
        //     choix6= view.findViewById(R.id.checkBox6);
        //     choix7= view.findViewById(R.id.checkBox7);
        //    choix8= view.findViewById(R.id.checkBox8);
        //   choix9= view.findViewById(R.id.checkBox9);
        //  choix10= view.findViewById(R.id.checkBox10);
        return builder.create();
    }

    private void setChoice() {

        if (choix1.isChecked()){
            verifie(choix1.getText().toString());
        }
        if (choix2.isChecked()){
            verifie(choix2.getText().toString());
        }
        if (choix3.isChecked()){
            verifie(choix3.getText().toString());
        }
        if (choix4.isChecked()){
            verifie(choix4.getText().toString());
        }
        if (choix5.isChecked()){
            verifie(choix5.getText().toString());
        }
        if (choix6.isChecked()){
            verifie(choix6.getText().toString());
        }
        if (choix7.isChecked()){
            verifie(choix7.getText().toString());
        }
        if (choix8.isChecked()){
            verifie(choix8.getText().toString());
        }
        if (choix9.isChecked()){
            verifie(choix9.getText().toString());
        }
        if (choix10.isChecked()){
            verifie(choix10.getText().toString());
        }
    }

    private void verifie(String text) {
        List<listItem> newList = new ArrayList<>();
        for (listItem name : listItems) {
            if (name.getSalle().toString().toLowerCase().contains(text)) {
                newList.add(name);
            }
        }
        myAdapter.updateListe2(newList);
        creation.application();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            creation= (changement) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public interface changement{
        void application();
    }
 }
