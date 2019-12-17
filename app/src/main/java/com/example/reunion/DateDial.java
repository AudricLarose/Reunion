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
import android.widget.CheckBox;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.listItem;

public class DateDial extends AppCompatDialogFragment {
    private SalleDial.changement creation;
    private MyAdapter myAdapter;
    private List<listItem> listItems;
    private static final String TAG = "DateDial";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.date_layout,null);

        builder.setView(view)
                .setTitle("Choix de la date")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Appliquer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        List<listItem> newList =new ArrayList<>();
                        for (listItem name: listItems)
                        {
                            Log.d(TAG, "setPositiveButton: name "+ listItems);
                          //  if (name.getDate().toString().toLowerCase().contains(choix2.getText().toString().toLowerCase())){
                                newList.add(name);
                                Log.d(TAG, "onQueryTextChange: match ");
                           // }
                            Log.d(TAG, "onClick: "+ name.getSalle().toString().toLowerCase());
                        }
                        Log.d(TAG, "setPositiveButton: names  " + listItems);
                        Log.d(TAG, "setPositiveButton: newList "+ newList);
                        myAdapter.updateListe(newList);
                        creation.application();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            creation= (SalleDial.changement) context;
            c;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
