package pl.put.poznan.transformer.services.visitors;

import org.junit.Test;
import pl.put.poznan.transformer.model.Actor;
import pl.put.poznan.transformer.model.Scenario;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PointsWithoutActorVisitorTest {

    @Test
    public void getStepsWithoutActor() {

        String text = "Bibliotekarz wybiera opcje dodania nowej pozycji książkowej\n" +
                "Wyświetla się formularz.\n" +
                "Bibliotekarz podaje dane książki.\n" +
                "IF: Bibliotekarz pragnie dodać egzemplarze książki\n" +
                "\tBibliotekarz wybiera opcję definiowania egzemplarzy\n" +
                "\tSystem prezentuje zdefiniowane egzemplarze\n" +
                "\tFOR EACH egzemplarz:\n" +
                "\t\tBibliotekarz wybiera opcję dodania egzemplarza\n" +
                "\t\tSystem prosi o podanie danych egzemplarza\n" +
                "\t\tBibliotekarz podaje dane egzemplarza i zatwierdza.\n" +
                "\t\tSystem informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.\n" +
                "Bibliotekarz zatwierdza dodanie książki.\n" +
                "System informuje o poprawnym dodaniu książki.\n";
        Actor system = new Actor("System");
        List<Actor> aktorzy = new ArrayList<Actor>();
        aktorzy.add(new Actor("Bibliotekarz"));
        Scenario scenario2 = new Scenario("", text, null);
        List<String> slowaKluczowe = new ArrayList<String>();
        slowaKluczowe.add("IF");
        slowaKluczowe.add("ELSE");
        slowaKluczowe.add("FOR EACH");


        PointsWithoutActorVisitor visitorActor = new PointsWithoutActorVisitor();
        List<Actor> actors = new ArrayList<Actor>(aktorzy); // UWAGA! kopiuje zawartosc listy aktorzy (ma Bibliotekarza_
        actors.add(system); // UWAGA! i dorzucam do tej listy System
        visitorActor.setActors(actors);
        visitorActor.setListOfKeyWords(slowaKluczowe);
        scenario2.accept(visitorActor);
        System.out.println("Kroki bez aktora:");
        String result = null;
        for(String stepWithoutActor: visitorActor.getStepsWithoutActor()){
            result += (stepWithoutActor + "\n");
        }
        String expected = "[, Wyświetla się formularz., FOR EACH egzemplarz:]"; // tak sie wyśwwietla bo to lista
        assertEquals(expected, visitorActor.getStepsWithoutActor().toString());
    }

    @Test
    public void getListOfKeyWords() {

        String text = "Bibliotekarz wybiera opcje dodania nowej pozycji książkowej\n" +
                "Wyświetla się formularz.\n" +
                "Bibliotekarz podaje dane książki.\n" +
                "IF: Bibliotekarz pragnie dodać egzemplarze książki\n" +
                "\tBibliotekarz wybiera opcję definiowania egzemplarzy\n" +
                "\tSystem prezentuje zdefiniowane egzemplarze\n" +
                "\tFOR EACH egzemplarz:\n" +
                "\t\tBibliotekarz wybiera opcję dodania egzemplarza\n" +
                "\t\tSystem prosi o podanie danych egzemplarza\n" +
                "\t\tBibliotekarz podaje dane egzemplarza i zatwierdza.\n" +
                "\t\tSystem informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.\n" +
                "Bibliotekarz zatwierdza dodanie książki.\n" +
                "System informuje o poprawnym dodaniu książki.\n";
        Actor system = new Actor("System");
        List<Actor> aktorzy = new ArrayList<Actor>();
        aktorzy.add(new Actor("Bibliotekarz"));
        Scenario scenario2 = new Scenario("", text, null);
        List<String> slowaKluczowe = new ArrayList<String>();
        slowaKluczowe.add("IF");
        slowaKluczowe.add("ELSE");
        slowaKluczowe.add("FOR EACH");

        ScenarioToCertainDepthVisitor vistorDepth = new ScenarioToCertainDepthVisitor(2);
        scenario2.accept(vistorDepth);

        assertTrue(vistorDepth.getOutputList().contains("IF"));
//        assertTrue(vistorDepth.getOutputList().contains("ELSE"));
//        assertTrue(vistorDepth.getOutputList().contains("FOR EACH"));
    }
}