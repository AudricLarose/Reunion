package Services;

import java.util.List;

import Model.listItem;

public interface ApiService {
    List<listItem> getReunion();
    void ajoutReunion(String nom_reunion,String date,String heure,String participant,String salle);
    void supprimeReunion(listItem reunion);
    void filtreReunion();
}
