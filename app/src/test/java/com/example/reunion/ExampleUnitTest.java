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
import Model.listItem;
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
    List<listItem> liste=new ArrayList<>();

    @Before
    public void setup() {
        service = DI.getService();
    }

    @Test
    public void getList() {
        List<listItem> liste_items = service.getReunion();
        List<listItem> expectedlistItems = ListeReunion.liste_des_items;
        assertThat(liste_items, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedlistItems.toArray()));
    }

    @Test
    public void Reset() {
        List<listItem> ajoute = service.getReunion();
        service.Reset(ajoute);
        assertFalse(service.getReunion().contains(ajoute));
    }
    @Test
    public void getOriginal() {
        List<listItem> liste_items = service.getOriginal();
        List<listItem> expectedlistItems = ListeReunion.liste_original;
        assertThat(liste_items, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedlistItems.toArray()));
    }
    @Test
    public void ajouterTout() {
        listItem supprime = service.getReunion().get(0);
        service.supprimeReunion(supprime);
        assertFalse(service.getReunion().contains(supprime));
    }

}