package Services;

import java.util.List;

import Model.listItem;

public interface ApiService {
    List<listItem> getReunion();
    List<listItem> getVide();
    void ajoutReunion(String nom_reunion,String date,String heure,String participant,String salle);
    void ajouterTout (List<listItem> newList);
    void supprimeReunion(listItem reunion);
    List<listItem> getOriginal ();
    void Reset(List<listItem> newList);
    void recherche(String item);
    void filtreReunion();
}
