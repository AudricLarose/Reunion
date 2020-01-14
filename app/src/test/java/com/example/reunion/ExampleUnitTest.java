package com.example.reunion;

import android.util.Log;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import DI.DI;
import Model.Reunion;
import Services.ApiService;
import Services.ListeReunion;

import static android.support.constraint.Constraints.TAG;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest {
    private ApiService service;
    List<Reunion> liste_original=new ArrayList<>();
    List<Reunion> liste=new ArrayList<>();

    @Before
    public void setup() {
        service = DI.getService();
        liste_original=service.getExemple();
        service.ajouterTout(liste_original);
        liste=liste_original;
    }

    @Test
    public void getList() {
        List<Reunion> liste_items = service.getReunion();
        List<Reunion> expectedlistItems = ListeReunion.liste_exemple;
        assertThat(liste_items, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedlistItems.toArray()));
    }

    @Test
    public void Reset() {
        List<Reunion> ajoute = service.getReunion();
        service.Reset(ajoute);
        assertFalse(service.getReunion().contains(ajoute));
    }
    @Test
    public void getOriginal() {
        List<Reunion> liste_items = service.getOriginal();
        List<Reunion> expectedlistItems = ListeReunion.liste_original;
        assertThat(liste_items, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedlistItems.toArray()));
    }
    @Test
    public void Suppression() {
        Reunion supprime = liste.get(0);
        service.supprimeReunion(supprime);
        assertFalse(service.getReunion().contains(supprime));
    }
    @Test
    public void Ajouter() {
        String nom_reunion="nom";
        String date="date";
        String heure="heure";
        String participant="participant";
        String salle="salle";
        service.ajoutReunion(nom_reunion,date,heure,participant,salle);
        List<Reunion> liste_items = service.getReunion();
        Reunion ajoute = liste_items.get(liste_items.size()-1);
        assertTrue(liste_items.contains(ajoute));
    }
    @Test
    public void FiltreDate() {
        String Date="dec 5, 2019";
        List<Reunion> listeDate=service.filtreReunion(Date);
        assertTrue(listeDate.contains(listeDate.get(0)));

    }
    @Test
    public void FiltreSalle() {
        String salle="salle 1";
        List<Reunion> listesalle=service.filtreReunion(salle);
        assertTrue(listesalle.contains(listesalle.get(0)));
    }

}