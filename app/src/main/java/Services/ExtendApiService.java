package Services;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.listItem;

    public class ExtendApiService implements ApiService {
    private listItem reunion;
    private ArrayList<listItem> ALL=new ArrayList<>();
    private List<listItem> liste_itemes= ListeReunion.liste_items();
    private List<listItem> listItemList= ListeReunion.list_vide_modifiable();
    private List<listItem> liste_original = ListeReunion.liste_original_modifiable();

    @Override
    public List<listItem> getReunion() {
        return liste_itemes;
    }

        @Override
        public List<listItem> getALL() {
        ALL.clear();
        ALL.addAll(liste_itemes);
        return ALL;
    }

        @Override
        public List<listItem> getOriginal() {
            return liste_original;
        }

    @Override
    public List<listItem> getVide() {
            return listItemList;
        }

    @Override
    public void ajoutReunion(String nom_reunion, String date, String heure, String participant, String salle) {
        liste_itemes.add(new listItem(nom_reunion,date,heure,participant,salle));
        listItemList.add(new listItem(nom_reunion,date,heure,participant,salle));
    }

        @Override
        public void ajouterTout(List<listItem> newList) {
            liste_itemes.clear();
            liste_itemes.addAll(newList);
            liste_itemes.addAll(listItemList);
        }

        @Override
        public void Reset(List<listItem> newList) {
            liste_itemes.clear();
            liste_itemes.addAll(newList);
        }


        @Override
    public void supprimeReunion(listItem reunion) {
        liste_itemes.remove(reunion);
        listItemList.remove(reunion);
    }

        @Override
        public void recherche(String item) {
            List<listItem> newList = new ArrayList<>();
            for (listItem name : liste_itemes) {
                if (name.getSalle().toString().toLowerCase().contains(item) || name.getDate().toString().toLowerCase().contains(item)) {
                    newList.add(name);
                }
            }
        }

        @Override
    public void filtreReunion() {

    }
}
