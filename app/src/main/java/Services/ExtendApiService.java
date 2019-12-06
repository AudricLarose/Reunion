package Services;

import android.util.Log;

import java.util.List;

import Model.listItem;

import static android.support.constraint.Constraints.TAG;

    public class ExtendApiService implements ApiService {
    private listItem reunion;
    private List<listItem> liste_itemes= ListeReunion.liste_items();

    @Override
    public List<listItem> getReunion() {
        return liste_itemes;
    }

    @Override
    public void ajoutReunion(String nom_reunion, String date, String heure, String participant, String salle) {
        liste_itemes.add(new listItem(nom_reunion,date,heure,participant,salle));
        Log.d(TAG, "ajoutReunion: liste"+ liste_itemes);
    }


    @Override
    public void supprimeReunion(listItem reunion) {
        liste_itemes.remove(reunion);
    }

    @Override
    public void filtreReunion() {

    }
}
