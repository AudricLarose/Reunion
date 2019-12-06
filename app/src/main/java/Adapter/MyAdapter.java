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
    private final List<listItem> listItemes;
    private static final String TAG = "MyAdapter";
    private ApiService exe;
    private List<String> names;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_row, viewGroup,false);
        return new ViewHolder(view);
    }

    public MyAdapter (Context context , List listItemes ){
        this.context=context;
        this.listItemes=listItemes;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int position) {
        exe= DI.getService();
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
                exe.supprimeReunion(item);
                Log.d(TAG, "onClick: Liste apres suppression "+ listItemes);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItemes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
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

    public void updateListe(List<String> newList) {
        names= new ArrayList<>();
        names.addAll(newList);
        notifyDataSetChanged();

    }

}
