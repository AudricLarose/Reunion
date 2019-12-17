package Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.reunion.R;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import DI.DI;
import Model.listItem;
import Services.ApiService;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private listItem reunion;
    private  List<listItem>   listItemes;
    private List<listItem> listItemesFull;
    private static final String TAG = "MyAdapter";
    private ApiService exe;
    private boolean Confirmation;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_row, viewGroup,false);
        return new ViewHolder(view);
    }

    public MyAdapter (Context context , List<listItem> listItemes ){
        this.context=context;
        this.listItemes=listItemes;
        Log.d(TAG, "updateListe: CONSTRUC " + listItemes);
        listItemesFull= new ArrayList<>(listItemes);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, final int position) {
        exe= DI.getService();
        listItemes=exe.getReunion();
        Log.d(TAG, "onBindViewHolder: liste total " + listItemes);
        final listItem item= listItemes.get(position);
        viewHolder.name.setText(item.getNom_reunion());
        viewHolder.description.setText(item.getDate());
        viewHolder.heure.setText(item.getHeure());
        viewHolder.participant.setText(item.getParticipant());
        viewHolder.salle.setText(item.getSalle());
        Log.d(TAG, "onBindViewHolder: item " + item);
        viewHolder.poubelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: liste avant suppression " + listItemes);
                Log.d(TAG, "onClick: item supprimé " + item);
                Log.d(TAG, "onClick: position numero "+ position);
                exe.supprimeReunion(item);
                    Log.d(TAG, "onClick: Liste apres suppression "+ listItemes);
                notifyDataSetChanged();            }
        });
    }
     public void updateListe(List<listItem> newList) {
         exe= DI.getService();
         listItemes = new ArrayList<>();
         Log.d(TAG, "updateListe: newlist" + newList);
         exe.ajouterTout(newList);
         Log.d(TAG, "updateListe: listitem" + listItemes);
         notifyDataSetChanged();

    }
    public void updateListe2(List<listItem> newList) {
        exe= DI.getService();
        listItemes = new ArrayList<>();
        Log.d(TAG, "updateListe: newlist" + newList);
        exe.Reset(newList);
        Log.d(TAG, "updateListe: listitem" + listItemes);
        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return listItemes.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView description;
        public TextView heure;
        public TextView participant;
        public TextView salle;
        public ImageButton poubelle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.title);
            description= (TextView) itemView.findViewById(R.id.description);
            heure= (TextView) itemView.findViewById(R.id.great);
            participant= (TextView) itemView.findViewById(R.id.partic);
            salle= (TextView) itemView.findViewById(R.id.salle);
            poubelle= (ImageButton) itemView.findViewById(R.id.item_list_delete_button);
        }
    }



}
