package pl.put.poznan.transformer.services.visitors;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.transformer.model.Actor;
import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.UseCase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ScenarioToCertainDepthVisitorTest {

    Scenario scenario;
    Actor system;
    List<Actor> aktorzy;
    UseCase useCase;

    @Before
    public void initTests() {
        scenario = new Scenario();
        SingleStep s1 = new SingleStep("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej", scenario);
        scenario.add(s1);
        SingleStep s2 = new SingleStep("Wyświetla się formularz.", scenario);
        scenario.add(s2);
        SingleStep s3 = new SingleStep("Bibliotekarz podaje dane książki.", scenario);
        scenario.add(s3);
        Scenario s4 = new Scenario("IF: Bibliotekarz pragnie dodać egzemplarze książki", scenario);
        scenario.add(s4);
        SingleStep s41 = new SingleStep("Bibliotekarz wybiera opcję definiowania egzemplarzy", s4);
        s4.add(s41);
        SingleStep s42 = new SingleStep("System prezentuje zdefiniowane egzemplarze", s4);
        s4.add(s42);
        Scenario s43 = new Scenario("FOR EACH egzemplarz:", s4);
        s4.add(s43);
        SingleStep s431 = new SingleStep("Bibliotekarz wybiera opcję dodania egzemplarza", s43);
        s43.add(s431);
        SingleStep s432 = new SingleStep("System prosi o podanie danych egzemplarza", s43);
        s43.add(s432);
        SingleStep s433 = new SingleStep("Bibliotekarz podaje dane egzemplarza i zatwierdza.", s43);
        s43.add(s433);
        SingleStep s434 = new SingleStep("System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.", s43);
        s43.add(s434);
        SingleStep s5 = new SingleStep("Bibliotekarz zatwierdza dodanie książki.", scenario);
        scenario.add(s5);
        SingleStep s6 = new SingleStep("System informuje o poprawnym dodaniu książki.", scenario);
        scenario.add(s6);


        system = new Actor("System");
        aktorzy = new ArrayList<Actor>();
        aktorzy.add(new Actor("Bibliotekarz"));
        useCase = new UseCase("Jakis tytul", aktorzy, system, scenario);
    }

    @Test
    public void getOutputList() {
        // Tworzenie
        ScenarioToCertainDepthVisitor mockObject = mock(ScenarioToCertainDepthVisitor.class);

        scenario.accept(mockObject);

        // Konfiguracja
        mockObject.setMaxDepth(2);
        when(mockObject.getOutputList()).thenReturn("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej\n" +
                "Wyświetla się formularz.\n" +
                "Bibliotekarz podaje dane książki.\n" +
                "IF: Bibliotekarz pragnie dodać egzemplarze książki\n" +
                "\tBibliotekarz wybiera opcję definiowania egzemplarzy\n" +
                "\tSystem prezentuje zdefiniowane egzemplarze\n" +
                "\tFOR EACH egzemplarz:\n" +
                "Bibliotekarz zatwierdza dodanie książki.\n" +
                "System informuje o poprawnym dodaniu książki.");
        // Interakcja
        String result = mockObject.getOutputList();
        // Weryfikacja
        verify(mockObject).getOutputList(); // weryfikacja poprawności
        // interakcji z obiektem zastępczym (Mockito)
//        assertEquals(2, mockObject.getMaxDepth()); // weryfikacja kodu testowanego (JUnit)
    }

    @Test
    public void PointsWithMochSecnario() {

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

        String expected = "1. Bibliotekarz wybiera opcje dodania nowej pozycji książkowej\n" +
                "2. Wyświetla się formularz.\n" +
                "3. Bibliotekarz podaje dane książki.\n" +
                "4. IF: Bibliotekarz pragnie dodać egzemplarze książki\n" +
                "4.1. Bibliotekarz wybiera opcję definiowania egzemplarzy\n" +
                "4.2. System prezentuje zdefiniowane egzemplarze\n" +
                "4.3. FOR EACH egzemplarz:\n" +
                "4.3.1. Bibliotekarz wybiera opcję dodania egzemplarza\n" +
                "4.3.2. System prosi o podanie danych egzemplarza\n" +
                "4.3.3. Bibliotekarz podaje dane egzemplarza i zatwierdza.\n" +
                "4.3.4. System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.\n" +
                "5. Bibliotekarz zatwierdza dodanie książki.\n" +
                "6. System informuje o poprawnym dodaniu książki.\n";
        Scenario scenario2 = mock(Scenario.class);
        scenario2 = new Scenario("", text, null);
        UseCase useCase2 = new UseCase("Jakis tytul", aktorzy, system, scenario2);


        ScenarioInPointsVisitor visitor2 = new ScenarioInPointsVisitor();
        scenario2.accept(visitor2);
        String result = visitor2.getOutputList();

        // Konfiguracja
//        when(visitor2.getOutputList()).thenReturn(expected);
        // Interakcja

        assertEquals(expected, result); // weryfikacja kodu testowanego (JUnit)
    }


    @Test
    public void numberOfStepsWhenScenarioIsShorten() {
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


        Scenario scenario2 = mock(Scenario.class);
        scenario2 = new Scenario("", text, null);
        UseCase useCase2 = new UseCase("Jakis tytul", aktorzy, system, scenario2);


        NumberOfPointsInScenarioVisitor visitorNumber = new NumberOfPointsInScenarioVisitor();
        scenario2.accept(visitorNumber);
        assertEquals(13, visitorNumber.getNumberOfPoints());

    }

}