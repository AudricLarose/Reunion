package Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.Reunion;


public abstract class ListeReunion {

    public static List<Reunion> liste_des_items = Arrays.asList(

    );

    static List<Reunion> liste_items() {
        return new ArrayList<>(liste_des_items);
    }

    public static List<Reunion> liste_original = Arrays.asList(

    );

    static List<Reunion> liste_original_modifiable() {
        return new ArrayList<>(liste_original);
    }

    public static List<Reunion> list_vide = Arrays.asList(
    );


    static List<Reunion> list_vide_modifiable() {
        return new ArrayList<>(list_vide);
    }

    public static List<Reunion> liste_exemple = Arrays.asList(
            new Reunion("Projet 1","Dec 5, 2019","15:30","George@hotmail.com","Salle 1"),
            new Reunion("Projet 2","Dec 5, 2019","15:30","George@hotmail.com","Salle 2"),
            new Reunion("Projet 3","Dec 8, 2019","14:30","George@hotmail.com","Salle 5"),
            new Reunion("Projet 4","Dec 7, 2019","17:30","George@hotmail.com","Salle 5"),
            new Reunion("Projet 5","Dec 9, 2019","19:30","George@hotmail.com","Salle 8")
    );

    static List<Reunion> liste_dexemple_modifiable() {
        return new ArrayList<>(liste_exemple);
    }

}
