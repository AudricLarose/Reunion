package Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.listItem;

public abstract class ListeReunion {

    public static List<listItem> liste_des_items = Arrays.asList(
            new listItem("projet 1","5,Dec","15:30","George@hotmail.com","A"),
            new listItem("projet 2","5,Dec","5:30","George@hotmail.com","A"),
            new listItem("projet 3","5,Dec","14:30","George@hotmail.com","A"),
            new listItem("projet 4","5,Dec","17:30","George@hotmail.com","A"),
            new listItem("projet 5","5,Dec","19:30","George@hotmail.com","A")
    );
    static List<listItem> liste_items() {
        return new ArrayList<>(liste_des_items);
    }

}
