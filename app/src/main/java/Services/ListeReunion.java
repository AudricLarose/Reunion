package Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.listItem;

public abstract class ListeReunion {

    public static List<listItem> liste_des_items = Arrays.asList(
            new listItem("Projet 1"," Dec 5, 2019","15:30","George@hotmail.com","Salle 1"),
            new listItem("Projet 2"," Dec 5, 2019","5:30","George@hotmail.com","Salle 2"),
            new listItem("Projet 3"," Dec 8, 2019","14:30","George@hotmail.com","Salle 5"),
            new listItem("Projet 4"," Dec 7, 2019","17:30","George@hotmail.com","Salle 5"),
            new listItem("Projet 5"," Dec 9, 2019","19:30","George@hotmail.com","Salle 8")
    );

    static List<listItem> liste_items() {
        return new ArrayList<>(liste_des_items);
    }

    public static List<listItem> list_vide = Arrays.asList(
    );

    static List<listItem> list_vide_modifiable() {
        return new ArrayList<>(list_vide);
    }

}
