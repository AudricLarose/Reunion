package Services;

import java.util.List;

import Model.Reunion;


public interface ApiService {
    List<Reunion> getReunion();
    List<Reunion> getALL();
    void ajoutReunion(String nom_reunion,String date,String heure,String participant,String salle);
    void ajouterTout (List<Reunion> newList);
    void supprimeReunion(Reunion reunion);
    List<Reunion> getOriginal ();
    List<Reunion> getExemple ();
    void Reset(List<Reunion> newList);
    List<Reunion> filtreReunion(String item);
}
