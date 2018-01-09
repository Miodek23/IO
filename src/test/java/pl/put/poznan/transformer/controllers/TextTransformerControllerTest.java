package pl.put.poznan.transformer.controllers;

import com.meterware.httpunit.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.put.poznan.transformer.model.*;
import pl.put.poznan.transformer.services.visitors.KeyWordsVisitor;
import pl.put.poznan.transformer.services.visitors.NumberOfPointsInScenarioVisitor;
import pl.put.poznan.transformer.services.visitors.ScenarioInPointsVisitor;
import pl.put.poznan.transformer.services.visitors.ScenarioInPointsWithTabsVisitor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TextTransformerControllerTest {
    Scenario scenario;
    Actor system;
    List<Actor> aktorzy;
    UseCase useCase;
    String text;

    ProcessedScenario ps;
    @Before
    public void setUp() {
        text = "Bibliotekarz wybiera opcje dodania nowej pozycji książkowej\n" +
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
        scenario = new Scenario("", text, null);

        system = new Actor("System");
        aktorzy = new ArrayList<Actor>();
        aktorzy.add(new Actor("Bibliotekarz"));
        useCase = new UseCase("Jakis tytul", aktorzy, system, scenario);

        scenario = useCase.getScenario(); // tego nie musi byc

    }


    @Test
    public void childAndParentTest() {

        Step stepChild = scenario.getChild(3);
        Step anotherStep = stepChild.getChild(1);
        String output = anotherStep.getText();

        assertEquals("System prezentuje zdefiniowane egzemplarze", output);

    }

    @Test
    public void keyWordsTest() {

        Scenario scenario2 = new Scenario("", text, null);
        List<String> slowaKluczowe = new ArrayList<String>();
        slowaKluczowe.add("IF");
        slowaKluczowe.add("ELSE");
        slowaKluczowe.add("FOR EACH");

        KeyWordsVisitor visitorKey = new KeyWordsVisitor();
        visitorKey.setListOfKeyWords(slowaKluczowe);
        scenario2.accept(visitorKey);

        assertEquals(2, visitorKey.getNumberOfKeyWord()); // tu mi nie działa
    }


    @Test(expected = NullPointerException.class)
    public void nullTextTest() {
        Scenario scenario2 = new Scenario("", null, null);

        NumberOfPointsInScenarioVisitor visitorNumber = new NumberOfPointsInScenarioVisitor();
        scenario2.accept(visitorNumber);
        assertEquals(0, visitorNumber.getNumberOfPoints());
    }

    @Test
    public void getScenarioInPointsWithTabsTest() {

        ScenarioInPointsVisitor visitor = new ScenarioInPointsVisitor();
        scenario.accept(visitor);
        assertEquals(false, visitor.getOutputList().contains("\t\t"));

    }

    @Test
    public void getScenarioNumberOfPointsTest() {

        NumberOfPointsInScenarioVisitor visitorNumber = new NumberOfPointsInScenarioVisitor();

        scenario.accept(visitorNumber);

        assertEquals(13, visitorNumber.getNumberOfPoints());

    }

}