package Services;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.Reunion;

    public class ExtendApiService implements ApiService {
    private Reunion reunion;
    private ArrayList<Reunion> ALL=new ArrayList<>();
    private List<Reunion> liste_itemes= ListeReunion.liste_items();
    private List<Reunion> listItemList= ListeReunion.list_vide_modifiable();
    private List<Reunion> liste_original = ListeReunion.liste_original_modifiable();
    private List<Reunion> liste_dexemple = ListeReunion.liste_dexemple_modifiable();


        @Override
    public List<Reunion> getReunion() {
        return liste_itemes;
    }

        @Override
        public List<Reunion> getALL() {
        ALL.clear();
        ALL.addAll(liste_itemes);
        return ALL;
    }

        @Override
        public List<Reunion> getOriginal() {
            return liste_original;
        }

        @Override
        public List<Reunion> getExemple() {
            return liste_dexemple;        }


    @Override
    public void ajoutReunion(String nom_reunion, String date, String heure, String participant, String salle) {
        liste_itemes.add(new Reunion(nom_reunion,date,heure,participant,salle));
        listItemList.add(new Reunion(nom_reunion,date,heure,participant,salle));
    }

        @Override
        public void ajouterTout(List<Reunion> newList) {
            liste_itemes.clear();
            liste_itemes.addAll(newList);
            liste_itemes.addAll(listItemList);
        }

        @Override
        public void Reset(List<Reunion> newList) {
            liste_itemes.clear();
            liste_itemes.addAll(newList);
        }


        @Override
    public void supprimeReunion(Reunion reunion) {
        liste_itemes.remove(reunion);
        listItemList.remove(reunion);
    }

        @Override
        public List<Reunion> filtreReunion(String item) {
            List<Reunion> newList = new ArrayList<>();
            for (Reunion name : liste_itemes) {
                if (name.getSalle().toString().toLowerCase().contains(item) || name.getDate().toString().toLowerCase().contains(item)) {
                    newList.add(name);
                }
            }
            return newList;
        }

}
